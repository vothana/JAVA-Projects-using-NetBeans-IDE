package own;
import java.util.Scanner;
/**
 * @author Vothana CHY
 */
public class Homework {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        //Declear 
        String name;
        int work;
        float salary;
        int target = 100;
        
        //Provide employee name and work time
        System.out.print("Employee name : ");
        name = input.nextLine();
        System.out.print("Employee work time : ");
        work = input.nextInt();
        input.nextLine();
        
        if ( work == target )
            salary = 50;
        else if ( work > target && work <= 120)
            salary = 50 + (work - target)*1.25f;
        else if ( work > 120 )
            salary = 50 + (120 - target)*1.25f + (work - 120)*1.5f;
        else
            salary = 50 - (target - work)*0.5f;
        
        int overtime = 0;
        String lose = "";
        if ( (work - target) >= 0 )
            overtime  = work - target;
        else
            lose = "\n| You are lack : " + (-(work - target)) + "hours";
        
        System.out.println("\n ================================");
        System.out.println("| Employee Name : " + name);
        System.out.println("| Time Target : " + target + "hours");
        System.out.println("| Work Time : " + work + "hours");
        System.out.println("| Base Salary : 50$" );
        System.out.println("| Overtime : " + overtime + "hours" + lose);
        System.out.println("| Salary : " + salary + "$") ;
        System.out.println("|================================");
    }
}
