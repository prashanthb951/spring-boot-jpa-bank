package com.bank.service;

import com.bank.model.Bank;
import com.bank.model.Branch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BranchService {

    List<Branch> findByBankNameAndCity(String bankName, String city, Pageable pageable);

    Branch save(Branch branch);
}
