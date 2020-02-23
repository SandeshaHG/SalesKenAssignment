package com.SaleskenAssignment.salesken.Model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_number_of_documents")
    private String productNumberOfDocuments;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="product_image_name")
    private String productImageName;

    public Product(String productName, String productNumberOfDocuments, String productDescription, String productImageName) {
        this.productName = productName;
        this.productNumberOfDocuments = productNumberOfDocuments;
        this.productDescription = productDescription;
        this.productImageName = productImageName;
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
}




