/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

public class Main {
  // Static method
  static void myStaticMethod() {
    System.out.println("Static methods can be called without creating objects");
  }

  // Public method
  static void myPublicMethod() {
    System.out.println("Public methods must be called by creating objects");
  }
  
  // Create a fullThrottle() method
  public void fullThrottle() {
    System.out.println("The car is going as fast as it can!");
  }
  
  // Create a speed() method and add a parameter
  public void speed(int maxSpeed) {
    System.out.println("Max speed is: " + maxSpeed);
  }

  // Main method
  public static void main(String[] args) {
//    myStaticMethod(); // Call the static method
//     myPublicMethod(); //This would compile an error
    
    Main myObj = new Main(); // Create an object of Main
//    myObj.myPublicMethod(); // Call the public method on the object
//    myObj.speed(200);       // Call the speed() method
  }
}