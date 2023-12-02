/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Orders;

import Customers.CustomersController;
import MessageBox.MessageView;
import Orders.Table.TableController;
import Products.Products;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import static possystem.DBConfig.query;
import possystem.Fucntions;
import possystem.Slider;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import static possystem.DBConfig.getConnection;
import static possystem.DBConfig.query;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class OrderDashboardController implements Initializable {
    Window owner;
    FXMLLoader loader;
    Parent root;
    Scene scene;
    Stage primaryStage;
    
    @FXML
    private Button btnDone;
    @FXML
    private Button btnCancel;
    @FXML
    private Label orderDate;
    @FXML
    private ComboBox<String> customerName;
    @FXML
    private Button btnAddNewCustomer;
    @FXML
    private HBox orderDetail;
    @FXML
    private Label noNumber;
    @FXML
    private Label productName;
    @FXML
    private Label productDetail;
    @FXML
    private Label qauntity;
    @FXML
    private Label price;
    @FXML
    private Label orderDetailTotal;
    @FXML
    private Label sub_Total;
    @FXML
    private Label discount;
    @FXML
    private Label total_Dollar;
    @FXML
    private Label tota_khmer;
    @FXML
    private Button btnClearOrder;
    @FXML
    private Button btnDiscount;
    @FXML
    private Button btnPay;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label orderID;
    
    private List<Products> products = new ArrayList<>();
    private Image image;
    private ClickProduct clickProduct;
    @FXML
    private AnchorPane rooter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayProducts();
        getCustomersList();
//        try {addTable();} catch (IOException ex) {}

    }
    
    private List<Products> getProducts(){
           List<Products> list = new ArrayList<Products>();

            String query = "SELECT * FROM products";
            ResultSet rs = query(query);

            Products product;
            try {
                while (rs.next()) {
                    product = new Products(rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("CategoryID"),
                            rs.getDouble("UnitPrice"),
                            rs.getString("Description"),
                            rs.getString("Picture"),
                            rs.getString("Status"));
                    list.add(product);
                }
            } catch (SQLException e) {
                Fucntions obj = new Fucntions();
                obj.printSQLException(e);
            }

        return list;
    }
    MessageView messageView = new MessageView();
    private void setChosenProduct(Products product) throws IOException {
        messageView.messageBox(btnDone, "Clicked on : " + product.getProductName() , "Success");
    }
    
    private void displayProducts(){
        products.addAll(getProducts());
        if (products.size() > 0) {
            clickProduct = new ClickProduct() {
                @Override
                public void onClickListener(Products product) {
                    try {
                        setChosenProduct(product);
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(products.get(i), clickProduct);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void exit() throws IOException{
        Slider silder = new Slider();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/possystem/Dashboard.fxml"));
        Parent root = loader.load();
        silder.slideTopToBottom(loader, root, rooter, btnCancel, true);
    }
    
    private void getCustomersList(){
        getConnection();
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            String query = "SELECT CustomerName From Customers";
            ResultSet rs = query(query);
            while (rs.next()) {
                list.add(rs.getString("CustomerName"));
            }

            customerName.setItems(null);
            customerName.setItems(list);
            customerName.setValue(list.get(0));
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    @FXML
    private void doneOrder(ActionEvent event) throws IOException {
        exit();
    }

    @FXML
    private void CancelOrder(ActionEvent event) throws IOException {
        exit();
    }

    @FXML
    private void addNewCustomer(ActionEvent event) throws IOException {
        owner = rooter.getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("/Customers/Customers.fxml"));
        root = loader.load();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.initOwner(owner);
        primaryStage.focusedProperty();
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        blur(rooter,10);
        CustomersController controller = loader.getController();
        controller.preventClose("clickedFromFormOrder");
        primaryStage.showAndWait();
        getCustomersList();
        blur(rooter,0);
    }
    

    @FXML
    private void clearOrderList(ActionEvent event) {
        
    }

    @FXML
    private void inputDiscount(ActionEvent event) {
        
    }

    @FXML
    private void actionPaying(ActionEvent event) {
        
    }
    
    @FXML
    private void addOrderTable(MouseEvent event) throws IOException {
         addTable();
    }
     private void addTable() throws IOException {
            owner = rooter.getScene().getWindow();
            loader = new FXMLLoader(getClass().getResource("Table/Table.fxml"));
            root = loader.load();
            scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage = new Stage();
            primaryStage.setResizable(false);
            primaryStage.initOwner(owner);
            primaryStage.focusedProperty();
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            blur(rooter,10);
            primaryStage.showAndWait();
            blur(rooter,0); 
            TableController tableController = loader.getController();
            orderID.setText(String.valueOf(tableController.getTableNumber()));
     }
    private void blur(AnchorPane anchorPane, int blurRadius){
         GaussianBlur g = new GaussianBlur();  
         g.setRadius(blurRadius); 
        anchorPane.setEffect(g);
    }
}
