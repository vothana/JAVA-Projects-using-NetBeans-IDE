package Chapter_3;
import java.util.Scanner;

public class Program_Challeng4 {
	private static Scanner cin;
	public static void main(String[] args) {
		cin = new Scanner(System.in);
		float weight;
		System.out.print("Object's mass (kg) : ");
		weight = cin.nextFloat();
		
		weight *= 9.8;
		
		if ( weight < 10 )
			System.out.println("Object's mass is too light !!!");
		if ( weight > 1000 )
			System.out.println("Object's mass is too heavy !!! ");

		System.out.println("Hello From team mate !!");
	}
}
