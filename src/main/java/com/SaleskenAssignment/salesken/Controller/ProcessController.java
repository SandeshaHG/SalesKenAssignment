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
        Process process=new Process(processName,processNumber);
        return dao.save(process);
    }

    @PatchMapping("/UpdateProcess/{id}")
    public Process UpdateProcess(@RequestBody String json,@PathVariable long id) {
        JSONObject jsonObj = new JSONObject(json);
        Process process=dao.findById(id).get();
        process.setProcessName(jsonObj.getString("process_name"));
        process.setProcessNumber(jsonObj.getString("process_number"));
        return dao.save(process);
    }

    @DeleteMapping("/DeleteProcess/{id}")
    public String deleteProcess(@PathVariable long id){
        dao.deleteById(id);
        return "Successfully deleted";
    }
}
