import java.util.Arrays;

public class BoardState{
    private Piece[] puzzle;
    private final static int maxGuesses = 10;  
    private Piece[] guess;
    private Piece[][] board;
    private int guesses = 0;

    public static boolean isValid(Piece[] pieces){
        boolean isValid = true;
        int i = 0;
        if(pieces.length == puzzleLength() && puzzleLength() > 1){
            while(isValid || i < puzzleLength() ){
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

   public static int colorPosMatch(int i){
    int match = 0;
    for(int i = 0; i < puzzleLength(); i++){ 
      if(puzzle[i] == guess[i]){
        match++;
      }
    }
    return match;
  }

  public static int onlyColorMatches(int i){
    int colorMatch = 0;
    int[] puzzleCpy = Arrays.copyOf(puzzle, puzzleLength());
    for(int j = 0; j < puzzle.length; j++){ 
        if(puzzle[i] == guess[i] ){
        puzzleCpy[i] = -400; 
      }
    }
    for(int j = 0; j < puzzleLength(); j++){
      boolean colorM = true;
      int j = 0;
      if(guess[i] != puzzle[i]){
        while(colorM && (j < puzzleLength())){ 
          if(guess[i] == puzzleCpy[k]){
            puzzleCpy[j] = -342;
            colorMatch++;
            colorM = false;
          }
          j++;
        }
      }
    }
    return colorMatch;
  }

  public String toString(){
    System.out.print("+");
    for(int j = 0; j < (2*board[i].length)+5; j++){
      System.out.print("-");
    }
    System.out.print("+\n");
    for(int k = i; k >= 0; k--){
      int match = colorPosMatch(guesses), colorMatch = onlyColorMatches(guesses);
      System.out.print("| ");
      for(int n:board[k]){
        System.out.print(n);
      }
      System.out.print(" | ");
      for(int j = 0; j < match; j++){
        System.out.print('o');
      }
      for(int j = 0; j < colorMatch; j++){
        System.out.print('*');
      }
      for(int j = 0; j < board[i].length - (match + colorMatch); j++){
         System.out.print(' ');
      }
      System.out.println(" |");
      System.out.print("+");
      for(int j = 0; j < (2*board[i].length)+5; j++){
        System.out.print("-");
      }
      System.out.print("+\n");
    }
  }

}