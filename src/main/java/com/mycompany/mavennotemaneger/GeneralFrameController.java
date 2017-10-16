package com.mycompany.mavennotemaneger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


/**
 *
 * @author хай-фай
 */
public class GeneralFrameController implements Initializable {
    
    @FXML private TableView<Note> table_of_notes;
    @FXML private TableColumn<Note, String> dateTime;
    @FXML private TableColumn<Note, String> textNote;
   
    private MainApp mainApp;
    
    
    @FXML
    private void addNote(ActionEvent event) {
        
        try {
            mainApp.startNewNoteForm();
        } catch (IOException ex) {
            Logger.getLogger(GeneralFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //***********НАСТРОЙКА ТАБЛИЦЫ
               
        //*********Настраиваем политика заполнения области таблици при растягивании экрана
        //*********Столбцы будут  растягиваться изанимать всю область растянутой таблицы
        //
        table_of_notes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        

        
        table_of_notes.setItems(mainApp.notes);
        
 
        dateTime.setCellValueFactory(cellData ->  cellData.getValue().dateTimeProperty());
        textNote.setCellValueFactory(cellData ->  cellData.getValue().textNodeProperty());
        
    }    
    
    public void accessGeneralClass(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    
    
    
}
