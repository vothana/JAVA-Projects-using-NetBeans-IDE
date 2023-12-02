package Chapter_3;
import java.util.Scanner;
public class Program_Challenge5 {
	public static Scanner cin;
	public static void main(String[] args) {
		cin = new Scanner(System.in);
		
		int sec,min,hour,day;
		int day_rm,hour_rm,min_rm;
		int _case = 0;
		
		System.out.print("Input second as your choice (s) : ");
		sec = cin.nextInt();
		
		if ( sec < 0) {
			System.out.println("Error");
		}
		else {
			if ( sec >= 60 && sec < 3600)
				_case = 1;
			else if ( sec >= 3600 && sec < 86400)
				_case = 2;
			else 
				_case = 3;
		}
		System.out.println("Case: " + _case);
		
		switch(_case) {
			case 1:{
				
				min = sec/60;
				min_rm = sec%60;
				
				sec = min_rm;
				
				System.out.println("Time : "  + min + " Minutes   " + sec + " Seconds   ");
			}break;
			case 2:{
				
				hour = sec/3600;
				hour_rm = sec%3600;
				
				min = hour_rm/60;
				min_rm = hour_rm%60;
				
				sec = min_rm;
				
				System.out.println("Time : "  + hour + " Hours   " + min + " Minutes   " + sec + " Seconds   ");
			}break;
			case 3:{
				
				day = sec/86400;
				day_rm = sec%86400;
				
				hour = day_rm/3600;
				hour_rm = day_rm%3600;
				
				min = hour_rm/60;
				min_rm = hour_rm%60;
				
				sec = min_rm;
				
				System.out.println("Time : " + day + " Days   " + hour + " Hours   " + min + " Minutes   " + sec + " Seconds   ");
			}
		}
		

	}

}
