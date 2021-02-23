package com.iris.util;
import com.iris.datasource.HikariCPDataSource;

import java.sql.*;
import java.util.Objects;

public class JDBCUtil {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Lx256464";

    public Connection getConnection(boolean useHikariCP) throws ClassNotFoundException, SQLException {
        if (useHikariCP) {
            return HikariCPDataSource.getConnection();
        }

        return getConnection();
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            // STEP 2: Register JDBC driver
            // Registering the driver is the process by which the Oracle driver's class
            // file is loaded into the memory, so it can be utilized as an implementation
            // of the JDBC interfaces.
            // The driver is automatically registered via the SPI and manual loading of
            // the driver class is generally unnecessary.
            // Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        return conn;
    }

    public void closeResourcesQuietly(ResultSet rs, Statement stmt, Connection conn) {
        //used within finally block used to close resources quietly
        try{
            if(rs!=null)
                rs.close();
        }catch(SQLException se2){
            se2.printStackTrace();
        }// nothing we can do

        try{
            if(stmt!=null)
                stmt.close();
        }catch(SQLException se2){
            se2.printStackTrace();
        }// nothing we can do

        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
    }

    public void rollback(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
