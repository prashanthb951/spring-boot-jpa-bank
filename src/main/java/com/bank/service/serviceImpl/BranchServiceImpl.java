package com.bank.service.serviceImpl;

import com.bank.model.Branch;
import com.bank.repository.BranchRepository;
import com.bank.service.BranchService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> findByBankNameAndCity(String bankName, String city, Pageable pageable) {
        return branchRepository.findByBankNameAndCity(bankName,city,pageable).getContent();
    }

    @Override
    public Branch save(Branch branch) {
        return  branchRepository.save(branch);
    }
}
