package ies.lab03.ex3_2.jpadata.repository;

import ies.lab03.ex3_2.jpadata.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    List<Employee> findByEmailId(String emailId);
}