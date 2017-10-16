/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavennotemaneger;



/**
 *
 * @author хай-фай
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {
    
    private  StringProperty dateTime;
    private  StringProperty textNode;

    public Note() {
        
        this(null, null);
    }

    public Note(String dateTime, String  textNode) {
        this.dateTime = new SimpleStringProperty(dateTime);
        this.textNode = new SimpleStringProperty(textNode);
    
    }
    
  
    public String getDateTime() { return dateTime.get();}
    public void setDateTime(String dateTime) {this.dateTime.set(dateTime);}
    public StringProperty dateTimeProperty() {return dateTime;}
   
    public String getTextNode() {return textNode.get();}
    public void setTextNode(String textNode) {this.textNode.set(textNode);}
    public StringProperty textNodeProperty() {return textNode;}
    
    
    
    
}
