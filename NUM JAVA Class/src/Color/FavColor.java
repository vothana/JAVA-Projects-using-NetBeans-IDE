package Color;
public class FavColor {
    public static void Color(int color){
        switch (color){
            case 0:{
                //RESET
                System.out.print("\033[0m");
            }break;
            case 1:{
                //RED
                System.out.print("\033[1;31m");
            }break;
            case 2:{
                //GREEN
                System.out.print("\033[1;32m");
            }break;
            case 3:{
                //YELLOW
                System.out.print("\033[1;33m");
            }break;
            case 4:{
                //MAGENTA
                System.out.print("\033[1;35m");
            }break;
            case 5:{
                //CYAN
                System.out.print("\033[1;36m");
            }break;
            default:{
                System.out.print(" <<Color Not Found>> ");
            }
        }
    }
}
