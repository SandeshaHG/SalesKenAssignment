package com.SaleskenAssignment.salesken.Controller;

import com.SaleskenAssignment.salesken.DAO.ProcessRepo;
import com.SaleskenAssignment.salesken.Model.Process;
import com.SaleskenAssignment.salesken.Model.Product;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProcessController {
    @Autowired
    public ProcessRepo dao;

    @GetMapping("/getAllProcess")
    public List<Process> getallProcess(){
        return (List<Process>)dao.findAll();
    }

    @PostMapping("/addProcess")
    public Process addProcess(@RequestBody String json){
        JSONObject jsonObject=new JSONObject(json);
        String processName=jsonObject.getString("process_name");
        String processNumber=jsonObject.getString("process_number");
        List<Product> productList=new ArrayList<Product>();
        Process process=new Process(processName,processNumber,productList);
        return dao.save(process);
    }

    @DeleteMapping("/{id}")
    public String delteProcess(@PathVariable long id){
        dao.deleteById(id);
        return "Successfully deleted";
    }
}
