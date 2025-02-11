import java.util.Random;

/**
 * This class creats a computer player which is going to play the MasterMind game
 * @author Daniel Levandovschi 54412
 * @author Rodrigo Queiroga 54978
 */
public class ComputerPlayer{

private int puzzleLength;
private int maxGuesses;
private long seed;
private Random rd;
private Piece[] pieces;

    
    /** 
     * Creats a ComputerPlayer object for guessing that doesnt use a seed to generate a puzzle
     * @param puzzleLength the length of the puzzle
     * @param maxGuesses  number of max guesses
     */
    public ComputerPlayer(int puzzleLength, int maxGuesses){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        rd = new Random();
        pieces = Piece.values();
        
    }

    
    /**
     * Creats a ComputerPlayer object for guessing that uses a seed to generate a puzzle
     * @param puzzleLength the length of the puzzle
     * @param maxGuesses number of max guesses
     * @param seed the seed of the puzzle
     */
    public ComputerPlayer(int puzzleLength, int maxGuesses, long seed){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        this.seed = seed;
        rd = new Random(seed);
        pieces = Piece.values();
    }

    
    /** 
     * Makes a random first guess
     * @return the firstGuess of the computerplayer
     */
    public Piece[] firstGuess(){
        Piece[] firstGuess = new Piece[puzzleLength];
        for(int i = 0; i < puzzleLength; i++){
            firstGuess[i] = pieces[rd.nextInt(pieces.length)];
        }
        return firstGuess;
    }  


    
    /** 
     * Makes random guesses after the first one
     * @param colorPosMatch the number of positions correct
     * @param onlyColorMatch the number of colors correct but in the wrong
     * @requires {@code colorPosMatch≥0 && onlyColorMatch≥0}
     * @return all the guesses made after the firstguess of the computerplayer
     */
    public Piece[] nextGuess(int colorPosMatch, int onlyColorMatch){
        Piece[] nextGuess = new Piece[puzzleLength];
        for(int i = 0; i < puzzleLength; i++){
            nextGuess[i] = pieces[rd.nextInt(pieces.length)];
        }
        return nextGuess;
    }

    
    /** 
     * Generates a random puzzle
     * @return a puzzle generated by the computer player
     */
    public Piece[] generatePuzzle(){
        Piece[] puzzle = new Piece[puzzleLength];
        for(int i = 0; i < puzzleLength; i++){
            puzzle[i] = pieces[rd.nextInt(pieces.length)];
        }
        return puzzle;
    }
}