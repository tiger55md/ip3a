import java.util.Arrays;

public class MasterMindGame{
    private int puzzleLength;
    private int maxGuesses;
    private int numberRounds;
    private int roundsPlayed;
    private int[] points;
    private int puzzleMaster;
    private int guessingPlayer;
    private BoardState board;
    private boolean roundBeingPlayed;


    public MasterMindGame(int numberRounds, int puzzleLength, int maxGuesses){
        this.numberRounds = numberRounds;
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        puzzleMaster = 1;
        guessingPlayer = 2;
        roundsPlayed = 0;
        points = new int[]{0,0};
        roundBeingPlayed=false;
    }

    private boolean isEqual(Piece[] puzzle, Piece[] guess){
        boolean isEqual = true;
        for(int i = 0; i < puzzle.length && isEqual; i++){
            if(puzzle[i] != guess[i]){
                isEqual = false;
            }
        }
        return isEqual;
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
        if( roundBeingPlayed ){
            roundBeingPlayed = !(board.guesses() >= maxGuesses || board.colorPosMatch(board.guesses()) == puzzleLength);
        }
        return roundBeingPlayed;
    }

    public int points(int player){
        return points[player-1];
    }

    public int guessingPlayer(){
        return guessingPlayer;
    }

    public int puzzleMaster(){
        return puzzleMaster;
    }

    public boolean isOver(){
        return roundsPlayed > numberRounds;
    }

    public void startNewRound(Piece[] puzzle){
        points[puzzleMaster() - 1] =0;
        points[guessingPlayer() - 1] =0;
        board = new BoardState(puzzle, maxGuesses);
        puzzleMaster = puzzleMaster == 1 ? 2 : 1;
        guessingPlayer = guessingPlayer == 1 ? 2 : 1;
        roundBeingPlayed = true;
        roundsPlayed++;
      }

    public int[] play(Piece[] guess){
        int[] play = new int[2];
        board.insertGuess(guess);
        play[0] = board.colorPosMatch(board.guesses());
        play[1] = board.onlyColorMatches(board.guesses());
        if(board.colorPosMatch(board.guesses()) != puzzleLength){
            if( board.guesses() < maxGuesses ){
                points[puzzleMaster() -1]++;
            } else {
                points[puzzleMaster() -1] += 2;
            }
        }
        return play;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
            sb.append("__________________________________________________________");
            sb.append("\n");
            if(!isOver()){
                sb.append("Round " + (roundsPlayed) + " of " + numberRounds + " being played. " );
                if(guessingPlayer() == 1){
                    sb.append("Player1 guessing. \n");
                }
                else{
                    sb.append("Player2 guessing. \n");
                }
                sb.append("Score: " + points[0] + " - " + points[1]+ "   " + " Remaining guesses: " + (maxGuesses - board.guesses()) + "\n");
                sb.append(board.toString());
                if( board.guesses() >=  maxGuesses || board.colorPosMatch(board.guesses()) == puzzleLength){
                    sb.append("Round end");
                }
            }

            else{
                sb.append("Round " + roundsPlayed + " of " + numberRounds + " ended. \n" );
                if(guessingPlayer == 1){
                    sb.append("Player 1 won");
                }
                else{
                    sb.append("Player 2 won");
                }
            }
        return sb.toString();
    }
}

