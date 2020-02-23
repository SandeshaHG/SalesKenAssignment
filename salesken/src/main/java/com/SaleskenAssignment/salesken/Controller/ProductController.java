package com.SaleskenAssignment.salesken.Controller;

import com.SaleskenAssignment.salesken.DAO.ProductRepo;
import com.SaleskenAssignment.salesken.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.JSONObject;

import java.util.List;

public class ProductController {
@Autowired
private ProductRepo dao;

@GetMapping("/getAllProducts")
List<Product> getAllProducts(){
    return (List<Product>) dao.findAll();
}

@PostMapping("/AddProduct")
public String AddProduct(@RequestBody String json) {
    JSONObject jsonObj = new JSONObject(json);
    String jsonProductName = jsonObj.getString("product_name");
    String jsonProductNumberOfDocuments = jsonObj.getString("product_number_of_documents");
    String jsonproductDescription = jsonObj.getString("product_description");
    String jsonproductImageName = jsonObj.getString("product_image_name");
    Product product = new Product(jsonProductName, jsonProductNumberOfDocuments, jsonproductDescription, jsonproductImageName);
    dao.save(product);
    return "Product Added";
}

}
