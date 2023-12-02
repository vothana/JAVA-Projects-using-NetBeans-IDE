/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package own;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Vothana CHY
 */
public class LoanCalculator {
    public static void main(String[] args) {
        int n = 0;
        do{
            Scanner sc = new Scanner(System.in);
            System.out.print("Customer's Name : "); String name = sc.nextLine();
            System.out.print("Phone Number : "); String phone = sc.nextLine(); sc.nextLine();
            System.out.print("Loan Amount($) : "); double amount = sc.nextDouble();
            System.out.print("Amount of Month : "); int month = sc.nextInt();
            System.out.print("Interest Rate : "); double rate = sc.nextDouble();            
            
            name = "KaaKA Long";
            phone = "010 826 027";
            amount = 1200;
            month = 12;
            rate = 3;
            
            int rowNo;
            String PayDate;
            double BeginBalance, Principle, Interest, Payment, EndBalance;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            for( int i=0 ; i<=month ; i++){
                BeginBalance = amount;
                Interest = amount;
                DateTimeFormatter dtfM = DateTimeFormatter.ofPattern("MM");
                String CurrentMonth = dtfM.format(now);
                PayDate = dtf.format(now.plusMonths(Integer.parseInt(CurrentMonth) + i));
            }
            
        }while(n > 0);

    }
    
}
