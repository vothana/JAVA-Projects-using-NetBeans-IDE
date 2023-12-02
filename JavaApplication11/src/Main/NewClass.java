/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Vothana CHY
 */
public class NewClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Boolean invalid = false;
        Object salary ;
        do{
            System.out.print("Input Salary (Int) = ");
            salary  = input.nextInt();
            Integer x;
            try {
                x = (Integer)salary ;
            } catch(ClassCastException | InputMismatchException cce){
                System.out.println("Your input is invalid, please try again");
                invalid = true;
            }
        }while (invalid);
        
        int willBe ;
        if ( (Integer) salary >=  100 ){
            willBe = 7;
            test((Integer) salary, willBe);
        }else if ( (Integer) salary >=  50 ){
            willBe = 6;
            test((Integer) salary, willBe);
        }else if ( (Integer) salary >=  20 ){
            willBe = 5;
            test((Integer) salary, willBe);
        }else if ( (Integer) salary >=  10 ){
            willBe = 4;
            test((Integer) salary, willBe);
        }else if ( (Integer) salary >=  5 ){
            willBe = 3;
            test((Integer) salary, willBe);
        }else if ( (Integer) salary >=  2 ){
            willBe = 2;
            test((Integer) salary, willBe);
        }else{
            willBe = 1;
            test((Integer) salary, willBe);
        }
 
   
    }
    
    public static void test(int salary , int willBe){
        int[] array = null;
        int[] num = {100,50,20,10,5,2,1};
        int[] rm = null;   
//        rm[1] = salary % 100;

        for ( int i = 8-willBe ; i<=7 ; i++){
            rm[i] = salary % num[i];
            array[i] = salary / 100;
            rm[i] = rm[i] % num[i];
        }
        
        System.out.println("Salary " + salary);
        System.out.println("100$ :  " + array[1]);
        System.out.println("50$ :  " + array[2]);
        System.out.println("20$ :  " + array[3]);
        System.out.println("10$ :  " + array[4]);
        System.out.println("5$ :  " + array[5]);
        System.out.println("2$ :  " + array[6]);
        System.out.println("1$ :  " + array[7]);
    }
}
