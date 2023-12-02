/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Products;

import MessageBox.Message;
import MessageBox.MessageView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import possystem.Slider;
import static possystem.DBConfig.getConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import static possystem.DBConfig.executeQueryUpdate;
import static possystem.DBConfig.getMaxId;
import static possystem.DBConfig.query;
import static possystem.DBConfig.close;
import possystem.Fucntions;

/**
 * FXML Controller class
 *
 * @author Vothana CHY
 */
public class ProductsController implements Initializable {


    @FXML
    private Button btnBack;
    @FXML
    private AnchorPane rooter;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtname;
    @FXML
    private ComboBox<String> txtCategory;
    @FXML
    private Button btnAddNewCategory;
    @FXML
    private TextArea describtion;
    @FXML
    private CheckBox btnCheckAvaiable;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSubmit;
    @FXML
    private TableView<Products> tableProduct;
    @FXML
    private TableColumn<Products, Integer> pid;
    @FXML
    private TableColumn<Products, String> pname;
    @FXML
    private TableColumn<Products, String> pCat;
    @FXML
    private TableColumn<Products, Double> ppricae;
    @FXML
    private TableColumn<Products, String> pDescription;
    @FXML
    private TableColumn<Products, String> pStatus;

    Window owner;
    FXMLLoader loader;
    Parent root;
    Scene scene;
    Stage primaryStage;
    
