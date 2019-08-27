package com.bank.resource;

import com.bank.model.Bank;
import com.bank.service.BankService;
import com.bank.utill.HeaderUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BankResource {

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    private final BankService bankService;

    private BankResource(BankService bankService){
        this.bankService  = bankService;
    }


    /**
     * POST  /banks : Create a new bank.
     *
     * @param bank the bank to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bank.
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/banks")
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank)throws Exception{
        log.debug("REST request to create bank : {}",bank);
        bank = bankService.save(bank);
        return ResponseEntity.created(new URI("/api/banks/" + bank.getId()))
                .headers(HeaderUtill.createEntityCreationAlert("Bank", bank.getId().toString()))
                .body(bank);
    }


    /**
     * GET  /banks/:ifsc : get the "ifsc" bank.
     *
     * @param ifsc the ifsc of the bank branch to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bank, or with status 404 (Not Found)
     */
    @GetMapping("/banks")
    public ResponseEntity<Bank> findByIFSCCode(@RequestParam String ifsc) {
        log.debug("REST request to get Bank by IFSC code : {}", ifsc);
        return  Optional
                .ofNullable( bankService.findByIFSCCode(ifsc) )
                .map( user -> ResponseEntity.ok().body(user) )          //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
