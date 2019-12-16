import java.util.Arrays;

public class BoardState{
    private static Piece[] puzzle;
    private final static int maxGuesses = 10;  
    private static Piece[] guess;
    private Piece[][] board;
    public int guesses = -1;


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
       if(isValid(puzzle) && maxGuesses >= 1){
            board = new Piece[maxGuesses][2];
            this.puzzle = puzzle.clone();
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
               board[guesses] = guess.clone();
       }
   }



   public int colorPosMatch(int i){
    Piece[] guess1 = board[i].clone();
    int match = 0;
    for(int j = 0; j < puzzleLength(); j++){ 
      if(puzzle[j] == guess1[j]){
        match++;
      }
    }
    return match;
  }



  public int onlyColorMatches(int i){
    Piece[] guess1 = board[i].clone();
    int colorMatch = 0;
    boolean[] puzzleBoolean = new boolean[puzzleLength()];
    for(int j = 0; j < puzzle.length; j++){ 
        if(puzzle[j] == guess1[j] ){
        puzzleBoolean[j] = true;
      }
    }
    for(int j = 0; j < puzzleLength(); j++){
      boolean colorM = true;
      if(guess1[j] != puzzle[j]){
        while(colorM && (k < puzzleLength())){ 
          if(guess1[j] == puzzle[k] && !puzzleBoolean[k]) {
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
    for(int k = guesses; k >= 0; k--){
      int match = colorPosMatch(guesses), colorMatch = onlyColorMatches(guesses);
      sb.append("| ");
      for(Piece n:board[k]){
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
      sb.append("+");
      for(int j = 0; j < (2*board[guesses].length)+5; j++){
        sb.append("-");
      }
      sb.append("+\n");
    }
    return sb.toString();
  }
}
