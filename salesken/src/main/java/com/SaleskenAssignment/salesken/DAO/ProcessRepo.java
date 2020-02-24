package com.SaleskenAssignment.salesken.DAO;

import org.springframework.data.repository.CrudRepository;
import com.SaleskenAssignment.salesken.Model.Process;

public interface ProcessRepo extends CrudRepository<Process,Long> {
}
