package com.SaleskenAssignment.salesken.Model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Product  is the main entity we'll be using
 *
 */
@Entity
@Getter
@Setter
@Table(name = "SaleskenProducts")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    /**
     * The name of the Product
     */
    @NonNull
    @Column(name="product_name")
    private String productName;

    /**
     * The Number of documents in the product
     */
    @NonNull
    @Column(name="product_number_of_documents")
    private String productNumberOfDocuments;

    /**
     * The Product Description
     */
    @NonNull
    @Column(name="product_description")
    private String productDescription;

    /**
     * The image file name fo the product
     */
    @NonNull
    @Column(name="product_image_name")
    private String productImageName;

    /**
     * A list of processes in the product.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Process> processes=new ArrayList<Process>();

    public Product(String productName, String productNumberOfDocuments, String productDescription, String productImageName, List<Process> processes) {
        this.productName = productName;
        this.productNumberOfDocuments = productNumberOfDocuments;
        this.productDescription = productDescription;
        this.productImageName = productImageName;
        this.processes = processes;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumberOfDocuments() {
        return productNumberOfDocuments;
    }

    public void setProductNumberOfDocuments(String productNumberOfDocuments) {
        this.productNumberOfDocuments = productNumberOfDocuments;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageName() {
        return productImageName;
    }

    public void setProductImageName(String productImageName) {
        this.productImageName = productImageName;
    }

    public Product() {
    }
}




