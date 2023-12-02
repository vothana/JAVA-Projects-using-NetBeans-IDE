package Chapter_2;
import java.util.Scanner;

public class Promgramming_Challenge_4 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		float meal_price;
		float tax_, tip_, mount_, total_;
		
		System.out.println("\n ==========Meal Price==========   \n");
		System.out.print("Please input meal price ($) = ");//សារប្រាប់ពីគោលបំណង
		meal_price = input.nextFloat();//រងចាំការវាយបញ្ចូលទិន្នន័យ
		
		input.close(); //បញ្ចប់ ឬឈប់ទទួលការវាយបញ្ចូល
		
		tax_   = (meal_price*6.75F)/100; //F តំណាងថាចំនួនទស្សភាគ
		tip_   = (meal_price*15)/100;
		
		total_ = tax_ + tip_ + meal_price;
		mount_ = tax_ + tip_;
		
		String tax   = String.format("%.2f", tax_);
		String tip   = String.format("%.2f", tip_);
		String mount = String.format("%.2f", mount_);
		String total = String.format("%.2f", total_);
		
		System.out.println( "\n ===========Invoice===========    \n" +
							"\n Meal charge  = " + meal_price + " $" +
							"\n Tax (6.75%)  = " + tax        + " $" +
							"\n Tip (15.00%) = " + tip 		  + " $" +
							"\n ===> Mount   = " + mount 	  + " $" +
							"\n ===> Total   = " + total 	  + " $"   );
	}
}
