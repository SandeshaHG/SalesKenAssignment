package com.SaleskenAssignment.salesken.Controller;

import com.SaleskenAssignment.salesken.DAO.ProcessRepo;
import com.SaleskenAssignment.salesken.Model.Process;
import com.SaleskenAssignment.salesken.Model.Product;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * The Rest Controller
 */
@RestController
public class ProcessController {
    @Autowired
    public ProcessRepo dao;

    /**
     * Method to get All the available processes
     * @return List of all the processes
     * */
    @GetMapping("/getAllProcess")
    public List<Process> getallProcess(){
        List<Process> processList= (List<Process>) dao.findAll();
        for(int index=0;index<processList.size();index++){
            for(int i=0;i<processList.get(index).getProducts().size();i++){
                processList.get(index).getProducts().get(i).getProcesses().clear();
            }
        }
        return processList;
    }

    /**
     * Method to add a process
     * @param json  Process from the Request body in json format
     * @return Returns the created process
     */
    @PostMapping("/addProcess")
    public Process addProcess(@RequestBody String json){
        JSONObject jsonObject=new JSONObject(json);
        String processName=jsonObject.getString("process_name");
        String processNumber=jsonObject.getString("process_number");
        List<Product> productList=new ArrayList<Product>();
        Process process=new Process(processName,processNumber,productList);
        return dao.save(process);
    }

    /**
     * Method to get update a Process
     * @param id The process id to edit
     * @param json  process from the Request body in json format
     * @return Returns the acknowledgement
     */
    @PatchMapping("/UpdateProcess/{id}")
    public String UpdateProcess(@RequestBody String json,@PathVariable long id) {
        JSONObject jsonObj = new JSONObject(json);
        Process process=dao.findById(id).get();
        process.setProcessName(jsonObj.getString("process_name"));
        process.setProcessNumber(jsonObj.getString("process_number"));
        dao.save(process);
        return "Successfully updated";
    }

    /**
     * Method to get delete a process
     * @param id The process id to delete
     * @return Returns the acknowledgement
     */
    @DeleteMapping("/DeleteProcess/{id}")
    public String deleteProcess(@PathVariable long id){
        dao.deleteById(id);
        return "Successfully deleted";
    }
}
