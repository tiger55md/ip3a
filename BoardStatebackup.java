public class BoardState{
    private Piece[] puzzle;
    private final static int maxGuesses = 10;  
    private Piece[] guess;
    private Piece[][] board;
    private int guesses = 0;

    public static boolean isValid(Piece[] pieces){
        boolean isValid = true;
        int i = 0;
        if(pieces.length == puzzle.length && puzzle.length > 1){
            while(isValid || i < pieces.length ){
                if(pieces[j] != null ){
                    isValid = false;
                }
                i++;
            }
        }else{
            isValid = false;
        }
        return isValid
    }

   public BoardState(Piece[] puzzle, int maxGuesses){
       if(isValid(puzzle) && maxGuesses > 1){
            board = new Piece[maxGuesses][2];
       }
   }

   public int puzzleLength(){
       return puzzle.length;
   }

   public int maxGuesses(){
       return maxGuesses;
   }

   public int guesses(){
       return guesses;
   }

   public void insertGuess(Piece[] guess){
       guesses++;
       if(guesses() < maxGuesses() && guess.length == puzzleLength() && isValid(guess)){
           for(int i = 0; i < guess.length; i++){
               board[guesses][i] == guess[i];
           }
       }
   }

}
