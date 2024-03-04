import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.paint.*;  
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Line;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.effect.BoxBlur; 
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main extends Application
{
    String reg,paswd;
    Scene dropDown_page;
    Scene choicePage;
    TextArea complaintTextArea;
    TextArea complaintsTextArea;
    // Create a LinearGradient for the background fill
     Stop[] stops = new Stop[] { new Stop(0, Color.LIGHTBLUE), new Stop(1, Color.WHITE)};
    LinearGradient gradient = new LinearGradient(
                                                0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops
                                                );
                                              
    // Set the background fill of the border pane
    BackgroundFill backgroundFill = new BackgroundFill(gradient, null, null);
    Background background = new Background(backgroundFill);
    
    
    public void start(Stage primaryStage)throws Exception
    {
        HBox hbButtons = new HBox(); 
        Label choose = new Label();
        GridPane grid = new GridPane();
        
        //Setting label text
        choose.setText("Are you a Hosteller or Admin?");
        choose.setStyle("-fx-font-weight: bold;");
        choose.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        choose.setTextFill(Color.DARKBLUE);
        
        //creating buttons
        Button hostellerbtn = new Button("Hosteller");
        hostellerbtn.setLayoutX(150);
        hostellerbtn.setLayoutY(120);
        hostellerbtn.setPrefWidth(90);
        hostellerbtn.setPrefHeight(30);
        hostellerbtn.setTextFill(Color.DARKBLUE);
        hostellerbtn.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        hostellerbtn.setBackground(new Background(new BackgroundFill(gradient, null, null)));
         
        // Apply a drop shadow effect to the button
        DropShadow dropShadow = new DropShadow(10, Color.GRAY);
        hostellerbtn.setEffect(dropShadow);
        
        hostellerbtn.setSkin(new MyButtonSkin(hostellerbtn));
        
        
        Button adminbtn = new Button("Admin");
        adminbtn.setLayoutX(150);
        adminbtn.setLayoutY(120);
        adminbtn.setPrefWidth(90);
        adminbtn.setPrefHeight(30);
        adminbtn.setTextFill(Color.DARKBLUE);
        adminbtn.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        adminbtn.setBackground(new Background(new BackgroundFill(gradient, null, null)));
        
        // Apply a drop shadow effect to the button
        DropShadow dropshadow = new DropShadow(10, Color.GRAY);
        adminbtn.setEffect(dropshadow);
        adminbtn.setSkin(new MyButtonSkin(adminbtn));
        
        Button login_btn = new Button("Login");
        login_btn.setSkin(new MyButtonSkin(login_btn));
        login_btn.setLayoutX(150);
        login_btn.setLayoutY(120);
        login_btn.setPrefWidth(80);
        login_btn.setPrefHeight(30);
        login_btn.setTextFill(Color.DARKBLUE);
        login_btn.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        login_btn.setBackground(new Background(new BackgroundFill(gradient, null, null)));
        
        // Apply a drop shadow effect to the button
        DropShadow drop2shadow = new DropShadow(10, Color.GRAY);
        login_btn.setEffect(drop2shadow);
        
        hostellerbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                Label h_Rno = new Label("Registration Number:");
                h_Rno.setTextFill(Color.DARKBLUE);
                h_Rno.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                
                TextField tfName = new TextField();
                
                Label h_Pwd = new Label("Password:");
                h_Pwd.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                h_Pwd.setTextFill(Color.DARKBLUE);
                
                PasswordField pfPwd = new PasswordField();
                
                GridPane root = new GridPane();
                root.add(h_Rno, 0, 0);
                root.add(tfName, 1, 0);
                root.add(h_Pwd, 0, 1);
                root.add(pfPwd, 1, 1);
                root.add(login_btn, 1, 2);  
                root.setAlignment(Pos.CENTER);
                root.setVgap(10);
                root.setHgap(5);
                
                login_btn.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent e)
                    {
                        reg=tfName.getText();
                        paswd=pfPwd.getText();
                        tfName.clear();
                        pfPwd.clear();
                        
                        if(Login.hosteller_login(reg,paswd))
                        {
                                // Create a BorderPane to hold the main page components
                                BorderPane mainPagePane = new BorderPane();

                                // Create a VBox to hold the dropdown boxes
                                VBox dropdownBox = new VBox();
                                
                                dropdownBox.setSpacing(10);
                                dropdownBox.setPadding(new Insets(20));
                                dropdownBox.setAlignment(Pos.TOP_LEFT);
                                dropdownBox.setLayoutX(150);
                                dropdownBox.setLayoutY(120);
                                dropdownBox.setStyle("-fx-font-family: Arial;  -fx-text-fill: blue;");
                                dropdownBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

                                // Create the dropdown boxes
                                ComboBox<String> profileComboBox = new ComboBox<>();
                                profileComboBox.getItems().addAll("View Profile", "Change Password");
                                profileComboBox.setPromptText("Profile");
                                
                                // Create labels for the profile information
                                Label nameLabel = new Label();
                                nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                nameLabel.setTextFill(Color.BLACK);
                                
                                Label regNumberLabel = new Label();
                                regNumberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                regNumberLabel.setTextFill(Color.BLACK);
                                
                                Label phoneNumberLabel = new Label();
                                phoneNumberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                phoneNumberLabel.setTextFill(Color.BLACK);
                                
                                Label degreeLabel = new Label();
                                degreeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                degreeLabel.setTextFill(Color.BLACK);
                                
                                Label messTypeLabel = new Label();
                                messTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                messTypeLabel.setTextFill(Color.BLACK);
                                
                                Label roomNumberLabel = new Label();
                                roomNumberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                roomNumberLabel.setTextFill(Color.BLACK);
                                
                                Label roomTypeLabel = new Label();
                                roomTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                roomTypeLabel.setTextFill(Color.BLACK);
                                
                                Label hostelBlockLabel = new Label();
                                hostelBlockLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                hostelBlockLabel.setTextFill(Color.BLACK);
                                
                                Label laundrynumberLabel= new Label();
                                laundrynumberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                laundrynumberLabel.setTextFill(Color.BLACK);
                                
                                String originalPromptText = profileComboBox.getPromptText();
                                
                                //action on view profile
                                profileComboBox.setOnAction(event -> {
                                   String selectedOption = profileComboBox.getValue();
                                   if (selectedOption.equals("View Profile")) {
                                       nameLabel.setText("NAME: " +  Login.getHostellerName(reg,paswd));
                                       regNumberLabel.setText("REGISTRATION NUMBER: " + Login.getRegistrationNumber(reg,paswd));
                                       phoneNumberLabel.setText("PHONE NUMBER: " +  Login.getPhoneNumber(reg,paswd));
                                       degreeLabel.setText("DEGREE: " +  Login.getDegree(reg,paswd));
                                       messTypeLabel.setText("MESS TYPE: " +  Login.getMessType(reg,paswd));
                                       roomNumberLabel.setText("ROOM NUMBER: " +  Login.getRoomNumber(reg,paswd));
                                       roomTypeLabel.setText("ROOM TYPE: " +  Login.getRoomType(reg,paswd));
                                       hostelBlockLabel.setText("HOSTEL BLOCK: " +  Login.getHostelBlock(reg,paswd));
                                       
                                       nameLabel.setStyle("-fx-font-weight: bold;");
                                       regNumberLabel.setStyle("-fx-font-weight: bold;");
                                       phoneNumberLabel.setStyle("-fx-font-weight: bold;");
                                       degreeLabel.setStyle("-fx-font-weight: bold;");
                                       messTypeLabel.setStyle("-fx-font-weight: bold;");
                                       roomNumberLabel.setStyle("-fx-font-weight: bold;");
                                       roomTypeLabel.setStyle("-fx-font-weight: bold;");
                                       hostelBlockLabel.setStyle("-fx-font-weight: bold;");
                                       
                                       Button backToDropdownPage = new Button("< Back");
                                       backToDropdownPage.setLayoutX(150);
                                       backToDropdownPage.setLayoutY(120);
                                       backToDropdownPage.setPrefWidth(80);
                                       backToDropdownPage.setPrefHeight(20);
                                       backToDropdownPage.setTextFill(Color.DARKBLUE);
                                       backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                       backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                       DropShadow drop2shadow = new DropShadow(10, Color.GRAY);
                                       backToDropdownPage.setEffect(drop2shadow);
                                       backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                       
                                       backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText(originalPromptText);
                                       });
 
                                       VBox profile_display=new VBox();
                                       BorderPane for_profile_bg = new BorderPane();
                                       profile_display.getChildren().addAll(nameLabel, regNumberLabel, phoneNumberLabel, degreeLabel, messTypeLabel, roomNumberLabel, roomTypeLabel, hostelBlockLabel,backToDropdownPage);
                                       profile_display.setPadding(new Insets(15));
                                       profile_display.setSpacing(10);
                                       for_profile_bg.setCenter(profile_display);

                                       Scene scene = new Scene(for_profile_bg,1100,700);
                                       for_profile_bg.setBackground(background);
                                       primaryStage.setScene(scene);  
                                       primaryStage.setTitle("Profile Section");
                                   }
                                   
                                   if(selectedOption.equals("Change Password"))
                                   {
                                       Label old_password = new Label("Old Password:");
                                       old_password .setTextFill(Color.DARKBLUE);
                                       old_password .setFont(Font.font("Arial", FontWeight.BOLD, 12));
                                       
                                       Label new_password = new Label("New Password:");
                                       new_password.setTextFill(Color.DARKBLUE);
                                       new_password .setFont(Font.font("Arial", FontWeight.BOLD, 12));
                                       
                                       PasswordField old_password_tf = new PasswordField();
                                       PasswordField new_password_tf = new PasswordField();
                                       
                                       Button submit_new_password = new Button("Submit");
                                       submit_new_password.setLayoutX(150);
                                       submit_new_password.setLayoutY(120);
                                       submit_new_password.setPrefWidth(80);
                                       submit_new_password.setPrefHeight(20);
                                       submit_new_password.setTextFill(Color.DARKBLUE);
                                       submit_new_password.setFont(Font.font("Arial",FontWeight.BOLD, 13));
                                       submit_new_password.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                       DropShadow drop2shadow = new DropShadow(10, Color.GRAY);
                                       submit_new_password.setEffect(drop2shadow);
                                       submit_new_password.setSkin(new MyButtonSkin(submit_new_password));
                                       
                                       Button back_to_dropbox = new Button("<Back");
                                       back_to_dropbox.setLayoutX(150);
                                       back_to_dropbox.setLayoutY(120);
                                       back_to_dropbox.setPrefWidth(80);
                                       back_to_dropbox.setPrefHeight(20);
                                       back_to_dropbox.setTextFill(Color.DARKBLUE);
                                       back_to_dropbox.setFont(Font.font("Arial",FontWeight.BOLD, 13));
                                       back_to_dropbox.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                       DropShadow drop1shadow = new DropShadow(10, Color.GRAY);
                                       back_to_dropbox.setEffect(drop1shadow);
                                       back_to_dropbox.setSkin(new MyButtonSkin(back_to_dropbox));
                                       
                                       //submit password action
                                       submit_new_password.setOnAction(ev->{
                                           String o = old_password_tf.getText();
                                           String n = new_password_tf.getText();
                                           old_password_tf.clear();
                                           new_password_tf.clear();
                                           if(Login.change_password(reg,paswd,o,n))
                                           {
                                               Alert alert = new Alert(AlertType.INFORMATION);
                                               alert.setTitle("Alert");
                                               alert.setHeaderText(null);
                                               alert.setContentText("Password changed successfully.");
                                               alert.showAndWait();
                                           }
                                           else{
                                               Alert alert = new Alert(AlertType.INFORMATION);
                                               alert.setTitle("Alert");
                                               alert.setHeaderText(null);
                                               alert.setContentText("Wrong Password.");
                                               alert.showAndWait();
                                           }
                                       });
                                       
                                       //back button action
                                       back_to_dropbox.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText("Profile");
                                       });
                                       
                                       //setting scene for changing password
                                       GridPane change_pass = new GridPane();
                                       change_pass.add(old_password,0,0);
                                       change_pass.add(new_password,0,1);
                                       change_pass.add(old_password_tf,1,0);
                                       change_pass.add(new_password_tf,1,1);
                                       change_pass.add(submit_new_password,0,2);
                                       change_pass.add(back_to_dropbox,1,2);
                                       change_pass.setAlignment(Pos.CENTER);
                                       change_pass.setHgap(10);
                                       change_pass.setVgap(12);
                                       change_pass.setBackground(background);
                                       
                                       Scene changepass = new Scene(change_pass,1100,700);
                                       change_pass.setBackground(background);
                                       primaryStage.setScene(changepass);
                                       primaryStage.setTitle("Change Password");
                                   }
                                });
                                
                                //Mess dropdown box
                                ComboBox<String> messComboBox = new ComboBox<>();
                                messComboBox.getItems().addAll("Breakfast Menu", "Lunch Menu", "Dinner Menu","Request New Mess Card","Feedback","Complaints");
                                messComboBox.setPromptText("Mess");
                                messComboBox.setOnAction(event->{
                                    String selectedOption = messComboBox.getValue();
                                    if(selectedOption.equals("Breakfast Menu"))
                                    {
                                        Label breakfastLabel = new Label("              BREAKFAST");
                                        BoxBlur b = new BoxBlur();  
                                        b.setHeight(5);  
                                        b.setWidth(2);  
                                        b.setIterations(1);  
                                        breakfastLabel.setEffect(b);  
                                        breakfastLabel.setFont(Font.font("Arial Black", FontWeight.BOLD,20));
                                        breakfastLabel.setTextFill(Color.DARKBLUE);
                                        
                                        Label dash = new Label("---------------------------------------------------------------------");
                                        dash.setFont(Font.font("Arial Black", FontWeight.BOLD,15));
                                        dash.setTextFill(Color.GRAY);
                                        
                                        Label monday = new Label("•Monday: Toast with butter and jam");
                                        monday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        monday.setTextFill(Color.BLACK);
                                        
                                        Label tuesday = new Label("•Tuesday: Scrambled eggs with toast");
                                        tuesday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        tuesday.setTextFill(Color.BLACK);
                                        
                                        Label wednesday = new Label("•Wednesday: Oatmeal with fruits");
                                        wednesday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        wednesday.setTextFill(Color.BLACK);
                                        
                                        Label thursday = new Label("•Thursday: Pancakes with maple syrup");
                                        thursday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        thursday.setTextFill(Color.BLACK);
                                        
                                        Label friday = new Label("•Friday: Yogurt with granola and berries");
                                        friday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        friday.setTextFill(Color.BLACK);
                                        
                                        Label saturday = new Label("•Saturday: Vegetable omelette");
                                        saturday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        saturday.setTextFill(Color.BLACK);
                                        
                                        Label sunday = new Label("•Sunday: French toast with powdered sugar");
                                        sunday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        sunday.setTextFill(Color.BLACK);
                                        
                                        Button backToDropdownPage = new Button("< Back");
                                        backToDropdownPage.setLayoutX(150);
                                        backToDropdownPage.setLayoutY(120);
                                        backToDropdownPage.setPrefWidth(80);
                                        backToDropdownPage.setPrefHeight(20);
                                        backToDropdownPage.setTextFill(Color.DARKBLUE);
                                        backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 13));
                                        backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop1shadow = new DropShadow(10, Color.GRAY);
                                        backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                        
                                        backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText("Profile");
                                       });
                                        
                                        VBox menuVbox = new VBox(10);
                                        menuVbox.setPadding(new Insets(10));
                                        menuVbox.getChildren().addAll(breakfastLabel,dash,monday,tuesday,wednesday,thursday,friday,saturday,sunday, backToDropdownPage);
                                        menuVbox.setBackground(background);
                                        Scene menuScene = new Scene(menuVbox,1100,700);
                                        primaryStage.setScene(menuScene);
                                        primaryStage.setTitle("Breakfast Menu");
                                    }
                                    if(selectedOption.equals("Lunch Menu"))
                                    {
                                        Label lunchLabel = new Label("             LUNCH");
                                        BoxBlur b = new BoxBlur();  
                                           b.setHeight(5);  
                                           b.setWidth(2);  
                                          b.setIterations(1);  
                                       lunchLabel.setEffect(b); 
                                        lunchLabel.setFont(Font.font("Arial Black", FontWeight.BOLD,20));
                                        lunchLabel.setTextFill(Color.DARKBLUE);
                                        Label dash = new Label("---------------------------------------------------------------------");
                                        dash.setFont(Font.font("Arial Black", FontWeight.BOLD,15));
                                        dash.setTextFill(Color.GRAY);
                                        
                                        Label monday = new Label("•Monday: Chicken curry with rice");
                                        monday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        monday.setTextFill(Color.BLACK);
                                        
                                        Label tuesday = new Label("•Tuesday: Spaghetti with meatballs");
                                        tuesday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        tuesday.setTextFill(Color.BLACK);
                                        
                                        Label wednesday = new Label("•Wednesday: Vegetable stir-fry with tofu");
                                         wednesday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        wednesday.setTextFill(Color.BLACK);
                                        
                                        Label thursday = new Label("•Thursday: Vegetable stir-fry with tofu");
                                        thursday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        thursday.setTextFill(Color.BLACK);
                                        
                                        Label friday = new Label("•Friday: Beef burger with fries");
                                        friday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        friday.setTextFill(Color.BLACK);
                                        
                                        Label saturday = new Label("•Saturday: Paneer tikka masala with naan bread");
                                        saturday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        saturday.setTextFill(Color.BLACK);
                                        

                                        Label sunday = new Label("•Sunday: Baked chicken with roasted vegetables");
                                        sunday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        sunday.setTextFill(Color.BLACK);
                                        
                                       Button backToDropdownPage = new Button("< Back");backToDropdownPage.setLayoutX(150);
                                       backToDropdownPage.setLayoutY(120);
                                       backToDropdownPage.setPrefWidth(80);
                                       backToDropdownPage.setPrefHeight(20);
                                       backToDropdownPage.setTextFill(Color.DARKBLUE);
                                       backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 13));
                                       backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                       DropShadow drop1shadow = new DropShadow(10, Color.GRAY);
                                       backToDropdownPage.setEffect(drop1shadow);
                                       backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                        backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText("Profile");
                                       });
                                        
                                        VBox menuVbox = new VBox(10);
                                        menuVbox.setPadding(new Insets(10));
                                        menuVbox.getChildren().addAll(lunchLabel,dash,monday,tuesday,wednesday,thursday,friday,saturday,sunday, backToDropdownPage);
                                        menuVbox.setBackground(background);
                                        Scene menuScene = new Scene(menuVbox,1100,700);
                                        primaryStage.setScene(menuScene);
                                        primaryStage.setTitle("Lunch Menu");
                                    }
                                    if(selectedOption.equals("Dinner Menu"))
                                    {
                                        Label dinnerLabel = new Label("                DINNER");
                                        BoxBlur b = new BoxBlur();  
                                           b.setHeight(5);  
                                           b.setWidth(2);  
                                          b.setIterations(1);  
                                        dinnerLabel.setEffect(b); 
                                        dinnerLabel.setFont(Font.font("Arial Black", FontWeight.BOLD,20));
                                        dinnerLabel.setTextFill(Color.DARKBLUE);
                                        
                                        Label dash = new Label("---------------------------------------------------------------------");
                                        dash.setFont(Font.font("Arial Black", FontWeight.BOLD,15));
                                        dash.setTextFill(Color.GRAY);
                                        
                                        Label monday = new Label("•Monday: Lentil soup with bread");
                                        monday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        monday.setTextFill(Color.BLACK);
                                        
                                        Label tuesday = new Label("•Tuesday: Caesar salad with grilled chicken");
                                        tuesday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        tuesday.setTextFill(Color.BLACK);
                                        
                                        Label wednesday = new Label("•Wednesday: Sushi rolls with miso soup");
                                         wednesday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        wednesday.setTextFill(Color.BLACK);
                                        
                                        Label thursday = new Label("•Thursday: Beef stew with rice");
                                        thursday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        thursday.setTextFill(Color.BLACK);
                                        
                                        Label friday = new Label("•Friday: Margherita pizza with garlic bread");
                                        friday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        friday.setTextFill(Color.BLACK);
                                        
                                        Label saturday = new Label("•Saturday: Vegetable biryani with raita");
                                        saturday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        saturday.setTextFill(Color.BLACK);
                                        
                                        Label sunday = new Label("•Sunday: Roast beef with mashed potatoes");
                                        sunday.setFont(Font.font("Arial", FontWeight.BOLD,15));
                                        sunday.setTextFill(Color.BLACK);
                                        
                                        Button backToDropdownPage = new Button("< Back");backToDropdownPage.setLayoutX(150);
                                       backToDropdownPage.setLayoutY(120);
                                       backToDropdownPage.setPrefWidth(80);
                                       backToDropdownPage.setPrefHeight(20);
                                       backToDropdownPage.setTextFill(Color.DARKBLUE);
                                       backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 13));
                                       backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                       DropShadow drop1shadow = new DropShadow(10, Color.GRAY);
                                       backToDropdownPage.setEffect(drop1shadow);
                                        backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                        backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText("Profile");
                                       });
                                        
                                        VBox menuVbox = new VBox(10);
                                        menuVbox.setPadding(new Insets(10));
                                        menuVbox.getChildren().addAll(dinnerLabel,dash,monday,tuesday,wednesday,thursday,friday,saturday,sunday, backToDropdownPage);
                                        menuVbox.setBackground(background);
                                        Scene menuScene = new Scene(menuVbox,1100,700);
                                        primaryStage.setScene(menuScene);
                                        primaryStage.setTitle("Dinner Menu");
                                    }
                                    if(selectedOption.equals("Request New Mess Card"))
                                    {
                                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Newmesscardrequest.txt", true))) {
                                                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                writer.write("Timestamp: " + timestamp + "\n");
                                                writer.write("Request Received for:"+ "\n");
                                                writer.write("Name: " + Login.getHostellerName(reg,paswd) + "\n");
                                                writer.write("Room number: " + Login.getRoomNumber(reg,paswd) + "\n");
                                                writer.write("---------------------------------------------\n");
                                                } 
                                                catch (IOException excep) {
                                                System.err.println("Error: Failed to save the feedback.");
                                            }
                                        boolean sent = true;
                                        if(sent)
                                        {
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Success");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Request Sent!");
                                                    alert.showAndWait();
                                                    complaintTextArea.clear();
                                                    return;
                                        }
                                    }
                                    if(selectedOption.equals("Feedback"))
                                    {
                                        complaintTextArea = new TextArea();
                                        complaintTextArea.setWrapText(true);
                                        complaintTextArea.setPromptText("Enter your feedback (up to 200 words)");
                                        
                                        // Create a submit button
                                        Button feedbackSubmitButton = new Button("Submit");
                                        feedbackSubmitButton .setLayoutX(150);
                                        feedbackSubmitButton .setLayoutY(120);
                                        feedbackSubmitButton .setPrefWidth(80);
                                        feedbackSubmitButton .setPrefHeight(20);
                                        feedbackSubmitButton .setTextFill(Color.DARKBLUE);
                                        feedbackSubmitButton .setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        feedbackSubmitButton .setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop2shadow = new DropShadow(10, Color.GRAY);
                                        feedbackSubmitButton .setEffect(drop2shadow);
                                        feedbackSubmitButton.setOnAction(feedbackSubmission ->{
                                            String complaint = complaintTextArea.getText();
                                                if (complaint.trim().isEmpty()) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Feedback cannot be empty.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                if (complaint.split("\\s+").length > 200) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Feedback should not exceed 200 words.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                // Save the complaint to a file
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("messFeedbacks.txt", true))) {
                                                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                writer.write("Timestamp: " + timestamp + "\n");
                                                writer.write("Feedback: " + complaint + "\n");
                                                writer.write("---------------------------------------------\n");
                                                } 
                                                catch (IOException excep) {
                                                System.err.println("Error: Failed to save the feedback.");
                                                }
                                        
                                                // Simulate sending the complaint to the admin
                                                // We simply print the complaint on the console
                                                System.out.println("Received Feedback: " + complaint);
                                        
                                                boolean sentSuccessfully = true;
                                                if (sentSuccessfully) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Success");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Thank you for your feedback.");
                                                    alert.showAndWait();
                                                    complaintTextArea.clear();
                                                    return;
                                                }  
                                                else {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Failed to submit the feedback. Please try again later.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                                // Create a vertical layout for the text area and button
                                        });
                                        
                                        Button backToDropdownPage = new Button("< Back");
                                        backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                        backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText("Profile");
                                       });    
                                            VBox feedbackVbox = new VBox(10);
                                            feedbackVbox.setPadding(new Insets(10));
                                            feedbackVbox.getChildren().addAll(complaintTextArea, feedbackSubmitButton,backToDropdownPage);
                                            feedbackVbox.setBackground(background);
                                            Scene messFeedbackScene = new Scene(feedbackVbox,1100,700);
                                            primaryStage.setScene(messFeedbackScene);
                                            primaryStage.setTitle("Feedback Area");
                                    }
                                    if(selectedOption.equals("Complaints"))
                                    {
                                        // Create a text area for the complaint
                                        complaintTextArea = new TextArea();
                                        complaintTextArea.setWrapText(true);
                                        complaintTextArea.setPromptText("Enter your complaint (up to 200 words)");

                                        // Create a submit button
                                        Button complaintSubmitButton = new Button("Submit");
                                        complaintSubmitButton .setLayoutX(150);
                                        complaintSubmitButton.setLayoutY(120);
                                        complaintSubmitButton .setPrefWidth(80);
                                        complaintSubmitButton .setPrefHeight(20);
                                        complaintSubmitButton .setTextFill(Color.DARKBLUE);
                                        complaintSubmitButton .setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        complaintSubmitButton .setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop2shadow = new DropShadow(10, Color.GRAY);
                                        complaintSubmitButton .setEffect(drop2shadow);
                                            complaintSubmitButton.setOnAction(complaintSubmission ->{
                                                 String complaint = complaintTextArea.getText();
                                                if (complaint.trim().isEmpty()) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint cannot be empty.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                if (complaint.split("\\s+").length > 200) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint should not exceed 200 words.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                // Save the complaint to a file
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("messComplaints.txt", true))) {
                                                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                writer.write("Timestamp: " + timestamp + "\n");
                                                writer.write("Complaint: " + complaint + "\n");
                                                writer.write("---------------------------------------------\n");
                                                } 
                                                catch (IOException excep) {
                                                System.err.println("Error: Failed to save the complaint.");
                                                }
                                        
                                                // Simulate sending the complaint to the admin
                                                // We simply print the complaint on the console
                                                System.out.println("Received complaint: " + complaint);
                                        
                                                boolean sentSuccessfully = true;
                                                if (sentSuccessfully) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Success");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint submitted successfully.");
                                                    alert.showAndWait();
                                                    complaintTextArea.clear();
                                                    return;
                                                }  
                                                else {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Failed to submit the complaint. Please try again later.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                            });
                                            
                                            Button backToDropdownPage = new Button("< Back");
                                            backToDropdownPage .setLayoutX(150);
                                        backToDropdownPage.setLayoutY(120);
                                        backToDropdownPage.setPrefWidth(80);
                                        backToDropdownPage.setPrefHeight(20);
                                        backToDropdownPage.setTextFill(Color.DARKBLUE);
                                        backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop3shadow = new DropShadow(10, Color.GRAY);
                                        complaintSubmitButton .setEffect(drop3shadow);
                                            backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                            backToDropdownPage.setOnAction(back->{
                                               primaryStage.setScene(dropDown_page);
                                               profileComboBox.setPromptText("Profile");
                                           });
                                            
                                            // Create a vertical layout for the text area and button
                                            VBox complaintVbox = new VBox(10);
                                            complaintVbox.setPadding(new Insets(10));
                                            complaintVbox.getChildren().addAll(complaintTextArea, complaintSubmitButton,backToDropdownPage);
                                            complaintVbox.setBackground(background);
                                            Scene messComplaintScene = new Scene(complaintVbox,1100,700);
                                            primaryStage.setScene(messComplaintScene);
                                            primaryStage.setTitle("Complaint Area");
                                        }
                                });
                        

                                ComboBox<String> roomServiceComboBox = new ComboBox<>();
                                roomServiceComboBox.getItems().addAll("Room Cleaning Request","Feedback");
                                roomServiceComboBox.setPromptText("Room Service");
                                roomServiceComboBox.setOnAction(event->{
                                    String selectedOption = roomServiceComboBox.getValue();
                                    if(selectedOption.equals("Room Cleaning Request"))
                                    {
                                        // Create a text area for the complaint
                                        complaintTextArea = new TextArea();
                                        complaintTextArea.setWrapText(true);
                                        complaintTextArea.setPromptText("Enter your complaint (up to 200 words)");

                                        // Create a submit button
                                        Button complaintSubmitButton = new Button("Submit");
                                            complaintSubmitButton.setOnAction(complaintSubmission ->{
                                                 String complaint = complaintTextArea.getText();
                                                if (complaint.trim().isEmpty()) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint cannot be empty.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                if (complaint.split("\\s+").length > 200) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint should not exceed 200 words.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                // Save the complaint to a file
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("roomserviceComplaints.txt", true))) {
                                                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                writer.write("Timestamp: " + timestamp + "\n");
                                                writer.write("Complaint: " + complaint + "\n");
                                                writer.write("---------------------------------------------\n");
                                                } 
                                                catch (IOException excep) {
                                                System.err.println("Error: Failed to save the complaint.");
                                                }
                                        
                                                // Simulate sending the complaint to the admin
                                                // We simply print the complaint on the console
                                                System.out.println("Received complaint: " + complaint);
                                        
                                                boolean sentSuccessfully = true;
                                                if (sentSuccessfully) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Success");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint submitted successfully.");
                                                    alert.showAndWait();
                                                    complaintTextArea.clear();
                                                    return;
                                                }  
                                                else {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Failed to submit the complaint. Please try again later.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        });
                                            
                                        Button backToDropdownPage = new Button("< Back");
                                        backToDropdownPage .setLayoutX(150);
                                        backToDropdownPage.setLayoutY(120);
                                        backToDropdownPage.setPrefWidth(80);
                                        backToDropdownPage.setPrefHeight(20);
                                        backToDropdownPage.setTextFill(Color.DARKBLUE);
                                        backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop3shadow = new DropShadow(10, Color.GRAY);
                                        complaintSubmitButton .setEffect(drop3shadow);
                                        backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                            backToDropdownPage.setOnAction(back->{
                                               primaryStage.setScene(dropDown_page);
                                               profileComboBox.setPromptText("Profile");
                                           });
                                            
                                            // Create a vertical layout for the text area and button
                                            VBox complaintVbox = new VBox(10);
                                            complaintVbox.setPadding(new Insets(10));
                                            complaintVbox.getChildren().addAll(complaintTextArea, complaintSubmitButton,backToDropdownPage);
                                            complaintVbox.setBackground(background);
                                            Scene messComplaintScene = new Scene(complaintVbox,1100,700);
                                            primaryStage.setScene(messComplaintScene);
                                            primaryStage.setTitle("Complaint Area");

                                    }
                                    if(selectedOption.equals("Feedback"))
                                    {
                                       complaintTextArea = new TextArea();
                                       complaintTextArea.setWrapText(true);
                                       complaintTextArea.setPromptText("Enter your feedback (up to 200 words)");
                                        
                                        // Create a submit button
                                        Button feedbackSubmitButton = new Button("Submit");
                                        feedbackSubmitButton.setOnAction(feedbackSubmission ->{
                                            String complaint = complaintTextArea.getText();
                                                if (complaint.trim().isEmpty()) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Feedback cannot be empty.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                if (complaint.split("\\s+").length > 200) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Feedback should not exceed 200 words.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                // Save the complaint to a file
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("roomserviceFeedbacks.txt", true))) {
                                                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                writer.write("Timestamp: " + timestamp + "\n");
                                                writer.write("Feedback: " + complaint + "\n");
                                                writer.write("---------------------------------------------\n");
                                                } 
                                                catch (IOException excep) {
                                                System.err.println("Error: Failed to save the feedback.");
                                                }
                                        
                                                // Simulate sending the complaint to the admin
                                                // We simply print the complaint on the console
                                                System.out.println("Received Feedback: " + complaint);
                                        
                                                boolean sentSuccessfully = true;
                                                if (sentSuccessfully) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Success");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Thank you for your feedback.");
                                                    alert.showAndWait();
                                                    complaintTextArea.clear();
                                                    return;
                                                }  
                                                else {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Failed to submit the feedback. Please try again later.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                                // Create a vertical layout for the text area and button
                                        });
                                        
                                        Button backToDropdownPage = new Button("< Back");
                                        backToDropdownPage .setLayoutX(150);
                                        backToDropdownPage.setLayoutY(120);
                                        backToDropdownPage.setPrefWidth(80);
                                        backToDropdownPage.setPrefHeight(20);
                                        backToDropdownPage.setTextFill(Color.DARKBLUE);
                                        backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop3shadow = new DropShadow(10, Color.GRAY);
                                        feedbackSubmitButton .setEffect(drop3shadow);
                                        backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                        
                                        backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText("Profile");
                                       });    
                                        VBox feedbackVbox = new VBox(10);
                                            feedbackVbox.setPadding(new Insets(10));
                                            feedbackVbox.getChildren().addAll(complaintTextArea, feedbackSubmitButton,backToDropdownPage);
                                            feedbackVbox.setBackground(background);
                                            Scene messFeedbackScene = new Scene(feedbackVbox,1100,700);
                                            primaryStage.setScene(messFeedbackScene);
                                            primaryStage.setTitle("Feedback Area");
                                    }
                                });


                                ComboBox<String> laundryComboBox = new ComboBox<>();
                                laundryComboBox.getItems().addAll("Feedback","Complaint","Reciept");
                                laundryComboBox.setPromptText("Laundry");
                                laundryComboBox.setOnAction(event->{
                                    String selectedOption = laundryComboBox.getValue();
                                    if(selectedOption.equals("Complaint"))
                                    {
                                         // Create a text area for the complaint
                                        complaintTextArea = new TextArea();
                                        complaintTextArea.setWrapText(true);
                                        complaintTextArea.setPromptText("Enter your complaint (up to 200 words)");

                                        // Create a submit button
                                        Button complaintSubmitButton = new Button("Submit");
                                            complaintSubmitButton.setOnAction(complaintSubmission ->{
                                                 String complaint = complaintTextArea.getText();
                                                if (complaint.trim().isEmpty()) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint cannot be empty.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                if (complaint.split("\\s+").length > 200) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint should not exceed 200 words.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                // Save the complaint to a file
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("laundryComplaints.txt", true))) {
                                                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                writer.write("Timestamp: " + timestamp + "\n");
                                                writer.write("Complaint: " + complaint + "\n");
                                                writer.write("---------------------------------------------\n");
                                                } 
                                                catch (IOException excep) {
                                                System.err.println("Error: Failed to save the complaint.");
                                                }
                                        
                                                // Simulate sending the complaint to the admin
                                                // We simply print the complaint on the console
                                                System.out.println("Received complaint: " + complaint);
                                        
                                                boolean sentSuccessfully = true;
                                                if (sentSuccessfully) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Success");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Complaint submitted successfully.");
                                                    alert.showAndWait();
                                                    complaintTextArea.clear();
                                                    return;
                                                }  
                                                else {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Failed to submit the complaint. Please try again later.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        });
                                            
                                        Button backToDropdownPage = new Button("< Back");
                                        backToDropdownPage .setLayoutX(150);
                                        backToDropdownPage.setLayoutY(120);
                                        backToDropdownPage.setPrefWidth(80);
                                        backToDropdownPage.setPrefHeight(20);
                                        backToDropdownPage.setTextFill(Color.DARKBLUE);
                                        backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop3shadow = new DropShadow(10, Color.GRAY);
                                        complaintSubmitButton .setEffect(drop3shadow);
                                        backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                            backToDropdownPage.setOnAction(back->{
                                               primaryStage.setScene(dropDown_page);
                                               profileComboBox.setPromptText("Profile");
                                           });
                                            
                                            // Create a vertical layout for the text area and button
                                            VBox complaintVbox = new VBox(10);
                                            complaintVbox.setPadding(new Insets(10));
                                            complaintVbox.getChildren().addAll(complaintTextArea, complaintSubmitButton,backToDropdownPage);
                                            complaintVbox.setBackground(background);
                                            Scene messComplaintScene = new Scene(complaintVbox,1100,700);
                                            primaryStage.setScene(messComplaintScene);
                                            primaryStage.setTitle("Complaint Area");
                                    }
                                    if(selectedOption.equals("Feedback"))
                                    {
                                        complaintTextArea = new TextArea();
                                       complaintTextArea.setWrapText(true);
                                       complaintTextArea.setPromptText("Enter your feedback (up to 200 words)");
                                        
                                        // Create a submit button
                                        Button feedbackSubmitButton = new Button("Submit");
                                        feedbackSubmitButton.setOnAction(feedbackSubmission ->{
                                            String complaint = complaintTextArea.getText();
                                                if (complaint.trim().isEmpty()) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Feedback cannot be empty.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                if (complaint.split("\\s+").length > 200) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error!");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Feedback should not exceed 200 words.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                        
                                                // Save the complaint to a file
                                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("laundryFeedbacks.txt", true))) {
                                                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                                writer.write("Timestamp: " + timestamp + "\n");
                                                writer.write("Feedback: " + complaint + "\n");
                                                writer.write("---------------------------------------------\n");
                                                } 
                                                catch (IOException excep) {
                                                System.err.println("Error: Failed to save the feedback.");
                                                }
                                        
                                                // Simulate sending the complaint to the admin
                                                // We simply print the complaint on the console
                                                System.out.println("Received Feedback: " + complaint);
                                        
                                                boolean sentSuccessfully = true;
                                                if (sentSuccessfully) {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Success");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Thank you for your feedback.");
                                                    alert.showAndWait();
                                                    complaintTextArea.clear();
                                                    return;
                                                }  
                                                else {
                                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                    alert.setTitle("Error");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText("Failed to submit the feedback. Please try again later.");
                                                    alert.showAndWait();
                                                    return;
                                                }
                                                // Create a vertical layout for the text area and button
                                        });
                                        
                                        Button backToDropdownPage = new Button("< Back");
                                        backToDropdownPage .setLayoutX(150);
                                        backToDropdownPage.setLayoutY(120);
                                        backToDropdownPage.setPrefWidth(80);
                                        backToDropdownPage.setPrefHeight(20);
                                        backToDropdownPage.setTextFill(Color.DARKBLUE);
                                        backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                        DropShadow drop3shadow = new DropShadow(10, Color.GRAY);
                                        feedbackSubmitButton .setEffect(drop3shadow);
                                        backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                        backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                           profileComboBox.setPromptText("Profile");
                                       });    
                                        VBox feedbackVbox = new VBox(10);
                                            feedbackVbox.setPadding(new Insets(10));
                                            feedbackVbox.getChildren().addAll(complaintTextArea, feedbackSubmitButton,backToDropdownPage);
                                            feedbackVbox.setBackground(background);
                                            Scene messFeedbackScene = new Scene(feedbackVbox,1100,700);
                                            primaryStage.setScene(messFeedbackScene);
                                            primaryStage.setTitle("Feedback Area");
                                    }
                                    if(selectedOption.equals("Reciept"))
                                    {
                                       nameLabel.setText("NAME: " +  Login.getHostellerName(reg,paswd));
                                       regNumberLabel.setText("REGISTRATION NUMBER: " + Login.getRegistrationNumber(reg,paswd));
                                       phoneNumberLabel.setText("PHONE NUMBER: " +  Login.getPhoneNumber(reg,paswd));
                                       roomNumberLabel.setText("ROOM NUMBER: " +  Login.getRoomNumber(reg,paswd));
                                       hostelBlockLabel.setText("HOSTEL BLOCK: " +  Login.getHostelBlock(reg,paswd));
                                       
                                       Button backToDropdownPage = new Button("< Back");
                                       backToDropdownPage .setLayoutX(150);
                                        backToDropdownPage.setLayoutY(120);
                                        backToDropdownPage.setPrefWidth(80);
                                        backToDropdownPage.setPrefHeight(20);
                                        backToDropdownPage.setTextFill(Color.DARKBLUE);
                                        backToDropdownPage.setFont(Font.font("Arial",FontWeight.BOLD, 12));
                                        backToDropdownPage.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                       backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                        backToDropdownPage.setOnAction(back->{
                                           primaryStage.setScene(dropDown_page);
                                        });
                                        VBox profile_display=new VBox();
                                       BorderPane for_profile_bg = new BorderPane();
                                       profile_display.getChildren().addAll(nameLabel,regNumberLabel,phoneNumberLabel,laundrynumberLabel ,roomNumberLabel,hostelBlockLabel,backToDropdownPage);
                                       profile_display.setPadding(new Insets(15));
                                       profile_display.setSpacing(10);
                                       for_profile_bg.setCenter(profile_display);

                                       Scene scene = new Scene(for_profile_bg,400,300);
                                       for_profile_bg.setBackground(background);
                                       primaryStage.setScene(scene);  
                                       primaryStage.setTitle("Laundry bill");
                                    }
                                });

                                //logout button
                                Button logout = new Button("Logout");
                                logout.setLayoutX(150);
                                logout.setLayoutY(120);
                                logout.setPrefWidth(90);
                                logout.setPrefHeight(30);
                                logout.setTextFill(Color.DARKBLUE);
                                logout.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                                logout.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                logout.setOnAction(logoutt->{
                                    primaryStage.setScene(choicePage);
                                });
                                // Add the dropdown boxes to the VBox
                                dropdownBox.getChildren().addAll(profileComboBox, messComboBox, roomServiceComboBox,laundryComboBox,logout);

                                // Set VBox alignment to top-left
                                //BorderPane.setAlignment(dropdownBox, Pos.TOP_LEFT);

                                // Add the VBox to the center-left of the main page
                                mainPagePane.setLeft(dropdownBox);
                                mainPagePane.setBackground(background);
                                dropDown_page = new Scene(mainPagePane,1100,700);   
                                primaryStage.setScene(dropDown_page);  
                                primaryStage.setTitle("Home Page");  
                        }
                    }
                }); 
                                        
                root.setBackground(background);
                Scene scene = new Scene(root,400,300);   
                primaryStage.setScene(scene);  
                primaryStage.setTitle("Login Page");
            }
        });

        adminbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                Label username = new Label("Username:");
                TextField tfName = new TextField();
                Label userPwd = new Label("Password:");
                PasswordField Pwd = new PasswordField();
                
                GridPane root = new GridPane();
                root.add(username, 0, 0);
                root.add(tfName, 1, 0);
                root.add(userPwd, 0, 1);
                root.add(Pwd, 1, 1);
                root.add(login_btn, 1, 2);  
                root.setAlignment(Pos.CENTER);
                root.setVgap(10);
                root.setHgap(5);
                
                login_btn.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent e)
                    {
                        reg=tfName.getText();
                        paswd=Pwd.getText();
                        tfName.clear();
                        Pwd.clear();
                        
                        if(Login.admin_login(reg,paswd))
                        {
                            // Create a BorderPane to hold the main page components
                                BorderPane mainPagePane = new BorderPane();

                                // Create a VBox to hold the dropdown boxes
                                VBox dropdownBox = new VBox();
                                
                                dropdownBox.setSpacing(10);
                                dropdownBox.setPadding(new Insets(20));
                                dropdownBox.setAlignment(Pos.TOP_LEFT);

                                // Create the dropdown boxes
                                ComboBox<String> complaintsComboBox = new ComboBox<>();
                                complaintsComboBox.getItems().add("View Complaints");
                                complaintsComboBox.setPromptText("Complaints");
                                complaintsComboBox.setOnAction(event->{
                                    String selectedOption = complaintsComboBox.getValue();
                                    if(selectedOption.equals("View Complaints"))
                                    {
                                        complaintsTextArea = new TextArea();
                                        complaintsTextArea.setWrapText(true);
                                        complaintsTextArea.setEditable(false);
                                
                                        // Load and display the complaints
                                         StringBuilder complaintsBuilder = new StringBuilder();
                                         if(reg.equals("Zenith_mess")){
                                         try (BufferedReader reader = new BufferedReader(new FileReader("messComplaints.txt"))) {
                                         String line;
                                        while ((line = reader.readLine()) != null) {
                                            complaintsBuilder.append(line).append("\n");
                                        }
                                    } catch (IOException excep) {
                                        System.err.println("Error: Failed to load the complaints.");
                                    }
                                }
                                else if(reg.equals("360_cleaning")){
                                    try (BufferedReader reader = new BufferedReader(new FileReader("roomserviceComplaints.txt"))) {
                                         String line;
                                        while ((line = reader.readLine()) != null) {
                                            complaintsBuilder.append(line).append("\n");
                                        }
                                    } catch (IOException excep) {
                                        System.err.println("Error: Failed to load the complaints.");
                                    }
                                }
                                else if(reg.equals("chota_dhobi_laundry")){
                                    try (BufferedReader reader = new BufferedReader(new FileReader("laundryComplaints.txt"))) {
                                         String line;
                                        while ((line = reader.readLine()) != null) {
                                            complaintsBuilder.append(line).append("\n");
                                        }
                                    } catch (IOException excep) {
                                        System.err.println("Error: Failed to load the complaints.");
                                    }
                                }
                                    complaintsTextArea.setText(complaintsBuilder.toString());
                                    
                                    Button backToDropdownPage = new Button("< Back");
                                    backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                    backToDropdownPage.setOnAction(back->{
                                            primaryStage.setScene(dropDown_page);
                                    });
                                
                                
                                    // Create a vertical layout for the text area
                                    VBox vbox = new VBox(10);
                                    vbox.setPadding(new Insets(10));
                                    vbox.getChildren().addAll(complaintsTextArea,backToDropdownPage);
                                    primaryStage.setScene(new Scene(vbox, 400, 300));
                                    primaryStage.setTitle("Complaint Area");
                                    }
                                });
                                
                                
                                ComboBox<String> feedbacksComboBox = new ComboBox<>();
                                feedbacksComboBox.getItems().addAll("View Feedbacks");
                                feedbacksComboBox.setPromptText("Feedbacks");
                                feedbacksComboBox.setOnAction(event->{
                                    String selectedOption = feedbacksComboBox.getValue();
                                    if(selectedOption.equals("View Feedbacks"))
                                    {
                                        complaintsTextArea = new TextArea();
                                        complaintsTextArea.setWrapText(true);
                                        complaintsTextArea.setEditable(false);
                                
                                        // Load and display the complaints
                                         StringBuilder complaintsBuilder = new StringBuilder();
                                         if(reg.equals("Zenith_mess")){
                                         try (BufferedReader reader = new BufferedReader(new FileReader("messFeedbacks.txt"))) {
                                         String line;
                                        while ((line = reader.readLine()) != null) {
                                            complaintsBuilder.append(line).append("\n");
                                        }
                                    } catch (IOException excep) {
                                        System.err.println("Error: Failed to load the feedbacks.");
                                    }
                                }
                                else if(reg.equals("360_cleaning")){
                                    try (BufferedReader reader = new BufferedReader(new FileReader("roomserviceFeedbacks.txt"))) {
                                         String line;
                                        while ((line = reader.readLine()) != null) {
                                            complaintsBuilder.append(line).append("\n");
                                        }
                                    } catch (IOException excep) {
                                        System.err.println("Error: Failed to load the feedbacks.");
                                    }
                                }
                                else if(reg.equals("chota_dhobi_laundry")){
                                    try (BufferedReader reader = new BufferedReader(new FileReader("laundryFeedbacks.txt"))) {
                                         String line;
                                        while ((line = reader.readLine()) != null) {
                                            complaintsBuilder.append(line).append("\n");
                                        }
                                    } catch (IOException excep) {
                                        System.err.println("Error: Failed to load the feedbacks.");
                                    }
                                }
                                    complaintsTextArea.setText(complaintsBuilder.toString());
                                    
                                    Button backToDropdownPage = new Button("< Back");
                                    backToDropdownPage.setSkin(new MyButtonSkin(backToDropdownPage));
                                    backToDropdownPage.setOnAction(back->{
                                            primaryStage.setScene(dropDown_page);
                                    });
                                
                                    // Create a vertical layout for the text area
                                    VBox vbox = new VBox(10);
                                    vbox.setPadding(new Insets(10));
                                    vbox.getChildren().addAll(complaintsTextArea,backToDropdownPage);
                                    vbox.setBackground(background);
                                    primaryStage.setScene(new Scene(vbox, 400, 300));
                                    primaryStage.setTitle("Feedback Area");
                                }
                            });
                        
                                
                                
                                
                                Button logout = new Button("Logout");
                                logout.setLayoutX(150);
                                logout.setLayoutY(120);
                                logout.setPrefWidth(90);
                                logout.setPrefHeight(30);
                                logout.setTextFill(Color.DARKBLUE);
                                logout.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                                logout.setBackground(new Background(new BackgroundFill(gradient, null, null)));
                                logout.setOnAction(logoutt->{
                                    primaryStage.setScene(choicePage);
                                });
                                
                                // Add the dropdown boxes to the VBox
                                    dropdownBox.getChildren().addAll(complaintsComboBox,feedbacksComboBox,logout);
                                


                                // Add the VBox to the center-left of the main page
                                mainPagePane.setLeft(dropdownBox);
                                mainPagePane.setBackground(background);
                                dropDown_page = new Scene(mainPagePane,1100,700);   
                                primaryStage.setScene(dropDown_page);  
                                primaryStage.setTitle("Home Page");
                        }
                        
                    }
                });
                
                Scene scene = new Scene(root,400,300); 
                root.setBackground(background);
                primaryStage.setScene(scene);  
                primaryStage.setTitle("Login Page");  
            }
        });
        
        
        choicePage = new Scene(grid,400,300);
        
        hbButtons.getChildren().addAll(hostellerbtn,adminbtn);
        hbButtons.setSpacing(15.0);
        hbButtons.setAlignment(Pos.CENTER);
        
        grid.add(choose,0,0);
        grid.add(hbButtons,0,1);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setBackground(background);
        
        primaryStage.setScene(choicePage);
        primaryStage.show();
    }
   
    public static void main(String[] args)
    {
        launch(args);
    }
}