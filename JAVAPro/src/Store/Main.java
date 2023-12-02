package Store;

import static Store.Main.ProductsArray;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Vothana Chy
 */
public class Main {
    
    private static File FilePath;
    private static String path = "C:\\Store";
    private static final String fileName = "Products.txt";
    
    public static void main(String[] args) {
        FilePath = new File( path + "\\" + fileName);
        if ( !FilePath.exists()) createFolder(path, fileName);
        int op;
        do {
            op = manu();
            if( op>0 && op<=9 ) operation(op); else wrongOp();
        }while(op<9);
    }
    //manu
    public static int manu() {
      Scanner sc = new Scanner(System.in);
      System.out.println("\n==============[ M E N U ]==============");
      System.out.print("""
                       [1]. Add New Product       
                       [2]. Show All Products        
                       [3]. Update a Product        
                       [4]. Delete a Product         
                       [5]. Search a Product         
                       [6]. Sort Products by Ascending           
                       [7]. Sort Products by Discending
                       [8]. Sell Product(s)
                       [9]. Exit Program     
                       """);
      System.out.print(">>> Please Choose an option [ 1-9 ] : ");
      return sc.nextInt();
    }
    //
    public static void operation(int op) {
        Scanner sc = new Scanner(System.in);
        switch(op) {
            case 1:{
                System.out.println("\n========[ ADD NEW PRODUCT ]========");
                input(false);
            }break;
            case 2:{
                System.out.println("\n========[ SHOW ALL PRODUCTS ]========");
                showAllProducts();
            }break;
            case 3:{
                System.out.println("\n========[ UPDATE A PRODUCT ]========");
                System.out.print(">>>>Enter Product's Name Which You Want To Update : ");
                String product = sc.nextLine();
                if(searchProductList(product) != -1)
                    updateProduct(product);
                else NotFound();
            }break;
            case 4:{
                System.out.println("\n========[ DELETE A PRODUCT ]========");
                System.out.print(">>>>Enter Product's Name Which You Want To Delete :");
                String product = sc.nextLine();
                if(searchProductList(product) != -1)
                    deleteProduct(product);
                else NotFound();
            }break;
            case 5:{
                System.out.println("\n========[ SEARCH A PRODUCT ]========");
                System.out.print("Input Product's Name : ");
                String product = sc.nextLine();
                if(searchProductList(product) != -1)
                    outPut(searchProductList(product));
                else NotFound();
            }break;
            case 6:{
                System.out.println("\n========[ SORT PRODUCTS ASC ]========");
                sortArrayAscending();
                showAllProducts();
            }break;
            case 7:{
                System.out.println("\n========[ SORT PRODUCTS DISC ]========");
                sortArrayDescending();
                showAllProducts();
            }break;
            case 8:{
                System.out.println("\n========[ SELL PRODUCT(s) ]========");
                sell();
            }break;
            case 9:{
               System.exit(0);
            }break;
        }
    }
    //write down a product detail
    public static String[] input(boolean nPro) {
        Scanner sc = new Scanner(System.in);
        String newPro = (nPro)? "New " : "";
        
        String name, description, category, supplier;
        int unitsInStock;
        float unitPrice;

        System.out.print(newPro + "Name : ");
            name = sc.nextLine();
        System.out.print(newPro + "Description [25] : ");
            description = sc.nextLine();
        System.out.print(newPro +"Category : \n");
            category = category();
        System.out.print(newPro + "Unit Price : ");
            unitPrice = sc.nextFloat();
            sc.nextLine();
        System.out.print(newPro + "Units In Stock : ");
            unitsInStock = sc.nextInt();
            sc.nextLine();
        System.out.print(newPro + "Supplier : ");
            supplier = sc.nextLine();
        
        //we need to covert to string before add to string array  
        String uP = String.valueOf(unitPrice);
        String uIs = String.valueOf(unitsInStock);
        //create new array for post to update section
        String array[] = { 
                   name,
                   description,
                   category,
                   uP,
                   uIs,
                   supplier
        };
        if(nPro){
            System.out.println(">>>>Data have been success updated.");
        }else{
            addProduct(name,description,category,supplier,unitPrice,unitsInStock);
        }

       return array;
    }
    //
    private static String category(){
        Scanner sc = new Scanner(System.in);
        System.out.printf("\t[1]. Drink \n\t[2]. Beer \n\t[3]. Juice \n\t[4]. Fruit \n");
        System.out.print("\t>>>> Choose one : ");
        int choosen = sc.nextInt();
        String cT = ""; //cT = Category
        switch(choosen){
            case 1:{
                cT = "Drink";
            }break;
            case 2:{
                cT = "Beer";
            }break;
            case 3:{
                cT = "Juice";
            }break;
            case 4:{
                cT = "Fruit";
            }break;
            default:{
                wrongOp();
            }
        }
       return cT;
    }
    //write a product to the file
    private static void addProduct(
        String name, String description, 
        String category, String supplier, float unitPrice, 
        int unitsInStock) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();
        
