package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FactoryDAO {

    private String urlBD;
    private String username;
    private String password;
    FactoryDAO(String urlBD, String username, String password)
    {
        this.urlBD = urlBD;
        this.username = username;
        this.password = password;
    }
    public static FactoryDAO getInstance()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
        FactoryDAO instance = new FactoryDAO("jdbc:mysql://localhost:3306/unabelissimabasa","root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(urlBD, username,password);
    }
}
