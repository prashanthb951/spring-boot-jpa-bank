package com.bank.resource;

import com.bank.model.Bank;
import com.bank.model.Branch;
import com.bank.service.BranchService;
import com.bank.utill.HeaderUtill;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class BranchResource {

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    private final BranchService branchService;

    public BranchResource(BranchService branchService) {
        this.branchService = branchService;
    }



    /**
     * POST  /branches : Create a new bank.
     *
     * @param branch the branch to create
     * @return the ResponseEntity with status 201 (Created) and with body the new branch.
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/branches")
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch)throws Exception{
        log.debug("REST request to create branch : {}",branch);
        branch = branchService.save(branch);
        return ResponseEntity.created(new URI("/api/branches/" + branch.getIfsc()))
                .headers(HeaderUtill.createEntityCreationAlert("Branch", branch.getIfsc().toString()))
                .body(branch);
    }


    /**
     * GET  /banks/:ifsc : get the "ifsc" bank.
     *
     * @param bankName
     * @param city
     * @return the ResponseEntity with status 200 (OK) and with body the branch, or with status 404 (Not Found)
     */
    @GetMapping("/branches/{bankName}/{city}")
    public ResponseEntity<List<Branch>> findByBankNameAndCity(@PathVariable String bankName, @PathVariable String city, @ApiParam Pageable pageable) {
        log.debug("REST request to get Branches by bankName:{} and city : {}", bankName,city);
        return  Optional
                .ofNullable( branchService.findByBankNameAndCity(bankName,city,pageable) )
                .map( user -> ResponseEntity.ok().body(user) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

}
