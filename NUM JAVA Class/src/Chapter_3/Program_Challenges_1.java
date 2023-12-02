package Chapter_3;

import java.util.Scanner;

public class Program_Challenges_1 {
     private static Scanner keyboard;

     public static void main(String args[]) {
          int userNum;
          keyboard = new Scanner(System.in);
          System.out.println("Please input number 1-10 : ");
          userNum = keyboard.nextInt();
          switch (userNum) {
               case 1:
                    System.out.println("1 = I");
                    break;
               case 2:
                    System.out.println("2 = II");
                    break;
               case 3:
                    System.out.println("3 = III");
                    break;
               case 4:
                    System.out.println("4 = IV");
                    break;
               case 5:
                    System.out.println("5 = V");
                    break;
               case 6:
                    System.out.println("6 = VI");
                    break;
               case 7:
                    System.out.println("7 = VII");
                    break;
               case 8:
                    System.out.println("8 = VIII");
                    break;
               case 9:
                    System.out.println("9 = IX");
                    break;
               case 10:
                    System.out.println("10 = X");
                    break;
               default:
                    System.out.println("Wrong number !!!");
          }
     }

}
