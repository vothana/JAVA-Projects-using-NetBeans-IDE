package FirstSlide;
/**
 * @author Vothana CHY
 */
public class Encapulation {
    private String name;
    
    Encapulation(){
        name = "Unknown";
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    
    public static void main(String[] args) {
        Encapulation obj = new Encapulation();
        System.out.println("First Name : " + obj.getName());
        obj.setName("Nhunh Chantha");
        
        System.out.println("Second Name : " + obj.getName());
    }
}
