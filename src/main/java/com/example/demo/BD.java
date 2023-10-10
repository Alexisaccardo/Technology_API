package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {
    public BD() {
    }

    String error_register = "No se pudo registrar el producto";
    String error_edit = "No se puedo editar el producto";
    String search_suppliers = "No se encontró un provedor registrado con el codigo especificado.";

    public static Product Search_product(String code) throws SQLException, ClassNotFoundException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/technologys";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consult_SQL = "SELECT * FROM tecnology WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(consult_SQL);
        statement.setString(1, code); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet2 = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet2.next()) {

            code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String id_supplier = resultSet2.getString("id_supplier");
            String description = resultSet2.getString("description");
            String value = resultSet2.getString("value");


            Product product = new Product(code, name, id_supplier, description, value);

            return product;
        }
        // Cerrar recursos
        resultSet2.close();
        statement.close();
        connection.close();

        return null;
    }

    public static int Delete_product(String code) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/technologys";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM tecnology WHERE code = ?";

        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, code);

        int f = prepareStatement.executeUpdate();

        System.out.println("Usuario eliminado correctamente");
        return f;
    }

    public static int Delete() throws ClassNotFoundException, SQLException {
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/technologys";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM tecnology";

        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);

        int f = prepareStatement.executeUpdate();

        System.out.println("Usuarios eliminado correctamente");
        return f;

    }


    public String Search_supplier(String id_supplier) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/technologys";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM suppliers WHERE id_supplier = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, id_supplier); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {
            id_supplier = resultSet.getString("id_supplier");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String city = resultSet.getString("city");

            return id_supplier;

        } else {
            System.out.println(search_suppliers);
        }
        System.out.println("El codigo del provedor coincide con: " + id_supplier);

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

        return "";

    }

    public Product Register(String code, String name, String id_supplier, String description, String value) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/technologys";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tecnology");

            // Sentencia INSERT
            String sql = "INSERT INTO tecnology (code , name, id_supplier, description, value) VALUES (?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, id_supplier);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, value);

            // Ejecutar la sentencia
            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("Empleado registrado de manera exitosa.");
                return new Product(code, name, id_supplier, description, value);
            } else {
                System.out.println(error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Product(null, null, null, null, null);
    }

    public Product Edit_product(String code, String name, String id_supplier, String description, String value) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/technologys";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consult = "UPDATE tecnology SET name = ?, id_supplier = ?, description = ?, value = ? WHERE code = ?";

        PreparedStatement preparedStatement = connection2.prepareStatement(consult);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, id_supplier);
        preparedStatement.setString(3, description);
        preparedStatement.setString(4, value);
        preparedStatement.setString(5, code);

        int files = preparedStatement.executeUpdate();
        if (files > 0) {
            System.out.println("Producto actualizado de manera exitosa");
            return new Product(code, name, id_supplier, description, value);
        } else {
            System.out.println(error_edit);
        }
        preparedStatement.close();
        connection2.close();
        return new Product(null, null, null, null, null);
    }

    public List<Product> Search_products() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/technologys";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM tecnology");

        List<Product> list = new ArrayList<>();

        while(resultSet2.next()){
            String code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String id_supplier = resultSet2.getString("id_supplier");
            String description = resultSet2.getString("description");
            String value = resultSet2.getString("value");

            System.out.println("este es el codigo "+code+  " nombre: "+name+ " id proveedor: "+id_supplier+ " descripcion: " + description + " valor: " + value);

            Product product = new Product(code, name, id_supplier, description, value);
            list.add(product);
    }
            return list;
        }
    }




