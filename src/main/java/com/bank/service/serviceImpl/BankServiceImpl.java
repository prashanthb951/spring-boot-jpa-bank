package com.bank.service.serviceImpl;

import com.bank.model.Bank;
import com.bank.repository.BankRepository;
import com.bank.resource.BankResource;
import com.bank.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

     BankServiceImpl(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }


    @Override
    public Bank findByIFSCCode(String ifsc) {
        log.debug("Request to get Bank by IFSC code : {}", ifsc);
        return bankRepository.findByIFSCCode(ifsc);
    }

    @Override
    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }


}
