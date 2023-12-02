package FirstSlide;
/**
 * @author Vothana CHY
 */
class Book {
    protected String bookName;
    public void Creator(){
        System.out.println("Nhanh Kakada");
    }
}

class Inheritance extends Book  {
    public static void main(String[] args) {
        Inheritance obj = new Inheritance();
        System.out.println("");
    }
}
