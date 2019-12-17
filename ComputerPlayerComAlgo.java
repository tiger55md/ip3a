import java.util.Random;
import java.util.List;

public class ComputerPlayer{

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

  /* public Piece[] firstGuess(){
        Piece[][] comb = possibleComb();
        Random rd = new Random();
        Piece[] firstGuess  = new Piece[puzzleLength];
        for(int i = 0; i < puzzleLength; i++){
            firstGuess[i] = 
        }
    return firstGuess;
    } */  


   /* public Piece[] nextGuess(int colorPosMatch, int onlyColorMatch){
        Piece[][] comb = possibleComb();
        Piece[] nextGuess = 
 
    } */

    private String[] possibleComb(){
        String[] comb = new String[2196];
        for(int j = 0; j < comb.length; j++){
            for (int d1 = 0; d1 < pieces.length; d1++){
                for (int d2 = 0; d2 < pieces.length; d2++){
                    for (int d3 = 0; d3 < pieces.length; d3++){
                        for (int d4 = 0; d4 < pieces.length; d4++){
                            if (d1 != d2 && d1 != d3 && d1 != d4 && d2 != d3 && d2 != d4 && d3 != d4){                        
                                    comb[j] = pieces[d1].toString() + pieces[d2].toString() + pieces[d3].toString() + pieces[d4].toString();
                        }
                    }
                }
            }
        }
    }
    return comb;
}
}
