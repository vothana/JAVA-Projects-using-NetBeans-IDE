package Chapter_3;

import java.util.Scanner;

public class Program_Challenge2 {
	private static Scanner keyboard;
	
	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		int day,month,year;
		int multi;
		System.out.print("Input day : ");
		day = keyboard.nextInt();
		System.out.print("Input month : ");
		month = keyboard.nextInt();
		System.out.print("Input year : ");
		year = keyboard.nextInt();
		
		multi = day * month ;
		
		if ( multi == year ) {
			System.out.print(" Date is magic !!!");
		}else {
			System.out.print(" Date is not magic !!!");
			
		}
		
	}
}
