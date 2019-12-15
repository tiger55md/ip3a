import java.util.Random;

    private int puzzleLength;
    private int maxGuesses;
    private long seed;
    private Piece[] pieces=Piece.values();

    public ComputerPlayer(int puzzleLength, int maxGuesses){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
    }

    public ComputerPlayer(int puzzleLength, int maxGuesses, long seed){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        this.seed = seed;
    }

    public Piece[] firstGuess(){
        Random rd = new Random();
        Piece[] firstGuess  = new Piece[puzzleLength];
        for(int i = 0; i < puzzleLength; i++){
            firstGuess[i] = pieces[rd.nextInt(puzzleLength)];
        }
        return firstGuess;
    }
}
