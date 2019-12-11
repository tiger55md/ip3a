import java.util.Arrays;

public class BoardState{
    private static Piece[] puzzle;
    private final static int maxGuesses = 10;  
    private static Piece[] guess;
    private Piece[][] board;
    private int guesses = -1;

    public static boolean isValid(Piece[] pieces){
        
        boolean isValid = true;
        int i = 0;
        if(pieces.length == puzzle.length && puzzle.length > 1){
            while(isValid || i < puzzle.length ){
                if(pieces[i] != null ){
                    isValid = false;
                }
                i++;
            }
        }else{
            isValid = false;
        }
        return isValid;
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
               board[guesses] = guess;
           }
       }
   }

   public int colorPosMatch(int i){
    int match = 0;
    for(int j = 0; j < puzzleLength(); j++){ 
      if(puzzle[j] == guess[j]){
        match++;
      }
    }
    return match;
  }

  public int onlyColorMatches(int i){
    int colorMatch = 0;
    boolean[] puzzleBoolean = new boolean[puzzleLength()];
    for(int j = 0; j < puzzle.length; j++){ 
        if(puzzle[i] == guess[i] ){
        puzzleBoolean[i] = true;
      }
    }
    for(int j = 0; j < puzzleLength(); j++){
      boolean colorM = true;
      if(guess[i] != puzzle[i]){
        while(colorM && (j < puzzleLength())){ 
          if(guess[i] == puzzle[j] && !puzzleBoolean[j]) {
            puzzleBoolean[j] = true;
            colorMatch++;
            colorM = false;
          }
        }
      }
    }
    return colorMatch;
  }

  public String toString(){
    System.out.print("+");
    for(int j = 0; j < (2*board[guesses].length)+5; j++){
      System.out.print("-");
    }
    System.out.print("+\n");
    for(int k = guesses; k >= 0; k--){
      int match = colorPosMatch(guesses), colorMatch = onlyColorMatches(guesses);
      System.out.print("| ");
      for(Piece n:board[k]){
        System.out.print(n);
      }
      System.out.print(" | ");
      for(int j = 0; j < match; j++){
        System.out.print('o');
      }
      for(int j = 0; j < colorMatch; j++){
        System.out.print('*');
      }
      for(int j = 0; j < board[guesses].length - (match + colorMatch); j++){
         System.out.print(' ');
      }
      System.out.println(" |");
      System.out.print("+");
      for(int j = 0; j < (2*board[guesses].length)+5; j++){
        System.out.print("-");
      }
      System.out.print("+\n");
    }
  }

}
