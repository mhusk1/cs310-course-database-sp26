package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            // Register for courses
            if (conn.isValid(0)) {
               String query = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
               ps = conn.prepareStatement(query);
               
               ps.setInt(1, studentid);
               ps.setInt(2, termid);
               ps.setInt(3,crn);
               
               int newReg = ps.executeUpdate();
               
               if (newReg == 1) {
               
                result = true;
               }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                // Drop out of courses
                String query2 = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
                ps = conn.prepareStatement(query2);
                
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                
                int drop = ps.executeUpdate();
                
                if (drop == 1) {
                
                   result = true;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
      
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                  // Withdraw from all courses
               String query3 = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
               ps = conn.prepareStatement(query3);
               
               ps.setInt(1, studentid);
               ps.setInt(2, termid);
               
               int withdrawAll = ps.executeUpdate();
               
               if (withdrawAll > 0) {
               
                   result = true;
               } 
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            // Diplays all registered courses for a student
            if (conn.isValid(0)) {
                
                String query4 = "SELECT studentid, termid, crn FROM registration "
                        + "WHERE studentid = ? AND termid = ?";
                ps = conn.prepareStatement(query4);
                
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                rs = ps.executeQuery();
                rsmd = rs.getMetaData();
                
                
                result = DAOUtility.getResultSetAsJson(rs);
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
