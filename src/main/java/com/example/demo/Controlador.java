package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController
public class Controlador {

    @PostMapping("/register_product")
    public Product register_product(@RequestBody Product product) throws SQLException, ClassNotFoundException {

        String code = product.getCode();
        String name = product.getName();
        String id_supplier = product.getId_supplier();
        String description = product.getDescription();
        String value = product.getValue();

        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                id_supplier == null || id_supplier.equals("") || id_supplier.length() < 0 || description == null || description.equals("") ||
                description.length() < 0 || value == null || value.equals("") || value.length() < 0) {

            return new Product(null, null, null, null, null);
        } else {
            BD bd = new BD();
            String code_supplier = bd.Search_supplier(id_supplier);

            if (code_supplier.equals("")) {
                return new Product(null, null, "No se encuentra el codigo del provedor", null, null);
            } else {

                product = bd.Register(code, name, id_supplier, description, value);
            }

            return product;
        }
    }

    @PostMapping("/edit_product")
    public Product edit_product(@RequestBody Product product) throws SQLException, ClassNotFoundException {

        String code = product.getCode();
        String name = product.getName();
        String id_supplier = product.getId_supplier();
        String description = product.getDescription();
        String value = product.getValue();


        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                id_supplier == null || id_supplier.equals("") || id_supplier.length() < 0 || description == null || description.equals("") ||
                description.length() < 0 || value == null || value.equals("") || value.length() < 0) {

            return new Product(null, null, null, null, null);
        } else {
            BD bd = new BD();
            String code_supplier = bd.Search_supplier(id_supplier);

            if (code_supplier.equals("")) {
                return new Product(null, null, "No se encuentra el codigo del provedor", null, null);
            } else {
                product = bd.Edit_product(code, name, id_supplier, description, value);
            }

        }
        return product;
    }

    @GetMapping("/search_products")
    public List<Product> search_products() throws SQLException, ClassNotFoundException, ParseException {
        BD bd = new BD();

        List<Product> list = bd.Search_products();

        if (list.isEmpty()) {
            Product product = new Product(null, null, null, "La fecha de expiración supera los 30 días", null);
            list.add(product);
        }
        return list;
    }

    @GetMapping("/search_product/{code}")
    public Product search_product(@PathVariable String code) throws SQLException, ClassNotFoundException {

        Product product;

        if (code == null || code.equals("") || code.length() < 0) {

            return new Product("No se encuentra un producto con el codigo ingresado", null, null, null, null);

        } else {
            BD bd = new BD();
            product = BD.Search_product(code);
        }


        return product;
    }

    @DeleteMapping("/delete_product")
    public Product delete_product(@RequestBody Product product) throws SQLException, ClassNotFoundException {
        String code = product.getCode();
        if (product.getCode() == null || product.getCode().equals("") || product.getCode().length() < 0) {
            return new Product(null, null, null, null, null);
        } else {
            BD bd = new BD();
            int f = BD.Delete_product(code);
            if (f == 0) {
                return new Product("No se encontro un producto para eliminar", null, null, null, null);
            } else {

            }
        }
        return product;
    }

    @DeleteMapping("/delete")
    public String delete() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        int f = BD.Delete();

        String message = "Se han eliminado todos los productos";
        return message;
    }
}


