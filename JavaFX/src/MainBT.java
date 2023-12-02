/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vothana CHY
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class MainBT {
    private static File Folder;
    private static File FileOR;
    private static File FileRE;
    private static File FileER;

    private static String path = "C:\\..AutoFill";
    private static String fileOr = "original.txt";
    private static String fileRe = "result.txt";
    private static String fileEr = "Message.txt";
    private static FileWriter fw;
    private static PrintWriter pw;
    
    public static void maintb(){
        FileOR = new File(path + "\\" + fileOr);
		FileRE = new File(path + "\\" + fileRe);
		FileER = new File(path + "\\" + fileEr);

		
		if ( FileOR.exists() ) {
			try {
				Scanner x = new Scanner(FileOR);
				if (x.hasNext() ) {
					if( FileRE.exists() ) {
                                                                                                                                                                                                                                                              FileRE.delete();
						fw = new FileWriter(FileRE, true);
						pw = new PrintWriter(fw);
						while ( x.hasNext() ) {
							pw.println("\t{");
							pw.print("\t\t \"phone\": \"+855");
							pw.print(x.next());
							pw.println("\"");
							pw.println("\t},");
						}
						pw.close();
						System.out.println("Suceess");
					}else {
						createFolder(path, fileRe);
						fw = new FileWriter(FileRE, true);
						pw = new PrintWriter(fw);
						while ( x.hasNext() ) {
							pw.println("\t{");
							pw.print("\t\t \"phone\": \"+855");
							pw.print(x.next());
							pw.println("\"");
							pw.println("\t},");
						}
						pw.close();
						System.out.println("Suceess");
					}
					
					x.close();
				}else {
					noData();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			//Message();
			createFolder(path,fileOr);
		}
    }
    
    
    
    
    
    
    
    
    
    private static void Message() {
		FileER = new File(path + "\\" + fileEr);
		if( FileER.exists() ) {
			messageText();
		}else {
			createFolder(path,fileEr);
			messageText();
		}
	}
	
	
	private static void createFolder(String path, String filename) {
		Folder = new File(path);
		boolean folder = new File(path).mkdirs();
		//create folder and Sub_Folder
		if( Folder.exists() ) {
			try {
				boolean file = new File(path + "\\" + filename).createNewFile();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			createFile(folder,path,filename);
		}
		
	}
	
	private static void createFile(boolean folder, String path, String filename) {
		//check if folder and sub_folder was created
		if ( folder ) {
			try {
				boolean file = new File(path + "\\" + filename).createNewFile();
				if ( file ) {
					System.out.println("Created New Folder and  New File Success !!");
					System.out.println("File name's path : " + path + "\\" + filename + "\n");
				}
			}catch (IOException e) {
				System.out.println("Can't not create folder and file...");
				e.printStackTrace();
			}
		}else {
			System.out.println("No folder");
		}
	}
	
	
	
	private static void messageText() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		try {
			fw = new FileWriter(FileER, true);
			pw = new PrintWriter(fw);
			pw.println("===================TDW Message===================");
			pw.print("កាលបរិច្ឆេទ ៖ ");
			pw.print(dtf.format(now));
			pw.println(" ដំណើរការ");
			pw.println("\t>>>> ប្រហែលជាគ្មាន File orginal.txt");
			pw.println("\t>>>> ឬសរសេរឈ្មោះ File ខុស");
			pw.println("\t>>>> ឬ File ត្រូវបានលុប");
			pw.println("\t>>>> \"សូមពិនិត្រឡើងវិញ\"");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void noData() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now(); 
		try {
            fw = new FileWriter(FileER, true);
            pw = new PrintWriter(fw);
            pw.println("===================TDW Message===================");
            pw.print("កាលបរិច្ឆេទ ៖ ");
            pw.print(dtf.format(now));
            pw.println(" ដំណើរការ");
            pw.println("\t>>>> ក្នុង orginal.txt គ្មានទិន្នន័យ");
            pw.println("\t>>>> \"សូមពិនិត្រឡើងវិញ\"");
            pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
