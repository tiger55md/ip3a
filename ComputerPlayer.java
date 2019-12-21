import java.util.Random;

public class ComputerPlayer{

private int puzzleLength;
private int maxGuesses;
private long seed;
private Piece[] pieces = Piece.values();
private Random rd;



    public ComputerPlayer(int puzzleLength, int maxGuesses){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        rd = new Random(System.nanoTime());
        
    }

    public ComputerPlayer(int puzzleLength, int maxGuesses, long seed){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        this.seed = seed;
        rd = new Random(seed);
    }

    public Piece[] firstGuess(){
        Piece[] firstGuess = generator();
        return firstGuess;
    }  


    public Piece[] nextGuess(int colorPosMatch, int onlyColorMatch){
        Piece[] nextGuess = generator();
        return nextGuess;
    }

    public Piece[] generatePuzzle(){
        Piece[] puzzle = generator();
        return puzzle;
    }

    private Piece[] generator(){
        Piece[] randomGen = new Piece[puzzleLength];
        for(int i = 0; i < puzzleLength; i++){
            randomGen[i] = pieces[rd.nextInt(pieces.length)];
            
        }
        return randomGen.clone();
    }
}
