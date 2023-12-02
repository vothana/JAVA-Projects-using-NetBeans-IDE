/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Vothana CHY
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet1 = workbook.createSheet("Students");
        
        Row r0 = sheet1.createRow(0);
        Cell c0 = r0.createCell(0);
        c0.setCellValue("Vothana");
        
        File f = new File("D:\\1. OTH\\Learn\\... School\\NetBean Projects\\JavaApplication11\\XLS_File\\Students_List.xls");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
            workbook.close();
            
            System.out.println("Succeed");
        } catch (FileNotFoundException ex) {
            System.out.println("Bad");
        }
    }
}
