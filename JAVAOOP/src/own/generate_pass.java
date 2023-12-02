package own;
import java.util.Random;
import java.util.Scanner;
/**
 * @author Vothana CHY
 */
public class generate_pass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        
        int[] numbers = {1,2,3,4,5,6,7,8,9,0};
        char[] chars = {'a','b','c','d','e','f','g',
                        'h','i','j','k','l','o','p',
                        'q','r','s','t','u','v','w',
                        'x','y','z'};
        char[] charsA = {'A','B','C','D','E','F','G',
                        'H','I','J','K','L','O','P',
                        'Q','R','S','T','U','V','W',
                        'X','Y','Z'};
        char[] symbole = {'~','!','@','#','$','%','^','&','*'};
        
        System.out.print("How many digits you want to generate ? (4,8,9,12,32) : ");
        
        int op = input.nextInt();
        int n ;
        if ( op == 4)
            n = 1;
        else if ( op == 8)
            n = 2;
        else if ( op == 9)
            n = 3;
        else if ( op == 12)
            n = 3;
        else  if ( op == 32 )
            n = 8;
        else 
            n = 0;
        
        String result = "";
        String des = "";
        if ( n != 0){
            for ( int i=0 ; i<n ; i++){
                String a = Integer.toString(rand.nextInt(numbers.length));
                String b = Character.toString(chars[rand.nextInt(chars.length)]);
                String c = Character.toString(symbole[rand.nextInt(symbole.length)]);
                String d = Character.toString(charsA[rand.nextInt(charsA.length)]);
                result += a + b + c + d;
            }
            des = "Your Password is : ";
        }else result = "Please choose valid option ... ";
        
        //we need 9 digit only so we need minus lastchild
        if ( op==9){
            char[] last = result.toCharArray();
            char[] news = new char[12];
            for ( int j = 0 ; j<last.length-3 ; j++){
                news[j] = last[j];
            }
            result = String.valueOf(news);
        }
        
        
        System.out.println("===========================");
        System.out.println(des + result);
         
    }
   
}
