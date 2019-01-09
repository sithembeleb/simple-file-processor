package com.file.processor;

import java.sql.*;

public class DbConnection {

    static final String DB_URL = "jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    static final String USER = "sa";


    public static void connectToDatabase(final ProcessingReport processingReport) throws ClassNotFoundException {
        String createTable = "CREATE TABLE PROCESSING_REPORT (ID int auto_increment primary key, " +
                " FILE_TOTAL NUMBER(10,0), " +
                " FILENAME VARCHAR2(255 CHAR), " +
                " TERMINATION_TOTAL NUMBER(10,0), " +
                " TRACKING_TOTAL NUMBER(10,0), " +
                " TRANSACTION_TOTAL NUMBER(10,0))" ;

        String insert = String.format("Insert into PROCESSING_REPORT(ID, FILE_TOTAL, FILENAME, TERMINATION_TOTAL, TRACKING_TOTAL, TRANSACTION_TOTAL)" +
                "values (default, %s, '%s', %s, %s, %s)",
                processingReport.getFile(), processingReport.getFilename(), processingReport.getTermination(), processingReport.getTracking(),processingReport.getTransaction());
        ResultSet resultSet = null;

        try(Connection connection = DriverManager.getConnection(DB_URL, USER, "");
            Statement statement = connection.createStatement()) { ;
            statement.executeUpdate(createTable);
            statement.executeUpdate(insert);

            resultSet = statement.executeQuery("SELECT * from PROCESSING_REPORT");

            while (resultSet.next()){
                System.out.print("ID " + resultSet.getInt("ID") +" : ");
                System.out.print("FILE_TOTAL " + resultSet.getInt("FILE_TOTAL") +" : ");
                System.out.print("TERMINATION_TOTAL " + resultSet.getInt("TERMINATION_TOTAL") +" : ");
                System.out.print("TRACKING_TOTAL " + resultSet.getInt("TRACKING_TOTAL") +" : ");
                System.out.print("TRANSACTION_TOTAL " + resultSet.getInt("TRANSACTION_TOTAL") +" : ");
                System.out.print("FILENAME" + resultSet.getString("FILENAME") +" ");
                System.out.println();
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            }catch (SQLException e){
                e.getMessage();
            }

        }
    }


}
