/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavennotemaneger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author хай-фай
 */
public class WriteToDataBase extends Task<Void>{
    
    
    final private Note  wtiteNote;


    public WriteToDataBase(Note wtiteNote) {
        this.wtiteNote = wtiteNote;
  
    }

    
    
   
    
    private void write(Note note) {
       
      
      
       //---------------------------------------
                  
          
        try {
            
          
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WriteToDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String url = "jdbc:jtds:sqlserver://localhost:1433;"
                + "database=EventWatch;";
        
        Connection con;
        try {
            con = DriverManager.getConnection(url);
            
            
            DatabaseMetaData  meta=con.getMetaData();
            Statement st= con.createStatement();
            
            
            String sql="INSERT INTO EventWatch.dbo.MyMessages " +
            "(EventDateTime,BodyMessage) VALUES ('" +
             note.getDateTime() + "','" +note.getTextNode()+
                   "' )";
            
            System.out.println(sql); 
           st.executeUpdate(sql);
       

       con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(WriteToDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       //-------------------------------------------------------------- 
        
    }                

    
    
    @Override
    protected Void call() throws Exception {
        
      
        
        write(wtiteNote);
       
                  
       return null;
    }
    
    protected void succeeded(){
    
        System.out.println("writeTreade is succeeded!");
    }
    
}
