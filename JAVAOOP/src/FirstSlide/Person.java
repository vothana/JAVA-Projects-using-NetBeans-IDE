package FirstSlide;

import java.util.Scanner;

/**
 *
 * @author Vothana CHY
 */
public class Person {
    String name;
    int age;
    Person(){
        name = "NULL";
        age = 0;
    }
    Person(int a , String n){
        name = n;
        age = a;
    }
    void Input(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Input your name : ");
        name = cin.nextLine();
        System.out.print("Input your age : ");
        age = cin.nextInt();
    }
    void Output(){
        System.out.print(name + " ");
        System.out.println(age);
    }
    
    public static void main(String[] args) {
        Person obj = new Person(24, "Chan Dara");
        System.out.println("Person 1 : " + obj.name + " Age : "+ obj.age);
        
        Person obj2 = new Person();
        obj2.Input();
        obj2.Output();
        
        
    }
}
