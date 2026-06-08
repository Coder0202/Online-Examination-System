package org.OnlineExamSystem.repositiory;

import java.sql.*;

public class DBconfig {

    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public DBconfig() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/onlineexamsystem",
                "root",
                "Avi@1234"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}