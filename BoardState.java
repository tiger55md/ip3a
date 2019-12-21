import java.util.Arrays;

/**
 * This class creats the board and updates it every play
 * @author Daniel Levandovschi 54412
 * @author Rodrigo Queiroga 54978
 */
public class BoardState{

    private static Piece[] puzzle;
    private static Piece[] guess;
    private int maxGuesses;
    private Piece[][] board;
    private final static int LENGTH = 4;
    private int guesses;

   
   /** 
    * Constructs a BoardState object
    * @param puzzle the puzzle we want to use
    * @param maxGuesses number of maximum guesses
    */
   public BoardState(Piece[] puzzle, int maxGuesses){
    this.maxGuesses = maxGuesses;
    if( isValid(puzzle)){
      this.puzzle= puzzle.clone();
      this.board = new Piece[maxGuesses+1][LENGTH];
      this.guesses = 0;
    }
   }

   
   /** 
    * Verifies if the combination of pieces is valid
    * @param pieces the combination of pieces
    * @return boolean
    */
   public static boolean isValid(Piece[] pieces){
    boolean isValid = true;
    int i = 0;
    if(pieces != null && pieces.length == LENGTH){
      while(isValid && i < LENGTH){
          if(pieces[i] == null ){
              isValid = false;
          }
          i++;
    }
  }
    return isValid;
}

   
   /** 
    * Returns the puzzle length
    * @return int
    */
   public int puzzleLength(){
       return puzzle.length;
   }


   
   /** 
    * Returns the max number of guesses
    * @return int
    */
   public int maxGuesses(){
       return maxGuesses;
   }


   
   /** 
    * Returns the current number of guesses
    * @return int
    */
   public int guesses(){
     return this.guesses;
   }



   
   /** 
    * Inserts the guess given by the player/computer in the board
    * @requires {@code guesses()<maxGuesses() && guess.length==puzzleLength() && isValid(guess);}
    * @param the guess
    */
   public void insertGuess(Piece[] guess){
     if(isValid(guess)){
       board[guesses] = guess.clone();
       guesses++;
     }
   }



   
   /** 
    * Verifies which colors are in the correct position
    * @param i  the number of the guess
    * @requires {@code i > 0 && i <= guesses}
    * @return int
    */
   public int colorPosMatch(int i){
    int match = 0;
    if(i != 0){
      guess = board[i-1];
    }
    else{
      guess = board[i];
    }
      for(int j = 0; j < puzzleLength(); j++){ 
        if(puzzle[j] == guess[j]){
          match++;
        }
      }

    return match;
  }



  
  /** 
   * Verifies which colors are correct but in the wrong position 
   * @param i number of the guess
   * @requires {@code i > 0 && i <= guesses}
   * @return int
   */
  public int onlyColorMatches(int i){
    int colorMatch = 0;
    if(i != 0){
      guess = board[i-1];
    }
    else{
      guess = board[i];
    }
    boolean[] puzzleBoolean = new boolean[puzzleLength()];
    for(int j = 0; j < puzzle.length; j++){ 
        if(puzzle[j] == guess[j] ){
        puzzleBoolean[j] = true;
      }
    }
    for(int j = 0; j < puzzleLength(); j++){
      boolean colorM = true;
      if(guess[j] != puzzle[j]){
        int k = 0;
        while(colorM && (k < puzzleLength())){ 
          if(guess[j] == puzzle[k] && !puzzleBoolean[k]) {
            puzzleBoolean[k] = true;
            colorMatch++;
            colorM = false;
          }
          k++;
        }
      }
    }
    return colorMatch;
  }

  
  /** 
   * Builds the board
   * @return String
   */
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("+");
    for(int j = 0; j < 2*(board[guesses].length)+5; j++){
      sb.append("-");
    }
    sb.append("+\n");
    for(int k = guesses; k > 0; k--){
      int match = colorPosMatch(k), colorMatch = onlyColorMatches(k);
      sb.append("| ");
      for(Piece n:board[k-1]){
        sb.append(n);
      }
      sb.append(" | ");
      for(int j = 0; j < match; j++){
        sb.append('*');
      }
      for(int j = 0; j < colorMatch; j++){
        sb.append('o');
      }
      for(int j = 0; j < board[k-1].length - (match + colorMatch); j++){
        sb.append(' ');
      }
      sb.append(" |");
      sb.append("\n");
      sb.append("+");
      for(int j = 0; j < (2*board[k-1].length)+5; j++){
        sb.append("-");
      }
      sb.append("+\n");
    }
    return sb.toString();
  }
}
