package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP26 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
              // Process for populating JsonArray()  
                ResultSetMetaData rsmd = rs.getMetaData();

                while (rs.next()) {
                JsonObject object2 = new JsonObject();
                
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String colName = rsmd.getColumnName(i);
                    String value = rs.getString(i);
                    
                    object2.put(colName, value);
                    
                }
                  records.add(object2);
                    
                }
               

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
