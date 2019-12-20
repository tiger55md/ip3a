public enum Piece{
    Blue,
    Yellow ,
    White,
    Red,
    Orange ,
    Green ;

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
