/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Products;
import java.sql.*;
/**
 *
 * @author Vothana CHY
 */
public class Products {
    private int ProductID;
    private String ProductName;
    private String Category;
    private double UnitPrice;
    private String Description;
    private String Picture;
    private String Status;

    public Products(int ProductID, String ProductName, String Category, double UnitPrice, String Description, String Picture, String Status) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Category = Category;
        this.UnitPrice = UnitPrice;
        this.Description = Description;
        this.Picture = Picture;
        this.Status = Status;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    
    

}
