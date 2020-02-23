package com.SaleskenAssignment.salesken.DAO;

import com.SaleskenAssignment.salesken.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {
}