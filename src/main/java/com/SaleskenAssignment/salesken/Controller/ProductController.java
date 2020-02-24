package com.SaleskenAssignment.salesken.Controller;

import com.SaleskenAssignment.salesken.DAO.ProcessRepo;
import com.SaleskenAssignment.salesken.DAO.ProductRepo;
import com.SaleskenAssignment.salesken.Model.Process;
import com.SaleskenAssignment.salesken.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
@Autowired
private ProductRepo dao;

@Autowired
private ProcessRepo processdao;

@GetMapping("/TestDeploy")
public String test(){
    return "working fine";
}
@GetMapping("/getAllProducts")
List<Product> getAllProducts(){
    return (List<Product>) dao.findAll();
}

@PostMapping("/AddProduct")
public Product AddProduct(@RequestBody String json) {
    JSONObject jsonObj = new JSONObject(json);
    String jsonProductName = jsonObj.getString("product_name");
    String jsonProductNumberOfDocuments = jsonObj.getString("product_number_of_documents");
    String jsonproductDescription = jsonObj.getString("product_description");
    String jsonproductImageName = jsonObj.getString("product_image_name");
    List<Process> processArrayList=new ArrayList<Process>();
    Product product = new Product(jsonProductName, jsonProductNumberOfDocuments, jsonproductDescription, jsonproductImageName,processArrayList);
    return dao.save(product);
}

@PatchMapping("/AddProcess/{productId}/{processId}")
    public String  AddProcess(@PathVariable long productId,@PathVariable long processId){
    Process process=processdao.findById(processId).get();
    Product product=dao.findById(productId).get();
    List<Process> processArrayList=product.getProcesses();
    processArrayList.add(process);
    product.setProcesses(processArrayList);
    return "Process  '"+process.getProcessName()+"' has been successfully added to Product '" +product.getProductName()+"'";

}

}
