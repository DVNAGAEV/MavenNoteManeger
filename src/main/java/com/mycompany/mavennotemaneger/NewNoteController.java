/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavennotemaneger;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author хай-фай
 */
public class NewNoteController implements Initializable {
    @FXML private TextField textField; 
    @FXML private Task wtiteTask;
    
    private Stage newNoteStage;
    private MainApp mainApp; 
    
    private final String datePattern = "dd.MM.yyyy HH:mm:ss";
   
    /** Форматировщик даты. */
    private    final DateTimeFormatter dateFormater = 
    DateTimeFormatter.ofPattern(datePattern); 
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void newNoteStage(Stage newNoteStage) {
        this.newNoteStage = newNoteStage;
        
    }
    
    
    
    //Форматирование даты
    public String formatData(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        //System.out.println("In formater"+date);
        return dateFormater.format(date);
    }
    


    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void clickOk() {
        
        Note newNote= new Note();
         
        
        newNote.setDateTime(formatData(LocalDateTime.now()));
        newNote.setTextNode(textField.getText());
        
        
        boolean goodAdd=false;
        
        if (newNote.getTextNode().length()>0) {
            wtiteTask=new WriteToDataBase(newNote);
            Thread writeThread=new Thread(wtiteTask);
            writeThread.setDaemon(true);
            writeThread.start();
            goodAdd= mainApp.notes.add(newNote); 
        
        if (goodAdd == true)  System.out.println("goodAdd to table_of_note is succeeded");
        }
        
        
        newNoteStage.close();
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void clickCancel() {
        newNoteStage.close();
    }
    
    @FXML
    private void inputText() {
       
     
    int size=100;   
       
       
       if (textField.getLength()>size) {
       
       textField.deleteText(size,size+1);
                  
       
       }
       
    }

    
}
