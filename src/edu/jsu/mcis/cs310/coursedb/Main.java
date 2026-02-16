package edu.jsu.mcis.cs310.coursedb;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsoner;
import edu.jsu.mcis.cs310.coursedb.dao.*;

public class Main {
    
    private static final String USERNAME = "nobody@jsu.edu";
    
    public static void main(String[] args) {
        
        // Create DAO Factory
        
        DAOFactory daoFactory = new DAOFactory("coursedb");
        
        // Test Connection
        
        if ( !daoFactory.isClosed() ) {
            
            System.out.println("Connected Successfully!");
            
            SectionDAO sectionDao = daoFactory.getSectionDAO();
            String sectionResult = sectionDao.find(1, "CS", "201");
            System.out.println(sectionResult);
            
            StudentDAO studentDao = daoFactory.getStudentDAO();
            int studentid = studentDao.find("nobody@jsu.edu");
            System.out.println("Student ID:" + studentid);
            
            RegistrationDAO registrationDaoCreate = daoFactory.getRegistrationDAO();
            Boolean registrationCreateResult = registrationDaoCreate.create(studentid, 1, 20556);
            if (registrationCreateResult) {
            System.out.println("Course Registered");
            } 
            
            RegistrationDAO registrationDaoCreate2 = daoFactory.getRegistrationDAO();
            Boolean registrationCreateResult2 = registrationDaoCreate2.create(studentid, 1, 20540);
            if (registrationCreateResult2) {
            System.out.println("Course Registered");
            }
            
           RegistrationDAO registrationDaoDelete = daoFactory.getRegistrationDAO();
           Boolean registrationDeleteResult = registrationDaoDelete.delete(studentid, 1, 20556);
           if (registrationDeleteResult){
           System.out.println("Course dropped");
           }
           
           RegistrationDAO registrationDaoWithdraw = daoFactory.getRegistrationDAO();
           Boolean registrationDaoWithdrawResult = registrationDaoWithdraw.delete(studentid,1);
           if (registrationDaoWithdrawResult) {
           System.out.println("All course dropped.");
           }
           
           RegistrationDAO registrationDaoList = daoFactory.getRegistrationDAO();
           String registrationListResult = registrationDaoList.list(studentid, 1);
           System.out.println(registrationListResult);
           
        }
        
    }
    
}