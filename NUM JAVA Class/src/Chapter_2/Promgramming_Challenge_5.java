package Chapter_2;
import java.util.Scanner;

public class Promgramming_Challenge_5 {
	  public static void main(String[] args){
	      Scanner input =new Scanner(System.in);
	      String name, college, profession, animal_type, animal_name;
	      String  city;
	      int age;
	      System.out.print("============information=============\n");
	      System.out.print("Input your Name:");
	      	name = input.nextLine();
	      	
	      System.out.print("Input your Age:");
	      	age  = input.nextInt();
	      	input.nextLine();
	      	
	      System.out.print("Input City:");
	      	city = input.nextLine();
	      	
	      System.out.print("Input college:");
	      	college = input.nextLine();
	      	
	      System.out.print("Input Profession:");
	      	profession  = input.nextLine();
	      	
	      System.out.print("Input Animal-type:");  
	      	animal_type  = input.nextLine();
	      	
	      System.out.print("Input Animal_name:");
	      	animal_name = input.nextLine();
	        
	      input.close();
	      
	      System.out.print("\n There once was a person named " + name + 
	                       " who lived in " + city + "."  + 
	                       " At the age of\n" + age +","
	                       + name + " went to college at" + college + ".\n" 
	                       + name + " graduated and went to" +" work as a"+ profession +"." +
	                       " Then,"+ animal_type + " adopted a(n) " 
	                        + animal_name + ". They both lived happily ever after!");
	  }  
}
