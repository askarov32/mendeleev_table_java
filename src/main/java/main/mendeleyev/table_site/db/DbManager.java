package main.mendeleyev.table_site.db;

import main.mendeleyev.table_site.models.Element;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbManager {
    public static ArrayList<Element> getAllElements() throws SQLException, IOException {
        ArrayList<Element> elements = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM elements";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Element element = new Element(
                resultSet.getInt("id"),
                resultSet.getInt("element_id"),
                resultSet.getString("name"),
                resultSet.getString("group"),
                resultSet.getString("period"),
                resultSet.getString("icon"),
                resultSet.getString("formula"),
                resultSet.getString("valency")
                );
                elements.add(element);
            }
        } finally {
            DatabaseConnection.closeConnection();
        }
        return elements;
    }

    public static Element getElementById(int id) throws SQLException, IOException {
        Element element = null;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SEELECT * FROM elements where element_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(2, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    element = new Element(
                            resultSet.getInt("id"),
                            resultSet.getInt("element_id"),
                            resultSet.getString("name"),
                            resultSet.getString("group"),
                            resultSet.getString("period"),
                            resultSet.getString("icon"),
                            resultSet.getString("formula"),
                            resultSet.getString("valency")
                    );
                }
            }
            finally {
                DatabaseConnection.closeConnection();
            }
        }
        return element;
    }


}
