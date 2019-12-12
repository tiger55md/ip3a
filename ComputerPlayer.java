public class ComputerPlayer{
    private int puzzleLength = puzzleLength();
    private int maxGuesses = maxGuesses();
    private long seed = 7;

    public ComputerPlayer(int puzzleLength, int maxGuesses){
        this.puzzleLength = puzzleLength;
        this.maxGuesses = maxGuesses;
    }
}