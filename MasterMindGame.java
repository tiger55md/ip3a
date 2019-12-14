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
    private BoardState board;
    private static Piece[] guess;
    

    public MasterMindGame(int numberRounds, int puzzleLength, int maxGuesses){
        this.numberRounds = numberRounds;
        this.puzzleLength = puzzleLength;
        if( numberRounds > 2 && numberRounds % 2 == 0 && puzzleLength > 1 && maxGuesses >= 1){
            this.board = new BoardState(puzzle,maxGuesses);
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
        if(board.guesses() > maxGuesses){
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
        if(board.guesses() > maxGuesses || roundsPlayed > numberRounds || isEqual(puzzle,guess)){
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
            play[0] = board.colorPosMatch(board.guesses());
            play[1] = board.onlyColorMatches(board.guesses());
            if(guessingPlayer() == player1 && isEqual(puzzle,guess)){
                points[0]++;
            }
            else if(guessingPlayer() == player2 && isEqual(puzzle,guess)){
                points[1]++;
            }
            if(isEqual(puzzle,guess) || board.guesses() > maxGuesses){
                startNewRound(puzzle);
            }       
        }
        return play;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("__________________________________________");
        sb.append("/n");
        for(int k = board.guesses(); k >= 0; k--){
            if(!isOver()){
            sb.append("Round " + roundsPlayed + " of " + numberRounds + " being played. " );
            if(guessingPlayer == player1){
                sb.append("Player1 guessing. \n");
            }
            else{
                sb.append("Player2 guessing. \n");
            }
            sb.append("Score: " + points[0] + " - " + points[1]+ "   " + " Remaining guesses: " + (maxGuesses - board.guesses()) + "\n");
            sb.append(board.toString());
        }

            if(isOver()){
                sb.append("Round " + roundsPlayed + " of " + numberRounds + " ended. \n" );
                    if(guessingPlayer == player1 && isEqual(puzzle,guess)){
                        sb.append("Player1 guessed puzzle in " + board.guesses() + " attempts. \n");
                    }
                    else if(guessingPlayer == player2 && isEqual(puzzle,guess)){
                        sb.append("Player2 guessed puzzle in " + board.guesses() + " attempts. \n");
                    }
                    sb.append("Score: " + points[0] + " - " + points[1] + "\n");
                    sb.append(board.toString());
                }
            }
        return sb.toString();
    }

}
