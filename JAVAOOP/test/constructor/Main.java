/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constructor;

import classesAndObjects.Second;

public class Main {
  int modelYear;
  String modelName = "test";
  
  

  public Main(int year, String name) {
    modelYear = year;
    modelName = name;
  }

  public static void main(String[] args) {
    
    Second test = new Second();
   // System.out.println(test.x);
      
//    Main myCar = new Main(1969, "Mustang");
//    System.out.println(myCar.modelYear + " " + myCar.modelName);
//    Main myCar2 = new Main(1970, "Camero");
//    System.out.println(myCar2.modelYear + " " + myCar2.modelName);
  }
}

