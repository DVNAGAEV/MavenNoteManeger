/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavennotemaneger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
 * @author хай-фай
 */
public class ReadFromDataBase extends Task<Void>{
    
    
    final private ObservableList<Note> notes;

    public ReadFromDataBase(ObservableList<Note> notes) {
        this.notes = notes;
    }

    
   
    
    private void read() {
        
        
            
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadFromDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String url = "jdbc:jtds:sqlserver://localhost:1433;"
                + "database=EventWatch;";
        
        Connection con;
        try {
            con = DriverManager.getConnection(url);
            
            //payments.clear();
            DatabaseMetaData  meta=con.getMetaData();
            Statement st= con.createStatement();
            
            String sql="SELECT * FROM EventWatch.dbo.MyMessages ";
            
            System.out.println(sql); 
            st.executeQuery(sql);
            ResultSet rs=st.executeQuery(sql);
       
       
        while (rs.next()){
           notes.add(new Note(rs.getString("EventDateTime"), rs.getString("BodyMessage")));
           
        }
       

       con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReadFromDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       //--------------------------------------------------------------     
        
        
        
    }  

    @Override
    protected Void call() throws Exception {
        System.out.println("SartToRead"); 
        read();
     return null;
    }
    
    
    protected void succeeded(){
    
        System.out.println("readTreade is succeeded!");
    }
    
}
