/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cppk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author denis
 */
public class ConnectionDB {
    
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public void SQLDatabaseConnection() {
        String connectionUrl = "jdbc:sqlserver://192.168.0.200:1433;databaseName=dbedu";
/*                "jdbc:sqlserver://192.168.0.200:1433;"
                        + "database=dbedu.dbo;"
                        + "user=public@SERVER1C;"
                        + "password=yourpassword;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
*/        
        String insertSql = "INSERT INTO dbedu.dbo.person (surname, firstname, patronymic, datebirth) VALUES "
                + "('Filichev', 'Denis', 'Andreevich', '2016-01-01');";

        ResultSet resultSet = null;
        System.out.println("Пробуем подключиться");
        try (Connection connection = DriverManager.getConnection(connectionUrl, "sa", "Itw09182002");
                ) {
            System.out.println("Соединение установлено");
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            
            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();
            prepsInsertProduct.close();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
