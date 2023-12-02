/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 *
 * @author VICHET-PC
 */
public class Dictionary {

    private static File dictFile;

    public static void main(String[] args) {
        //store dictionary data in drive C:...
        dictFile = new File("C:\\dictionary\\file\\dictionary.txt");
        //if file donsn't exist create new folder and file(dictionary.txt)
        if (!dictFile.exists()) {
            createFolder("C:\\dictionary\\file", "dictionary.txt");
        }

        String answer = "no";
        Scanner keyboard = new Scanner(System.in);
        do {
            operation();
            System.out.print("\tDo you want to continute? yes/no?");
            answer = keyboard.nextLine();
        } while (answer.equalsIgnoreCase("yes") | answer.equalsIgnoreCase("y"));

    }
   

    /**
     * create folder and file
     * @param path is the folder for keep files
     * @param fileName is the file for keep word and definition
     */
    public static void createFolder(String path, String fileName) {
        boolean folder = new File(path).mkdirs();
        if (folder) {
            try {
                boolean file = new File(path + "\\" + fileName).createNewFile();
                if (file) {
                    System.out.println("Dictionary file : " + path + "\\" + fileName);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * add new word and definition to file
     * @param array is used to save a array of words into file
     */
    public static void addNewWordList(String[] array) {
        try {
            FileWriter fw = new FileWriter(dictFile, false);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < array.length; i++) {
                pw.println(array[i]);
            }
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * add new word and definition to file while you want to update
     */
    public static void addNewWord() {
        Scanner keyboard = new Scanner(System.in);
        String word, definition;

        System.out.print("\tEnter new word:");
        word = keyboard.nextLine();
        word = convertSentence(word);
        System.out.print("\tEnter new definition:");
        definition = keyboard.nextLine();
        try {
            FileWriter fw = new FileWriter(dictFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(word);
            pw.print(":");
            pw.println(definition);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all word and definition from file insert into array of String
     * @return the array of words
     */
    public static String[] getWordList() {
        String list[] = new String[getTotalWordCount()];
        int index = 0;
        
        try {
            Scanner data = new Scanner(dictFile);
            while (data.hasNext()) {
                list[index] = data.nextLine();
                index++;
            }
            data.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

//      list[0] = Banana:  jek
//      list[1] = Orange:  krouch
        return list;
    }
    


    /**
     * display all words and definitions from file
     * 
     */
    public static void displayWordList() {
        try {
            Scanner data = new Scanner(dictFile);
            if (data.hasNext()) {
                System.out.println("------------------------------------------------------");
                while (data.hasNext()) {
                    System.out.println("\t" + data.nextLine());
                }
                System.out.println("------------------------------------------------------");
            } else {
                System.out.println("\tDictionary is empty! Please add new word and definition!");
            }
            data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the total of number of records in file
     * @return the number of record in file
     */
    public static int getTotalWordCount() {
        int counter = 0;
        try {
            Scanner data = new Scanner(dictFile);
            while (data.hasNext()) {
                data.nextLine();
                counter++;
            }
            data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

   
    /**
     * delete word from file
     * @param wordDelete delete word from array
     * @return always return true
     */
    public static boolean deleteWordDictionary(String wordDelete) {

        String newData[] = deleteArray(wordDelete);
        if (newData != null) {
            System.out.println("\tThe word \" " + wordDelete + "\" was deleted.");
        } else {
            System.out.println("\tThe word \"" + wordDelete + "\" was not found to deleted.");
        }
        
        return true;
    }

  
    /**
     * delete word in array
     * @param wordDelete for delete from array
     * @return true if deleted else false
     */
    public static String[] deleteArray(String wordDelete) {//1,2,3,4
        boolean status = false;
//      list[0] = Banana:  jek
//      list[1] = Orange:  krouch
//      list[2] = Potato:  domloung
        String array[] = getWordList();
        String[] newArray;

        if (searchArray(wordDelete)) {
            newArray = new String[array.length - 1]; // sol 2
            
            for (int i = 0; i < array.length; i++) {

                if (i != array.length - 1) {// 0 != 0 : false
                    newArray[i] = array[i];
                }

                if (array[i].split(":")[0].equalsIgnoreCase(wordDelete)) {//2 = 2
                    for (int d = i; d < array.length - 1; d++) {//d = 1 , 2,3
                        array[d] = array[d + 1];//array[1] = array[2], array[2] = array[3]
                        newArray[d] = array[d];   
                    }
                    
                    addNewWordList(newArray);
                    return newArray;
                }
            }
        }
        
        return null;
    }

    
    /**
     *sort word ascending from array 
     * @param array sort array as ascending order
     */
    public static void sortArrayAscending(String array[]) {//a,s,b,e,z
    	
    	
    	//Potato, Orange, Banana
        for (int i = 0; i < array.length - 1; i++) {
        	
            for (int j = i + 1; j < array.length; j++) { 
                if (array[i].split(":")[0].compareTo(array[j].split(":")[0]) > 0) {
                    String tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
            //when i= 0 => //Banana, Potato, Orange
            //when i= 1 => //Banana, Orange, Potato
            //when i= 2 => //
            
        }
        
        addNewWordList(array);
    }

   
    /**
     * sort word by descending from array
     * @param array sort array as descending order
     */
    public static void sortArrayDescending(String array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].split(":")[0].compareTo(array[j].split(":")[0]) < 0) {
                    String tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        addNewWordList(array);
    }

    
    /**
     * search word from array
     * @param wordSearch the word for search from array
     * @return  true if found, and false is not found
     */
    public static boolean searchArray(String wordSearch) {
        String[] array = getWordList();
        //list[0] = Banana
        //list[1] = Orange
        
        for (int i = 0; i < array.length ; i++) {
            if (array[i].split(":")[0].equalsIgnoreCase(wordSearch)) {
                System.out.println("\tThe word \"" + wordSearch + "\" was found in dictionarty!");
                return true;
            }
        }
        
        return false;
    }
public static String searchArrayList(String wordSearch) {
        String[] array = getWordList();
        
        for (int i = 0; i < array.length; i++) {
            if (array[i].split(":")[0].equalsIgnoreCase(wordSearch)) {
                return array[i];
            }
        }
        
        return null;
    }
    
    /**
     * update new word and definition from array
     * @param wordSearch the word for search in file
     * @param wordNew the word for insert into file
     * @param newDefinition  the definition of word for insert into file
     */
    public static void updateArray(String wordSearch, String wordNew, String newDefinition) {
        String[] array = getWordList();
        
        if (searchArray(wordSearch)) {
            for (int i = 0; i < array.length; i++) { 
                if (array[i].split(":")[0].equalsIgnoreCase(wordSearch)) {
                    String newUpdated = wordNew + ":" + newDefinition;
                    array[i] = newUpdated;//array[2] = .... : ....
                    addNewWordList(array);
                    break;
                }
            }
        }
    }
 
    /**
     *  display options for user so user can insert, delete, update, and search in file. 
     */
    public static void operation() {
        Scanner keyboard = new Scanner(System.in);

        int option = menu();

        switch (option) {
            case 1:
                System.out.println("###Add new word and definition into dictionary!###");
                addNewWord();
                break;
            case 2:
                System.out.println("###Delete word from dictionary!###");
                System.out.print("\tEnter word you want to delete!");
                String wordDelete = keyboard.nextLine();
                deleteWordDictionary(wordDelete);
                break;
            case 3:
                System.out.println("###Update word and definition from dictioanry!###");
                System.out.print("\tSearch the word, you want to update!");
                String update = keyboard.nextLine();
                if (searchArray(update)) {
                    System.out.print("\tEnter new word:");
                    String newWord = keyboard.nextLine();
                    newWord = convertSentence(newWord);
                    System.out.print("\tEnter new definition:");
                    String newDefinition = keyboard.nextLine();
                    updateArray(update, newWord, newDefinition);
                } else {
                    System.out.println("\tThe word \"" + update + "\" was not found in dictionary!");
                }
                break;
            case 4:
                System.out.println("###Search word from dictionary!###");
                System.out.print("\tEnter word to search:");
                String search = keyboard.nextLine();
                String wordFound = searchArrayList(search);
                if(wordFound == null){
                    System.out.println("\tYour word is not found in dictionary! \n\tPlease create your word!");
                }else{
                    System.out.println("\t"+wordFound);
                }
                break;
            case 5:
                System.out.println("###Display all words and definitions from dictionary!###");
                displayWordList();
                break;
            case 6:
                System.out.println("###Sort all words by ascending order###");
                sortArrayAscending(getWordList());
                displayWordList();
                break;
            case 7:
                System.out.println("###Sort all words by descending order###");
                sortArrayDescending(getWordList());
                displayWordList();
                break;
            case 8:
                System.out.println("###Exit application###");
                System.exit(0);
                break;
            default:
                System.out.println("\tPlease enter number in menu above!");

        }
    }

    //display menu to user
    /**
     * display menu to user for choose option to execute 
     * @return  int option that user inserted
     */
    public static int menu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("\tEnter number optionn below:\n"
                + "\t1. Add New Word and Definition!\n"
                + "\t2. Delete word from dictionary!\n"
                + "\t3. Update word and definition from dictionary!\n"
                + "\t4. Search word from dictionary!\n"
                + "\t5. Display all words and definitions from dictionary\n"
                + "\t6  Sort all words by ascending order\n"
                + "\t7  Sort all words by descending order\n"
                + "\t8. Exit");
        System.out.println("------------------------------------------------------");
        System.out.print("\tEnter number option above:");
        int option = 0;  
        try {
            option = keyboard.nextInt();
        } catch (InputMismatchException e) {
//          user enter other characters or other numbers
//          System.out.println(e.getMessage());
        	e.printStackTrace();
        }
        
        return option;
    }

    public static String convertSentence(String word){
    	//banana
        char []chars = word.toCharArray();   // chars{b,a,n,a,n,a};
        String newWord = "";
        newWord += Character.toUpperCase(chars[0]); //b => B
        newWord = "B";
        
        for(int i = 1;i <word.length();i++){
            newWord += chars[i];
        }
        
        return newWord;
    }
    
}
