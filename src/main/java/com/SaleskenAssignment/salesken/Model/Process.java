package com.SaleskenAssignment.salesken.Model;



import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Process  is the second entity we'll be using.A product can have many processes.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "SaleskenProcesses")
public class Process {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    /**
     * The name of the Process
     */
    @NonNull
    @Column(name="process_name")
    private String processName;

    /**
     * The Process number
     */
    @NonNull
    @Column(name="process_number")
    private String processNumber;

    /**
     * List of all products having the respective process
     */
    @ManyToMany(mappedBy = "processes",fetch = FetchType.LAZY)
    private List<Product> products=new ArrayList<Product>();


    public Process(String processName, String processNumber, List<Product> products) {
        this.processName = processName;
        this.processNumber = processNumber;
        this.products = products;
    }

    public Process() {
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }
}
