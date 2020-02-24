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
    @PatchMapping("/UpdateProduct/{id}")
    public Product UpdateProduct(@RequestBody String json,@PathVariable long id) {
        JSONObject jsonObj = new JSONObject(json);
        Product product=dao.findById(id).get();
        product.setProductName(jsonObj.getString("product_name"));
        product.setProductNumberOfDocuments(jsonObj.getString("product_number_of_documents"));
        product.setProductDescription(jsonObj.getString("product_description"));
        product.setProductImageName(jsonObj.getString("product_image_name"));
        List<Process> processArrayList=new ArrayList<Process>();
        product.setProcesses(processArrayList);
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
    @PatchMapping("/DeleteProcess/{productId}/{processId}")
    public String  DeleteProcess(@PathVariable long productId,@PathVariable long processId){
        Process process=processdao.findById(processId).get();
        Product product=dao.findById(productId).get();
        List<Process> processArrayList=product.getProcesses();
        processArrayList.remove(process);
        product.setProcesses(processArrayList);
        return "Process  '"+process.getProcessName()+"' has been successfully Deleted from Product '" +product.getProductName()+"'";
    }
@DeleteMapping("/DeleteProduct/{id}")
    public String DeleteProduct(@PathVariable long id){
    dao.deleteById(id);
    return "Successfully deleted";
}

}