    @FXML
    private StackPane stackpane;
    @FXML
    private Button btnChooseImage;
    @FXML
    private Label txtImagePath;   //this label invisible
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private Label noImageLabel;
    @FXML
    private Button newProduct; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbCategories();
        showTable();
        addListenerForTable();
        idIncrement();
    }

    // show the table
    public void showTable() {
        ObservableList<Products> listProducts = getTableList();
        try {
            pid.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
            pname.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
            pCat.setCellValueFactory(new PropertyValueFactory<>("Category"));
            ppricae.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
            pDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            pStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

            tableProduct.setItems(listProducts);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }

    // loop all the data from database
    private ObservableList<Products> getTableList() {
        ObservableList<Products> tableList = FXCollections.observableArrayList();

        String query = "SELECT * FROM products p INNER JOIN Categories c ON p.CategoryID = c.CategoryID";
        ResultSet rs = query(query);

        Products tables;
        try {
            while (rs.next()) {
                tables = new Products(rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("CategoryName"),
                        rs.getDouble("UnitPrice"),
                        rs.getString("Description"),
                        rs.getString("Picture"),
                        rs.getString("Status"));
                tableList.add(tables);
            }
        } catch (SQLException e) {
            Fucntions obj = new Fucntions();
            obj.printSQLException(e);
        }
        
        return tableList;
    }
    
    //Used in orderDashboard
    public ObservableList<Products> allProducts(){
        ObservableList<Products> list =  getTableList();
        return list;
    }
    
    public void idIncrement() {
        String query = "SELECT MAX(ProductID) FROM Products";
        String cid = String.valueOf(getMaxId(query) + 1);
        txtid.setText(cid);
    }
    
    @FXML
    private void addnewProduct(ActionEvent event) {
        clearForm();
    }

    // Clear form
    private void clearForm() {
        idIncrement();
        txtname.setText("");
        txtUnitPrice.setText("");
        describtion.setText("");
        txtImagePath.setText(""); //this label invisible
        imageView.imageProperty().set(null);
        if(noImageLabel.isVisible()){
            noImageLabel.setVisible(false);
        }
        btnDelete.setDisable(true);
        btnSubmit.setText("Submit");
    }
    
    // when click a row of table will bring data to text feilds
    private void addListenerForTable() {
        tableProduct.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnSubmit.setText("Update");
                btnDelete.setDisable(false);

                txtid.setText(String.valueOf(newSelection.getProductID()));
                txtname.setText(newSelection.getProductName());
                txtCategory.setValue(newSelection.getCategory());
                txtUnitPrice.setText(String.valueOf(newSelection.getUnitPrice()));
                describtion.setText(newSelection.getDescription());
                if ( newSelection.getStatus() == "Available")
                    btnCheckAvaiable.setSelected(true);
                
                checkImageFileIsPresent(newSelection.getPicture());
            } else {
                clearForm();
                btnSubmit.setText("Submit");
                btnDelete.setDisable(true);
            }
        });
    }
    
    private void checkImageFileIsPresent(String fileImgPath){
        String dir = System.getProperty("user.dir") + "/src" + fileImgPath; //From Database
 
        File imageFile = new File(dir);
        
        if (imageFile.exists()) {
            Image image = new Image(dir);
            imageView.setImage(image);
            noImageLabel.setVisible(false);
            txtImagePath.setText("Prevent Has");//this label invisible
        }else{
            noImageLabel.setVisible(true);
            txtImagePath.setText("");//this label invisible
        }
    }

    private String GetImagePathToDelete() throws IOException{
            //trying to delete the image file from directory folder when delete product
            try {
                if ( imageView.getImage().getUrl() != null ){
                  String pathFromImageView = imageView.getImage().getUrl();
                  return deleteImage(pathFromImageView);
                }
            } catch (Exception e) {
                System.err.print(e.getMessage() + "sss");
                return null;
            }
            
            return null;
    }
    
    private String deleteImage(String pathFromImageView) throws IOException{
        String spiltedPath = null;
        if( pathFromImageView.contains("classes")){
            spiltedPath  = pathFromImageView.split("classes")[1]; //"classes" is the word inside pathFromImageView inside build
        }else if( pathFromImageView.contains("src")){
            spiltedPath  = pathFromImageView.split("src")[1]; //"src" is the word inside pathFromImageView inside prosject
        }

        if( spiltedPath.contains("%20") )
            spiltedPath = spiltedPath.replace("%20", " ");

        String pathToReturn = spiltedPath;
        
        spiltedPath = spiltedPath.replace("/", "\\");  
        String currentDir = System.getProperty("user.dir");
        currentDir += "\\src\\" + spiltedPath;

        String imageToDeleteFromFolder = currentDir;
        Path imagesPath = Paths.get(imageToDeleteFromFolder);

        if( imagesPath != null){
            Files.delete(imagesPath);
        }
         
        return pathToReturn;
    }
    
    
    @FXML
    private void deleteProduct(ActionEvent event) throws IOException, InterruptedException {
        getConnection();
        MessageBox.Message message = new Message();
        Products product = tableProduct.getSelectionModel().getSelectedItem();
        String messagetext = "Are you sure ? Delete Product's name : " + product.getCategory();
        
        int ok = message.popupMessage(rooter, messagetext, "Confirm");
        if(ok>0){
             try {
            
            String query = "DELETE FROM `Products` WHERE ProductID = '" + product.getProductID()+ "'";

            GetImagePathToDelete();
            executeQueryUpdate(query);
            showTable();
            clearForm();
            
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        }
    }

    private ObservableList<String> cbCategories() {
        getConnection();
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            String query = "SELECT * From Categories";
            ResultSet rs = query(query);
            while (rs.next()) {
                list.add(rs.getString("CategoryName"));
            }
            txtCategory.setItems(null);
            txtCategory.setItems(list);
            txtCategory.setValue(list.get(0));
            return list;
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        } 
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Slider silder = new Slider();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/possystem/Dashboard.fxml"));
        Parent root = loader.load();
        silder.slideTopToBottom(loader, root, rooter, btnBack, true);
    }

    @FXML
    private void addNewCategory(ActionEvent event) throws IOException {
        owner = rooter.getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("/Categories/Categories.fxml"));
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
        primaryStage.showAndWait();
        cbCategories();
    }
    
    MessageView messageView = new MessageView();
    @FXML
    private void submit(ActionEvent event) throws IOException {
        int id = Integer.valueOf(txtid.getText());
        String name = txtname.getText();
        String category = txtCategory.getValue();
        String unitPrice = txtUnitPrice.getText();
        String description = describtion.getText();
        String status = "Unavailable";
                
        if(btnCheckAvaiable.isSelected()){
            status = "Available";
        }
        int catID =  cbCategories().indexOf(category) + 1;

        if("Submit".equals(btnSubmit.getText())){
            if(!txtname.getText().isEmpty() && !txtUnitPrice.getText().isEmpty()){
                String query = "INSERT INTO `products` Values(" + id
                            + "  , '" + name
                            + "' , " + catID
                            + "  , '" + unitPrice
                            + "' , '" + description
                            + "' , '" + imagePathToUpload(category)
                            + "' , '" + status + "')";
                executeQueryUpdate(query);
                messageView.messageBox(btnSubmit, "Success adding product!" , "Success");
                showTable();
                saveImageToFolder(category);
                clearForm();
            }else{
                messageView.messageBox(btnSubmit, "Make sure no feild blank !" , "Wrong");
            }
        }else{ //it mean update
            getConnection();
            if(!txtname.getText().isEmpty() && !txtUnitPrice.getText().isEmpty()){
                if(!txtImagePath.getText().isEmpty()){
                    try {
                    Products product = tableProduct.getSelectionModel().getSelectedItem();
                    String q = "UPDATE `Products` SET ProductName = '" + name 
                            + "' , CategoryID     = '" + catID
                            + "' , UnitPrice      = '" + unitPrice
                            + "' , Description    = '" + description
                            + "' , Picture       = '" + imagePathToUpload(category)
                             + "' , Status       = '" + status
                            + "' WHERE ProductID = '" + id + "'";
                    executeQueryUpdate(q);
                    saveImageToFolder(category);
                    showTable();
                    close();
                    clearForm();
                    } catch (Exception e) {
                        System.err.print(e.getMessage());
                    }
                }else{
                    messageView.messageBox(btnSubmit, "Please choose image to update!" , "Wrong");
                }
            }else{
                messageView.messageBox(btnSubmit, "Make sure no feild blank !" , "Wrong");
            }
        }
        
    }
    
    String fullpath;
    @FXML
    private void chooseImage(ActionEvent event) throws IOException {
        ExtensionFilter ex1 = new ExtensionFilter("Image Files", "*.png");
        ExtensionFilter ex2 = new ExtensionFilter("Image Files", "*.jpg");
        ExtensionFilter ex3 = new ExtensionFilter("all Files", "*.*");
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.getExtensionFilters().addAll(ex1, ex2, ex3);
            File file = filechooser.showOpenDialog(rooter.getScene().getWindow());
            if(file.getAbsolutePath() != null){
                fullpath = file.getAbsolutePath(); //Full path of image chosen
                Image image = new Image(fullpath);
                imageView.setImage(image);
                txtImagePath.setText(fullpath);
                noImageLabel.setVisible(false);
            }else{
                noImageLabel.setVisible(true);
            }
        } catch (Exception e) {
            noImageLabel.setVisible(true);
        }  
    }

    private String imagePathToUpload(String category){
        ObservableList<String> list = cbCategories();
        String imagePath = "/images/" + category +  
                fullpath.substring(fullpath.lastIndexOf("\\"))
                        .replace("\\", "/");
        System.out.println("Image Path To Save to database : " + imagePath);
        return imagePath;
    }
    
    private void saveImageToFolder(String categoryName) throws IOException{
        File sourceFile = null;
        File desFile = null;
        String imagePath = imagePathToUpload(categoryName);
        
        String newPath = "src/images/" + categoryName ;
        File dire = new File(newPath);
        if(!dire.exists()){
            dire.mkdirs();
        }
        
        String imageNamewWithExtension = imagePath.substring(imagePath.lastIndexOf("/"));
        sourceFile = new File(fullpath);
        desFile = new File(newPath + imageNamewWithExtension);
        
        if(!desFile.exists()){
            System.out.println("Image Path To copy : " + imageNamewWithExtension);
            Files.copy(sourceFile.toPath(), desFile.toPath());
        }
    }

    
    
//String s1 = path.substring(path.indexOf("\\")+3);
//String trim = s1.trim();
//String imagePath = imageFile(categoryName, path);

//System.out.println(imagePath);
//        System.out.println(iconimage.getName());
//        if (iconimage != null) {
//            String iconimagepath = iconimage.getAbsolutePath();
//            System.out.println(iconimagepath);
//            iconimageview.setImage(iconimage);
//        }

 
}
