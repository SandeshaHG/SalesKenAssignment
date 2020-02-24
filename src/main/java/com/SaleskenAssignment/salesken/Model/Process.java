package com.SaleskenAssignment.salesken.Model;



import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "SaleskenProcesses")
public class Process {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NonNull
    @Column(name="process_name")
    private String processName;

    @NonNull
    @Column(name="process_number")
    private String processNumber;




    public Process(String processName, String processNumber) {
        this.processName = processName;
        this.processNumber = processNumber;

    }

    public Process() {
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
