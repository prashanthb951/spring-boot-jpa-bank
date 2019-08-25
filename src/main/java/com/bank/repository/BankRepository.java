package com.bank.repository;

import com.bank.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

    @Query("select bank from Branch branch where branch.ifsc=:ifsc")
    Bank findByIFSCCode(@Param("ifsc") String ifsc);
}
