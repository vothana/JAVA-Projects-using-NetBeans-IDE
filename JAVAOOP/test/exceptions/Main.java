/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

public class Main {
  public static void main(String[ ] args) {
    try {
      int[] myNumbers = {1, 2, 3};
      System.out.println(myNumbers[0]);
    } catch (Exception e) {
      System.out.println("Something went wrong. Error: " + e.getMessage());
    } finally {
      System.out.println("The 'try catch' is finished.");
    }
  }
}
