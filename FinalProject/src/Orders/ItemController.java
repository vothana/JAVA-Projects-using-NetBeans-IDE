/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Orders;

import Products.Products;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class ItemController implements Initializable {

    @FXML
    private AnchorPane itemAnchorPane;
    @FXML
    private Label productName;
    @FXML
    private Label price;
    @FXML
    private ImageView imageView;
    URL url;
    
    private Products products;
    private ClickProduct clickProduct;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Products products, ClickProduct clickProduct) {
        this.products = products;
        this.clickProduct = clickProduct;
        productName.setText(products.getProductName());
        price.setText(products.getUnitPrice() + " $");
                
        String dir = System.getProperty("user.dir") + "/src" + products.getPicture(); //From Database
        String def = System.getProperty("user.dir") + "/src" + "/images/defaultImage.png"; //current Directory
        
        File imageFile = new File(dir);
        File imageDef = new File(def);
        
        if (imageFile.exists()) {
            Image image = new Image(dir);
            imageView.setImage(image);
        }else if (imageDef.exists()){
            Image defaultImage = new Image(def);
            imageView.setImage(defaultImage);
        }
    }

    @FXML
    private void click(MouseEvent event) {
        clickProduct.onClickListener(products);
    }
    
    
    
}
