package classesAndObjects;

public class Main {
  int x = 5;

  public static void main(String[] args) {
    Main myObj1 = new Main();  // Object 1
    Main myObj2 = new Main();  // Object 2
    System.out.println("test: " + myObj1.x);
    myObj1.x = 10;
    System.out.println(myObj1.x);
  }
}
