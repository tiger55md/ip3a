/**
 * This class simulates a MasterMindGame between 2 computers
 * @author Daniel Levandovschi 54412
 * @author Rodrigo Queiroga 54978
 */

import java.util.Arrays;
public class MainTxt{
    public static void main(String[] args){
        int numberRounds = 4, puzzleLength = 4, maxGuesses = 10;
        MasterMindGame game = new MasterMindGame(numberRounds, puzzleLength, maxGuesses);
        ComputerPlayer cpu1 = new ComputerPlayer(puzzleLength, maxGuesses);
        ComputerPlayer cpu2 = new ComputerPlayer(puzzleLength, maxGuesses);
        game.startNewRound(cpu1.generatePuzzle());
        while(!game.isOver()){
            if(game.roundBeingPlayed()){
                if(game.guessingPlayer() == 1){
                    game.play(cpu1.nextGuess(0, 0));
                    System.out.println(game);
                }
                else{
                    game.play(cpu2.nextGuess(0, 0));
                    System.out.println(game);
                }
            }
            else{
                if(game.guessingPlayer() == 1){
                    game.startNewRound(cpu1.generatePuzzle());
                }
                else{
                    game.startNewRound(cpu2.generatePuzzle());
                }
            }
        }
        if(game.isOver()){
            System.out.println(game);
        }
    }
}