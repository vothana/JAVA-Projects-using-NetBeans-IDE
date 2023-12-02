/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encapsulation;

public class Main {
  public static void main(String[] args) {
    Person myObj = new Person();
//    myObj.name = "John";  // error
//    System.out.println(myObj.name); // error 
    
    myObj.setName("John"); // Set the value of the name variable to "John"
    //System.out.println(myObj.name);
  }
}