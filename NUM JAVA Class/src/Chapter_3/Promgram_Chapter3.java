package Chapter_3;
import java.util.Scanner;
public class Promgram_Chapter3 {
	private static Scanner cin;
	public static void main(String[] args) {
		cin = new Scanner(System.in);
		float weight, height, bmi;
		System.out.print("Your weight (kg) : ");
		weight = cin.nextFloat();
		System.out.print("Your height (m) : ");
		height = cin.nextFloat();

		weight *= 2.20462f;
		height *= 39.37f;
		
		bmi = (weight*703) / (height*height);
		
		if ( bmi >= 18.5 && bmi <= 25) 
			System.out.println("BMI = " + bmi + " Your are optimum");
		else if ( bmi < 18.5 ) 
			System.out.println("BMI = " + bmi + " Your are undewight");
		else 
			System.out.println("BMI = " + bmi + " Your are overweight");
		
	}
}
