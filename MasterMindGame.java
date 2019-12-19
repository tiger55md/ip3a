import java.util.Arrays;

public class MasterMindGame{
    private int puzzleLength;
    private int maxGuesses;
    private int numberRounds;
    private int roundsPlayed = 0;
    private int[] points = new int[]{0,0};
    private int puzzleMaster = 1;
    private int guessingPlayer = 2;
    private BoardState board;
    private static Piece[] puzzle;
    private static Piece[] guess;
    private boolean roundBeingPlayed=false;


    public MasterMindGame(int numberRounds, int puzzleLength, int maxGuesses){
        this.numberRounds = numberRounds;
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
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
        return board.isValid(pieces);
    }

    public boolean roundBeingPlayed(){  
        return roundBeingPlayed;
    }

    public int points(int player){
        return points[player-1];
    }

    public int guessingPlayer(){
        if(roundBeingPlayed() == true){
            if(guessingPlayer == 1){
                guessingPlayer = 2;
            }
            else{
                guessingPlayer = 1;
            }
        }
        return guessingPlayer;
    }

    public int puzzleMaster(){
        if(roundBeingPlayed() == true){
            if(puzzleMaster == 1){
                puzzleMaster = 2;
            }
            else{
                puzzleMaster = 1;
            }
        }
        return puzzleMaster;
    }

    public boolean isOver(){
        return roundsPlayed > numberRounds;
    }

    public void startNewRound(Piece[] puzzle){
        roundsPlayed++;
        this.puzzle = Arrays.copyOf(puzzle,puzzleLength);
        board = new BoardState(puzzle, maxGuesses);

        roundBeingPlayed=!roundBeingPlayed;
      }

    public int[] play(Piece[] guess){
        int[] play = new int[2];
        if(roundBeingPlayed() && isValid(guess)){
            play[0] = board.colorPosMatch(board.guesses());
            play[1] = board.onlyColorMatches(board.guesses());
            points[puzzleMaster() - 1]++;
            if(isEqual(puzzle,guess) == true){
                points[guessingPlayer() - 1]++;
                startNewRound(puzzle);
            }
            else
            if( board.guesses() > maxGuesses){

                points[puzzleMaster() - 1]++;
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
            if(guessingPlayer == 1){
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
                    if(guessingPlayer == 1 && isEqual(puzzle,guess)){
                        sb.append("Player1 guessed puzzle in " + board.guesses() + " attempts. \n");
                    }
                    else if(guessingPlayer == 2 && isEqual(puzzle,guess)){
                        sb.append("Player2 guessed puzzle in " + board.guesses() + " attempts. \n");
                    }
                    sb.append("Score: " + points[0] + " - " + points[1] + "\n");
                    sb.append(board.toString());
                }
            }
        return sb.toString();
    }

}

