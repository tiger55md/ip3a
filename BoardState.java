import java.util.Arrays;

public class BoardState{
    private static Piece[] puzzle;
    private static Piece[] guess;
    private int maxGuesses;
    private Piece[][] board;
    public int guesses = 0;

   public BoardState(Piece[] puzzle, int maxGuesses){
    this.maxGuesses = maxGuesses;
    this.puzzle = puzzle.clone();
    this.board = new Piece[maxGuesses][4];
   }

   public static boolean isValid(Piece[] pieces){
    boolean isValid = true;
    int i = 0;
    if(pieces != null && pieces.length == 4){
      while(isValid && i < pieces.length ){
          if(pieces[i] == null ){
              isValid = false;
          }
          i++;
      }
    }
    else{
      isValid = false;
    }
    return isValid;
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
       board[guesses] = guess.clone();
       this.guess = guess.clone();
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

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("+");
    for(int j = 0; j < (2*board[guesses].length)+5; j++){
      sb.append("-");
    }
    sb.append("+\n");
    for(int k = guesses; k >= 2; k--){
      int match = colorPosMatch(guesses), colorMatch = onlyColorMatches(guesses);
      sb.append("| ");
      for(Piece n:board[guesses]){
        sb.append(n);
      }
      sb.append(" | ");
      for(int j = 0; j < match; j++){
        sb.append('*');
      }
      for(int j = 0; j < colorMatch; j++){
        sb.append('o');
      }
      for(int j = 0; j < board[guesses].length - (match + colorMatch); j++){
        sb.append(' ');
      }
      sb.append(" |");
      sb.append("\n");
      sb.append("+");
      for(int j = 0; j < (2*board[guesses].length)+5; j++){
        sb.append("-");
      }
      sb.append("+\n");
    }
    return sb.toString();
  }
}
