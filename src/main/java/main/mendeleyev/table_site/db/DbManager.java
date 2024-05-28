package main.mendeleyev.table_site.db;

import main.mendeleyev.table_site.models.Element;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbManager {
    private static Connection connection;
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
            connection.close();
        }
        return elements;
    }

    public static Element getElementById(int id) throws SQLException, IOException {
        Element element = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM elements WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
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
            }
        }
        return element;
    }


    public static void addElement(Element element) {
        try (Connection connection = DatabaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO elements (element_id, " +
                    "name, \"group\", period, icon, formula, valency) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, element.getElement_id());
            statement.setString(2, element.getName());
            statement.setString(3, element.getGroup());
            statement.setString(4, element.getPeriod());
            statement.setString(5, element.getIcon());
            statement.setString(6, element.getFormula());
            statement.setString(7, element.getValency());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteElement(int id) {
        System.out.println("Attempting to delete element with ID: " + id);
        try(Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM elements WHERE id = ?");
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No rows affected. Element not found or could not be deleted.");
            } else {
                System.out.println("Deleted " + affectedRows + " rows.");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public static void updateElement(Element element) {
        System.out.println("Updating element with ID: " + element.getId());
        System.out.println("Element details: " + element);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE elements SET element_id = ?, name = ?, \"group\" = ?, period = ?, icon = ?, formula = ?, valency = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, element.getElement_id());
                statement.setString(2, element.getName());
                statement.setString(3, element.getGroup());
                statement.setString(4, element.getPeriod());
                statement.setString(5, element.getIcon());
                statement.setString(6, element.getFormula());
                statement.setString(7, element.getValency());
                statement.setInt(8, element.getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating element failed, no rows affected.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}
