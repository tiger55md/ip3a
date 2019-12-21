/**
 * Enumerates the multiple colors the combinations can have
 * @author Daniel Levandovschi 54412
 * @author Rodrigo Queiroga 54978
 */
public enum Piece{
    Blue,
    Yellow,
    White,
    Red,
    Orange ,
    Green ;
    /**
     * Converts the chosen color into a letter so it can be displayed that way 
     * @return String 
     */
    public String toString(){
        String initial = " ";
        switch(this){
        case Blue :
            initial = "B";
            break;
        case Yellow :
            initial = "Y";
            break;
        case Red :
            initial = "R";
            break;
        case Orange :
            initial = "O";
            break;
        case White :
            initial = "W";
            break;
        case Green :
            initial = "G";
            break;
        }
        return initial;
    }
}