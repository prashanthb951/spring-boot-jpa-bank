package com.bank.service;

import com.bank.model.Bank;

public interface BankService {


    Bank findByIFSCCode(String ifsc);

    Bank save(Bank bank);
}
