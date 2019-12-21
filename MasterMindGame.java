import java.util.Arrays;

/**
 * This class verifies the validity of the guesses as well as comparing them to the puzzle aka it plays the game
 * @author Daniel Levandovschi 54412
 * @author Rodrigo Queiroga 54978
 */
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


    
    /** 
     * Constructs a MasterMindGame object
     * @param numberRounds   numberr of rounds
     * @param puzzleLength the length of the puzzle
     * @param maxGuesses number of maxguesses
     * @requires {@code numberRounds≥2 && numberRounds%2==0 && puzzleLength>1 && maxGuesses≥1;}
     */
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
    
    /** 
     * Returns the puzzle Length
     * @return puzzleLength
     */
    public int puzzleLength(){
        return puzzleLength;
    }

    
    /** 
     * Returns the maximum number of guesses
     * @return maxguesses
     */
    public int maxGuesses(){
        return maxGuesses;
    }

    
    /** 
     * Returns th number of rounds played
     * @return roundsPlayed
     */
    public int roundsPlayed(){
        return roundsPlayed;
    }

    
    /** 
     * Returns the current number of rounds
     * @return int
     */
    public int numberRounds(){
        return numberRounds;
    }

    
    /** 
     * Verifies if the combination of pieces given is valid
     * @param  the combination of pieces
     * @return validity
     */
    public boolean isValid(Piece[] pieces){
        return board.isValid(pieces);
    }

    
    /** 
     * Verifies if there is a round being played
     * @return if a round is being played
     */
    public boolean roundBeingPlayed(){
        if( roundBeingPlayed ){
            roundBeingPlayed = !(board.guesses() >= maxGuesses || board.colorPosMatch(board.guesses()) == puzzleLength);
        }
        return roundBeingPlayed;
    }

    
    /** 
     * Returns the number of points for each player
     * @param player a
     * @return nr of points
     */
    public int points(int player){
        return points[player-1];
    }

    
    /** 
     * Returns which player is the guessing player
     * @requires{@code roundbeingPlayed()
     * @ensures {@code 1<= results <= 2}}
     * @return guessing player
     */
    public int guessingPlayer(){
        return guessingPlayer;
    }

    
    /** 
     * Returns which player is the puzzle master player
     *  @requires{@code roundbeingPlayed()}
     * @ensures {@code 1<= results <= 2}}
     * @return puzzle master
     */
    public int puzzleMaster(){
        return puzzleMaster;
    }

    
    /** 
     * Determins if the game is over
     * @return if the game is over or not
     */
    public boolean isOver(){
        return roundsPlayed > numberRounds;
    }

    
    /** 
     * Starts a new round
     * @param the puzzle which we need to guess
     * @requires {@code isValid(puzzle) &&  !roundBeingPlayed()  && numberMatches()>roundsPlayed() && !isOver()}
     */
    public void startNewRound(Piece[] puzzle){
        board = new BoardState(puzzle, maxGuesses);
        puzzleMaster = puzzleMaster == 1 ? 2 : 1;
        guessingPlayer = guessingPlayer == 1 ? 2 : 1;
        roundBeingPlayed = true;
        roundsPlayed++;
      }

    
    /** 
     * Starts a new game
     * @param the guess given
     * @requires {@code RoundBeingPlayed() & isValid(guess)}
     * @return the nr of position and only colors matched
     */
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

    
    /** 
     * Builds the output including the board already built by the BoardState
     * @return String
     */
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
                    sb.append("Round " + roundsPlayed +  " ended");
                }
            }

            else{
                sb.append("Round " + (roundsPlayed-1) + " of " + numberRounds + " ended. \n" );
                if(guessingPlayer == 1 && points[guessingPlayer() - 1] > points[puzzleMaster() - 1] || guessingPlayer == 2 && points[guessingPlayer() - 1] < points[puzzleMaster() - 1] ){
                    sb.append("Player 1 won");
                }
                else if(guessingPlayer == 2 && points[guessingPlayer() - 1] > points[puzzleMaster() - 1] || guessingPlayer == 1 && points[guessingPlayer() - 1] < points[puzzleMaster() - 1]){
                    sb.append("Player 2 won");
                }
                else{
                    sb.append("Player 1 and 2 drew");
                }
            }
        return sb.toString();
    }
}