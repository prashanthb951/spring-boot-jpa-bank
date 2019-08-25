package com.bank.repository;

import com.bank.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {

    @Query("select branch from Branch branch where bank.name=:bankName AND branch.city=:city")
    Page<Branch> findByBankNameAndCity(@Param("bankName") String bankName, @Param("city") String city, Pageable pageable);
}
