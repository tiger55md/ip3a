public class ComputerPlayer{
    private int puzzleLength;
    private int maxGuesses;
    private long seed;

    public ComputerPlayer(int puzzleLength, int maxGuesses){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
    }

    public ComputerPlayer(int puzzleLength, int maxGuesses, long seed){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
        this.seed = seed;
    }

    public Piece[] firstGuess{
        
    }
}