        try {
            FileWriter fw = new FileWriter(FilePath, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(name + ":;");
            pw.print(description + ";" + category + ";" + unitPrice + ";" );
            pw.print(unitsInStock + ";" + supplier + ";");
            pw.println(dtf.format(now) + ";");
            
            System.out.println(">>>>Data have been success added.");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //update file, add new list after updated
    public static void addNewProductsList(String[] array) {
        try {
            FileWriter fw = new FileWriter(FilePath, false);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < array.length; i++) {
                pw.println(array[i]);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //show all products
    public static void showAllProducts() {
        String[] array = ProductsArray();
        Header();
        for( int i=0 ; i<array.length ; i++){
            String[] split = array[i].split(";");
            for ( int j = 0 ; j<split.length ; j++){
                if( j==0 ) split[j] = removeLast(split[j]); 
                System.out.printf("%-25s", split[j]);
            }
            br();
        }
    }
    //Product list as array
    public static String[] ProductsArray() {
        String list[] = new String[countProducts()];
        int i = 0;
        try {
            Scanner data = new Scanner(FilePath);
            while (data.hasNext()) {
                list[i] = data.nextLine();
                i++;
            }
            data.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //updating product
    public static void  updateProduct(String product) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now(); 
        
        String[] array = ProductsArray();
        
        for (int i = 0; i < array.length; i++) { 
            if (array[i].split(":")[0].equalsIgnoreCase(product)) {
                //new update
                String newU[] = input(true);
                String a = ";";
                String str="";
                for ( int j=0 ; j<newU.length ; j++){
                    if( j== 0 ) newU[j] += ":";
                    str = str + newU[j] + a;
                }
                //add Date modify
                str += dtf.format(now) + a ;
                array[i] = str;
                addNewProductsList(array);
                break;
            }
        }
    }
    //delete a product
    public static String[] deleteProduct(String product) {
        String array[] = ProductsArray();
        String[] newList;
        newList = new String[array.length - 1];
            
            for (int i = 0; i < array.length; i++) {
                if (i != array.length - 1) {
                    newList[i] = array[i];
                }
                if (array[i].split(":")[0].equalsIgnoreCase(product)) {
                    for (int j = i; j < array.length - 1; j++) {
                        array[j] = array[j + 1];
                        newList[j] = array[j];   
                    }
                    addNewProductsList(newList);
                    return newList;
                }
            }
        return null;
    }
    //search product in array list
    //this is not search product detail
    public static int searchProductList(String product) {
        String[] array = ProductsArray();
        for (int i = 0; i < array.length ; i++) {
            if (array[i].split(":")[0].equalsIgnoreCase(product)) {
                return i;
            }
        }
        return -1;
    }
    //not found message 
    static void NotFound(){
        System.out.println("Opp! Search not found...! Try again.");
        closeOrNot();
    }
    //output a product that founded
    static void outPut(int i){
        String array[] = ProductsArray();
        Header();
        String[] split = array[i].split(";");
        for ( int j = 0 ; j<split.length ; j++){
            if( j==0 ) split[j] = removeLast(split[j]); 
            System.out.printf("%-25s", split[j]);
        }
        br();
    }
    //
    public static void sortArrayAscending() {
        String[] array = ProductsArray();
        for (int i = 0; i < array.length - 1; i++) {	
            for (int j = i + 1; j < array.length; j++) { 
                if (array[i].split(":")[0].compareTo(array[j].split(":")[0]) > 0) {
                    String tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            } 
        }
        addNewProductsList(array);
    }
    //
    public static void sortArrayDescending() {
        String[] array = ProductsArray();
        for (int i = 0; i < array.length - 1; i++) {	
            for (int j = i + 1; j < array.length; j++) { 
                if (array[i].split(":")[0].compareTo(array[j].split(":")[0]) < 0) {
                    String tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            } 
        }
        addNewProductsList(array);
    }
    //
    private static void sell(){
        Scanner sc = new Scanner(System.in);
        String[] array = ProductsArray();
        
        System.out.print("Product Name : ");
        String product = sc.nextLine();
            if(searchProductList(product) != -1 ){
                int i = searchProductList(product);
                String[] split = array[i].split(";");
                float unitPrice = Float.parseFloat(split[3]);
                
                System.out.print("Quantity : ");
                int qty = sc.nextInt();
                
                float total = unitPrice * qty;
                int dis = discount(total);
                float payment = total - (total*dis)/100;
                float discounted = total - payment;
               
                System.out.println("\n [[[[ INVOICE ]]]]");
                System.out.printf("%-10s%-20s%-30s%-20s%-20s%-20s \n", "No",
                                 "Product", "Description", "Unit Price", "Quantity", "Discount");
                System.out.printf("%-10s%-20s%-30s$%-20.2f%-20s%%%-20d \n","1",product,split[1],unitPrice,qty,dis );
                row();
                System.out.printf("%100s$%-20.2f", "Sub-Total = ", total );br();
                System.out.printf("%100s$%-20.2f", " Discount = ", discounted );br();
                System.out.printf("%100s$%-20.2f", "    Total = ", payment );br();
            }else{
                System.out.println("Opp! Product Not Found...!");
                closeOrNot();
            }
    }
    //
    public static int discount(float total){
        int dis = 0;
        if ( total <= 10)
            dis = 10;
        else if ( total >= 10 && total <= 20)
            dis = 20;
        else if ( total >= 20 && total <= 30)
            dis = 30;
        else if ( total >= 30 && total <= 40)
            dis = 40;
        else if ( total >= 40 && total <= 50)
            dis = 50;
        else if ( total >= 50 && total <= 60)
            dis = 60;
        else 
            dis = 70;
        
        return dis;
    }
    //
    public static int countProducts() {
        int counter = 0;
        try {
            Scanner data = new Scanner(FilePath);
            while (data.hasNext()) {
                data.nextLine();//read newline before counter++
                counter++;
            }
            data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }
    //for delete ":" from Product Name
    public static String removeLast(String word){
        char []chars = word.toCharArray(); 
        String newWord="";
        newWord += Character.toUpperCase(chars[0]);
        for(int i = 1;i <word.length()-1;i++){
            newWord += chars[i];
        }
        return newWord;
    }
    //convert first child of word to Uppercasse
    public static String upperCase(String word){
        char []chars = word.toCharArray();    
        String newWord="";
        newWord += Character.toUpperCase(chars[0]);
        for(int i = 1;i <word.length();i++){
            newWord += chars[i];
        }
        return newWord;
    }
    //
    static void Header(){
        int count = countProducts();
        System.out.printf("%25s%-25d",">>>>>>>> Product In Stock : ", count);br();
        String array[] = {
                          "Name",
                          "Description", 
                          "Category", 
                          "UnitPrice", 
                          "UnitInStock",
                          "Supplier", 
                          "Date Modify"};
        for (String array1 : array) {
            System.out.printf("%-25s", array1);
        }
        br();
    }  
    //message if folder and file have been being created
    public static void created(String path, String fileName) {
        System.out.println("________________________=========_______________________");
        System.out.println("|  File's Path: " + path +  "\\" + fileName + "  |");
        System.out.println("|______________________________________________________|\n");
    }
    //if someone choose wrong option
    public static void wrongOp() {
        System.out.println("\n\tOpp! Maybe wrong option...!\n");
        closeOrNot();
    }
    //you want to close or not
    static void closeOrNot(){
        Scanner sc = new Scanner(System.in);
        System.out.println("  >>>>>>>> You want to exit the program?");
        System.out.println("\t (1) . Yes");
        System.out.println("\t (2) . No");
        System.out.print("  >>>>>>>> Choose one : ");
        int opt = sc.nextInt();
        if ( opt == 1 ) System.exit(0) ; else manu();
    }
    //for invoice row
    static void row(){
        for( int i = 1 ; i<5 ; i++){
            System.out.printf("%-10d%-20s%-30s%-20s%-20s %n",i+1,"","","","","" );
        }
        for(int i=1 ; i<=120 ; i++){
            System.out.print("_");
        }
        br();
    }
    //for break point
    static void br(){
        System.out.println("");
    }
    //create folder and file
    private static void createFolder(String path, String filename) {
        boolean folder = new File(path).mkdirs();
        if ( folder ) {
            try {
                boolean file = new File(path + "\\" + filename).createNewFile();
                if ( file ) {
                     created(path , filename);
                }
            }catch (IOException e) {
                System.out.println("Can't not create folder and file...");
                e.printStackTrace();
            }

        }
    }  
}