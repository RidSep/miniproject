package id.co.indivara.jdt12.carrental.repo;

import id.co.indivara.jdt12.carrental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

}
