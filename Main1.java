public class Main1{
    private final static Piece[] validGuess = new Piece[] {Piece.Green, Piece.White,
        Piece.Green, Piece.White};
private final static Piece[] validPuzzle = new Piece[] {Piece.Red, Piece.Green,
        Piece.Green, Piece.White};
private final static Piece[] invalidGuess = new Piece[] {Piece.Red, Piece.Green,
        Piece.Green, Piece.White, Piece.White};
    public static void main(String[] args){
        MasterMindGame game = new MasterMindGame(4,4,10);
        game.startNewRound(validPuzzle);
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
        game.play(validGuess);
        System.out.println(game.toString());
    }
}