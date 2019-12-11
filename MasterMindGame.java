import java.util.Arrays;

public class MasterMindGame{
    private int puzzleLength = puzzleLength();
    private int maxGuesses = maxGuesses();
    private int numberRounds = 4;
    private int roundsPlayed = 0;
    private int[] points = new int[]{0,0};
    private int puzzleMaster = 0;
    private int guessingPlayer = 0;
    private int player1 = 1;
    private int player2 = 2;
    private static Piece[] puzzle;
    

    public MasterMindGame(int numberRounds, int puzzleLength, int maxGuesses){
        this.numberRounds = numberRounds;
        this.puzzleLength = puzzleLength;
        if( numberRounds > 2 && numberRounds % 2 == 0 && puzzleLength > 1 && maxGuesses >= 1){
            BoardState board = new BoardState(puzzle,maxGuesses);
        }
    }

    private boolean isEqual(Piece[] puzzle, Piece[] guess){
        return Arrays.equals(puzzle,guess);
    }

    public int puzzleLength(){
        return puzzleLength;
    }

    public int maxGuesses(){
        return maxGuesses;
    }

    public int roundsPlayed(){
        return roundsPlayed;
    }

    public int numberRounds(){
        return numberRounds;
    } 

    public boolean isValid(Piece[] pieces){
        boolean isValid = true; 
        int i = 0;
        if(pieces.length == puzzleLength && puzzleLength > 1){
            while(isValid || i < puzzleLength ){
                if(pieces[i] != null ){
                    isValid = false;
                }
                i++;
            }
        }else{
            isValid = false;
        }
        return isValid;
    }

    public boolean roundBeingPlayed(){
        boolean roundBeingPlayed = true;
        if(roundsPlayed > 4){
            roundBeingPlayed = false;
        }
        return roundBeingPlayed;
    }

    public int points(int player){
        return points[player-1];
    }
    
    public int guessingPlayer(){
        if(roundBeingPlayed() == true){
            if(guessingPlayer == 1){
                guessingPlayer = player2;
            }
            else{
                guessingPlayer = player1;
            }
        }
        return guessingPlayer;
    }

    public int puzzleMaster(){
        if(roundBeingPlayed() == true){
            if(puzzleMaster == 1){
                puzzleMaster = player2;
            }
            else{
                puzzleMaster = player1;
            }
        }
        return puzzleMaster;
    }

    public boolean isOver(){
        boolean isOver = false;
        if(guesses() > maxGuesses || roundsPlayed > numberRounds){
            isOver = true;
        }
        return isOver;
    }

    public void startNewRound(Piece[] puzzle){
        roundsPlayed++;
        if(isValid(puzzle) && !roundBeingPlayed() && !isOver()){
            this.puzzle = Arrays.copyOf(puzzle,puzzleLength);
        }
    }

    public int[] play(Piece[] guess){
        int[] play = new int[2];
        if(roundBeingPlayed() && isValid(guess)){
            play[0] = colorPosMatch(guesses());
            play[1] = onlyColorMatches(guesses());
            if(guessingPlayer() == player1 && isEqual(puzzle,guess)){
                points[0]++;
            }
            else if(guessingPlayer() == player2 && isEqual(puzzle,guess)){
                points[1]++;
            }
            if(isEqual(puzzle,guess) || guesses() > maxGuesses){
                startNewRound(puzzle);
            }       
        }
    }

    public String toString(){
        String b = "b";
        return b;
    }

}