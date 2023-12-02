package FirstSlide;
/**
 * @author Vothana CHY
 */
public class Object_Pratice {

    public static void main(String[] args) {
        Parent obj = new Parent(); // Create an object
        System.out.println("First Object value = " + obj.x);

        Parent.speed(80);  //has static
        obj.today("Thurday");  //no static, We must call with object

    }
}
