package com.henrique.gerenciamento_biblioteca_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateLoanDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;
import com.henrique.gerenciamento_biblioteca_api.Service.LoanService;

@RestController
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loans")
    public ResponseEntity<String> createLoan(@RequestBody CreateLoanDTO loanDTO, Authentication authentication) {

        UserModel user = (UserModel) authentication.getPrincipal();

        loanService.createLoan(loanDTO, user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Empréstimo criado com sucesso!");
    }

    @PostMapping("loans/{id}/return")
    public ResponseEntity<String> returnLoan(@PathVariable Long id) {
        loanService.returnLoan(id);
        return ResponseEntity.ok("Livro devolvido com sucesso!");
    }
}
