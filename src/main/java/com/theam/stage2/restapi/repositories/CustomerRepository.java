package com.theam.stage2.restapi.repositories;

import com.theam.stage2.restapi.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
