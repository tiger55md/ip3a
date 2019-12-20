import java.util.Random;

public class computerPlayer{

private int puzzleLength;
private int maxGuesses;
private long seed;
private Piece[] pieces = Piece.values();
private int piecesLength = pieces.length; 


    public computerPlayer(int puzzleLength, int maxGuesses){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
    }

    public computerPlayer(int puzzleLength, int maxGuesses, long seed){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        this.seed = seed;
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
        Random rd = new Random(this.seed);
        for(int i = 0; i < puzzleLength; i++){
            randomGen[i] = pieces[rd.nextInt(piecesLength)];
            
        }
        return randomGen.clone();
    }
}
