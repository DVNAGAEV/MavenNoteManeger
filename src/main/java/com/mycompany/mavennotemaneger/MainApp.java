package com.mycompany.mavennotemaneger;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author хай-фай
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private Task readTask;
    
      public static ObservableList<Note> notes = FXCollections.observableArrayList();
    
   

    public MainApp() {
        
 
         readTask=new ReadFromDataBase(notes);
            Thread readThread=new Thread(readTask);
            readThread.setDaemon(true);
            readThread.start();
           

    }
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        primaryStage=stage;
       
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("/fxml/GeneralFrame.fxml"));
        GridPane rootLayout = (GridPane) loader.load();

        
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
       
        // Даём контроллеру доступ к главному прилодению.
        GeneralFrameController controller = loader.getController();
        controller.accessGeneralClass(this);
        
        primaryStage.show();
        
          
        
    }

    
    public void startNewNoteForm() throws IOException {
    
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/NewNote.fxml"));
        GridPane page = (GridPane) loader.load();

        
        Stage newNoteStage = new Stage();
        newNoteStage.setTitle("New note forma");
        newNoteStage.initModality(Modality.WINDOW_MODAL);
        newNoteStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        newNoteStage.setScene(scene);
        
        
        NewNoteController controller = loader.getController();
        controller.newNoteStage(newNoteStage);
        
        newNoteStage.showAndWait();
        
    }
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    
    
}
