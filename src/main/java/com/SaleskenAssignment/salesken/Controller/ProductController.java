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

/**
 * The Rest Controller
 */
@RestController
public class ProductController {
@Autowired
private ProductRepo dao;

@Autowired
private ProcessRepo processdao;

@GetMapping("/")
public String landingpage(){
    return "Welcome. Please check out the ReadMe file in https://github.com/SandeshaHG/SalesKenAssignment to know more about the project and endpoints";
}
    /**
     * Method to get All the available products
     * @return List of all the products
     * */
@GetMapping("/getAllProducts")
List<Product> getAllProducts(){
    List<Product> productList= (List<Product>) dao.findAll();
    for(int index=0;index<productList.size();index++){
        for(int i=0;i<productList.get(index).getProcesses().size();i++){
            productList.get(index).getProcesses().get(i).getProducts().clear();
        }
    }
    return productList;
}

    /**
     * Method to add a product
     * @param json  Product from the Request body in json format
     * @return Returns the created product
     */
@PostMapping("/AddProduct")
public Product AddProduct(@RequestBody String json) {
    JSONObject jsonObj = new JSONObject(json);
    String jsonProductName = jsonObj.getString("product_name");
    String jsonProductNumberOfDocuments = jsonObj.getString("product_number_of_documents");
    String jsonproductDescription = jsonObj.getString("product_description");
    String jsonproductImageName = jsonObj.getString("product_image_name");
    String jsonproductPrice=jsonObj.getString("product_price");
    List<Process> processArrayList=new ArrayList<Process>();
    Product product = new Product(jsonProductName, jsonProductNumberOfDocuments, jsonproductDescription, jsonproductImageName,jsonproductPrice,processArrayList);
    return dao.save(product);
}
    /**
     * Method to get update a Product
     * @param id The Product id to edit
     * @param json  Product from the Request body in json format
     * @return Returns the acknowledgement
     */
    @PatchMapping("/UpdateProduct/{id}")
    public String UpdateProduct(@RequestBody String json,@PathVariable long id) {
        JSONObject jsonObj = new JSONObject(json);
        Product product=dao.findById(id).get();
        product.setProductName(jsonObj.getString("product_name"));
        product.setProductNumberOfDocuments(jsonObj.getString("product_number_of_documents"));
        product.setProductDescription(jsonObj.getString("product_description"));
        product.setProductImageName(jsonObj.getString("product_image_name"));
        product.setProductPrice(jsonObj.getString("product_price"));
        List<Process> processArrayList=new ArrayList<Process>();
        product.setProcesses(processArrayList);
        dao.save(product);
        return "Successfully Updated";
    }

    /**
     * Method to get Add a Process to the product
     * @param productId The Product id to which a process has to be added
     * @param processId  The Process which is being added
     * @return Returns the acknowledgement
     */
@PatchMapping("/AddProcess/{productId}/{processId}")
    public String  AddProcess(@PathVariable long productId,@PathVariable long processId){
    Process process=processdao.findById(processId).get();
    Product product=dao.findById(productId).get();
    List<Process> processArrayList=product.getProcesses();
    processArrayList.add(process);
    product.setProcesses(processArrayList);
    dao.save(product);
    return "Process  '"+process.getProcessName()+"' has been successfully added to Product '" +product.getProductName()+"'";
}
    /**
     * Method to get Delete a process from a product
     * @param processId The Process id to delete
     * @param productId The product Id to delete from
     * @return Returns the acknowledgement
     */
    @PatchMapping("/DeleteProcess/{productId}/{processId}")
    public String  DeleteProcess(@PathVariable long productId,@PathVariable long processId){
        Process process=processdao.findById(processId).get();
        Product product=dao.findById(productId).get();
        List<Process> processArrayList=product.getProcesses();
        processArrayList.remove(process);
        product.setProcesses(processArrayList);
        return "Process  '"+process.getProcessName()+"' has been successfully Deleted from Product '" +product.getProductName()+"'";
    }

    /**
     * Method to get delete a Product
     * @param id The Product id to delete
     * @return Returns the acknowledgement
     */
@DeleteMapping("/DeleteProduct/{id}")
    public String DeleteProduct(@PathVariable long id){
    dao.deleteById(id);
    return "Successfully deleted";
}

}
