
import java.util.Random;

public class ComputerPlayer{

private int puzzleLength;
private int maxGuesses;
private long seed;
private Random rd;

    public ComputerPlayer(int puzzleLength, int maxGuesses){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        rd = new Random();
        
    }

    public ComputerPlayer(int puzzleLength, int maxGuesses, long seed){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        this.seed = seed;
        rd = new Random(seed);
    }

    public Piece[] firstGuess(){
        Piece[] firstGuess = new Piece[puzzleLength];
        Piece[] pieces = Piece.values();
        for(int i = 0; i < puzzleLength; i++){
            firstGuess[i] = pieces[rd.nextInt(pieces.length)];
        }
        return firstGuess;
    }  


    public Piece[] nextGuess(int colorPosMatch, int onlyColorMatch){
        Piece[] nextGuess = new Piece[puzzleLength];
        Piece[] pieces = Piece.values();
        for(int i = 0; i < puzzleLength; i++){
            nextGuess[i] = pieces[rd.nextInt(pieces.length)];
        }
        return nextGuess;
    }

    public Piece[] generatePuzzle(){
        Piece[] puzzle = new Piece[puzzleLength];
        Piece[] pieces = Piece.values();
        for(int i = 0; i < puzzleLength; i++){
            puzzle[i] = pieces[rd.nextInt(pieces.length)];
        }
        return puzzle;
    }
}
