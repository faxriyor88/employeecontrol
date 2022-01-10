package com.example.employeecontrol.repository;

import com.example.employeecontrol.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Page<Employee> findAllByCompanyId(Integer company_id, Pageable pageable);

    List<Employee> findAllByCompanyId(Integer company_id);

    Optional<Employee> findByIdAndCompanyId(UUID id, Integer company_id);

    boolean existsByFullname(String fullname);

    boolean existsByFullnameAndCompanyId(String fullname, Integer company_id);

    boolean existsByFullnameNotAndCompanyId(String fullname, Integer company_id);

    boolean existsByCompanyIdAndId(Integer company_id, UUID id);

    List<Employee> findAllByFullnameContaining(String fullname);

    List<Employee> findAllByFullnameContainingAndCompanyId(String fullname, Integer company_id);

    @Query(value = "select * from employee e inner join company c on e.company_id = c.id   where c.companyname LIKE %:company_companyname%", nativeQuery = true)
    List<Employee> findByCompany_CompanynameContaining(@Param("company_companyname") String company_companyname);

    @Query(value = "select * from employee e inner join company c on e.company_id = c.id   where c.id=:company_id and c.companyname LIKE %:company_companyname%", nativeQuery = true)
    List<Employee> findCompanyNameAndCompanyId(@Param("company_id") Integer company_id,@Param("company_companyname") String company_companyname);

    List<Employee> findAllByLavozimivaQachondanContaining(String lavozimivaQachondan);

    List<Employee> findAllByLavozimivaQachondanContainingAndCompanyId(String lavozimivaQachondan, Integer company_id);


}
