/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package own;

/**
 *
 * @author Vothana CHY
 */
public class Complex {
    double real;
    double img;
    public Complex(double real , double img){
        this.real = real;
        this.img = img;
    }
    
    public static void main(String[] args) {
        Complex n1 = new Complex(2.3 , 4.5),
                n2 = new Complex(3.4 , 5.0), temp;
        
        temp = add(n1, n2);
        System.out.printf("Sum = %.1f + %.1fi" , temp.real , temp.img);
        System.out.println();
        System.out.println("n1 = " + n1.getClass().getSimpleName());
    }
    
    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static Complex add(Complex n1, Complex n2){
        Complex temp = new Complex(0.0 , 0.0);
        
        temp.real = n1.real + n2.real;
        temp.img = n1.img + n2.img;
        
        return temp;
    }
     
}
