
import java.lang.reflect.Array;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vothana CHY
 */
public class NewClass1 {
    public static void main(String[] args) {
        
        //I
        String ODD="", EVEN = "";
        for( int i = 1 ; i <= 100 ; i++){
            if ( i % 2 != 0 )
                ODD = ODD + i + ",";
            else
                EVEN = EVEN + i + ",";
        }
        EVEN = RemovelastChild(EVEN);
        ODD  = RemovelastChild(ODD);
        System.err.println("ODD : " + ODD +"\n" + "EVEN : " + EVEN);
         
        //II
//        for ( int i = 100 ; i<= 202 ; i++){
//            if ( i % 3 == 0)
//                System.out.println(" => \"ALL\" " + i +"/3=" + (i/3) + " and Remainder : " + (i%3));
//            
//            if ( i % 5 == 0)
//                 System.out.println(" => \"WEB\" " + i +"/5=" + (i/5) + " and Remainder : " + (i%5));
//            
//            if ( i % 3 == 0 && i % 5 ==0 )
//                System.out.println(" ==> \"ALLWEB\" " + i +"/3=" + (i/3) + " and Remainder : " + (i%3) + " and " + i +"/5=" + (i/5) + " and Remainder : " + (i%5));
//            else
//                System.out.println( "=" + i + " Because : " + i +"/3=" + (i/3) + " and Remainder : " + (i%3) + " and " + i +"/5=" + (i/5) + " and Remainder : " + (i%5));
//        }
        
        //III
          int[] detaset = {121,-372,427,588,448,656,-151,845,982,242};
          int temp = 0;
//          Not this
//          for ( int i = 0 ; i <= detaset.length ; i++){
//              for ( int j = i ; j <= detaset.length ; j++){
//                  
//                  if (i < detaset.length - 1){
//                      if ( detaset[i] > detaset[i+1]){
//                      temp = detaset[i+1];
//                      detaset[i+1] = detaset[i];
//                      detaset[i] = temp;
//                    }
//                  }
//   
//              }
//          } 
            for (int i = 0 ; i < detaset.length ; i++){
                for ( int j = i + 1 ; j < detaset.length ; j++){
                    if(detaset[i] > detaset[j] ){
                        temp = detaset[i];
                        detaset[i] = detaset[j];
                        detaset[j] = temp;
                    }
                }
            }
          
          String num = "";
          for ( int deta : detaset){
              num = num + deta + ",";
          }
          
          num = RemovelastChild(num);
          
          System.out.println("ASC = " + num);
          
        //IV
        //Select * From 
        //NOT This SELECT * FROM users WHERE Like Address = "*Avenue";
        //SELECT * FROM users WHERE Address LIKE '*Avenue';
         
    }
    
    public static String RemovelastChild(String string){
        StringBuilder sb = new StringBuilder(string);
        sb.deleteCharAt(sb.length()-1); 
        
        return sb.toString();
    }
    
}
