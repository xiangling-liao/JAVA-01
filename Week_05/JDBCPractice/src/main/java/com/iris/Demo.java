package com.iris;

import com.iris.util.JDBCUtil;

import java.sql.*;

public class Demo {


    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println("select all: ");
        demo.selectAll();

        // 1. insertion
//        demo.insertEmployee(104, "Xiangling", "Liao", 26);
//        System.out.println("select all after insert 104: ");
//        demo.selectAll();

        // 2. update
       // demo.updateEmployeeFirstNameById(104, "Ligeng");

        // 3. delete
        //demo.deleteEmployeeById(104);

        // 4. batch processing
        //demo.batchProcessing();

    }

    private void selectAll() {
        JDBCUtil jdbcUtil = new JDBCUtil();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtil.getConnection(true);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            jdbcUtil.closeResourcesQuietly(rs, stmt, conn);
        }//end try
        System.out.println("Goodbye selectAll!");
    }

    /**
     * Insert an employee using preparedStatement and transaction
     * @param id
     * @param firstName
     * @param lastName
     * @param age
     */
    private void insertEmployee(int id, String firstName, String lastName, int age) {

        //step1: open a connection
        JDBCUtil jdbcUtil = new JDBCUtil();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = jdbcUtil.getConnection();
            //STEP 2: Execute a query
            System.out.println("Creating statement for insertion...");

            // Create SQL statement
            String SQL = "INSERT INTO Employees (id, first, last, age) " +
                "VALUES(?, ?, ?, ?)";

            // Create PrepareStatement object
            preparedStatement = conn.prepareStatement(SQL);

            //Set auto-commit to false
            conn.setAutoCommit(false);

            // Set the variables
            preparedStatement.setInt( 1, id );
            preparedStatement.setString( 2, firstName );
            preparedStatement.setString( 3, lastName );
            preparedStatement.setInt( 4, age );

            preparedStatement.executeUpdate();

            //Explicitly commit statements to apply changes
            conn.commit();

            //STEP 6: Clean-up environment
            preparedStatement.close();
            conn.close();

            selectAll();
        }catch(SQLException se){
            //Handle errors for JDBC by rollback the insertion
            jdbcUtil.rollback(conn);
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            jdbcUtil.closeResourcesQuietly(null, preparedStatement, conn);
        }//end try
        System.out.println("Goodbye insertion!");
    }

    private void updateEmployeeFirstNameById(int id, String firstName) {
        //step1: open a connection
        JDBCUtil jdbcUtil = new JDBCUtil();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = jdbcUtil.getConnection();
            //STEP 2: Execute a query
            System.out.println("Creating statement for insertion...");

            // Create SQL statement
            String SQL = "Update Employees SET first = ? WHERE id = ?";

            // Create PrepareStatement object
            preparedStatement = conn.prepareStatement(SQL);



            // Set the variables
            preparedStatement.setString( 1, firstName );
            preparedStatement.setInt( 2, id );

            //Set auto-commit to false
            conn.setAutoCommit(false);

            preparedStatement.executeUpdate();

            //Explicitly commit statements to apply changes
            conn.commit();

            //STEP 6: Clean-up environment
            preparedStatement.close();
            conn.close();

            selectAll();
        }catch(SQLException se){
            //Handle errors for JDBC by rollback the update
            jdbcUtil.rollback(conn);
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            jdbcUtil.closeResourcesQuietly(null, preparedStatement, conn);
        }//end try
        System.out.println("Goodbye update!");
    }

    private void deleteEmployeeById(int id) {
        //step1: open a connection
        JDBCUtil jdbcUtil = new JDBCUtil();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = jdbcUtil.getConnection();
            //STEP 2: Prepared a query
            System.out.println("Creating statement for insertion...");

            // Create SQL statement
            String sql = "DELETE FROM Employees " +
                    "WHERE id = ?";

            // Create PrepareStatement object
            preparedStatement = conn.prepareStatement(sql);

            // Set the variables
            preparedStatement.setInt( 1, id);

            //3. Set auto-commit to false
            conn.setAutoCommit(false);

            // 4. execute the query
            preparedStatement.executeUpdate();

            //5. Explicitly commit statements to apply changes
            conn.commit();

            //STEP 6: Clean-up environment
            preparedStatement.close();
            conn.close();

            selectAll();
        }catch(SQLException se){
            //Handle errors for JDBC by rollback the delete
            jdbcUtil.rollback(conn);
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            jdbcUtil.closeResourcesQuietly(null, preparedStatement, conn);
        }//end try
        System.out.println("Goodbye delete!");
    }

    private void batchProcessing() {
        //step1: open a connection
        JDBCUtil jdbcUtil = new JDBCUtil();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = jdbcUtil.getConnection();
            //STEP 2: Prepared a query
            // Create statement object
            stmt = conn.createStatement();

            // Set auto-commit to false
            conn.setAutoCommit(false);

            // Create SQL statement
            String SQL = "INSERT INTO Employees (id, first, last, age) " +
                    "VALUES(200,'Zia', 'Ali', 30)";
            // Add above SQL statement in the batch.
            stmt.addBatch(SQL);

            // Create one more SQL statement
            String SQL2 = "INSERT INTO Employees (id, first, last, age) " +
                    "VALUES(201,'Raj', 'Kumar', 35)";
            // Add above SQL statement in the batch.
            stmt.addBatch(SQL2);

            // Create one more SQL statement
            String SQL3 = "UPDATE Employees SET age = 35 " +
                    "WHERE id = 100";
            // Add above SQL statement in the batch.
            stmt.addBatch(SQL3);

            // Create an int[] to hold returned values
            int[] count = stmt.executeBatch();

            //Explicitly commit statements to apply changes
            conn.commit();

            selectAll();
        }catch(SQLException se){
            //Handle errors for JDBC by rollback the batch
            jdbcUtil.rollback(conn);
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            jdbcUtil.closeResourcesQuietly(null, stmt, conn);
        }//end try
        System.out.println("Goodbye batch processing!");
    }


}
