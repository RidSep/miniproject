package id.co.indivara.jdt12.carrental.repo;

import id.co.indivara.jdt12.carrental.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,String> {

}
