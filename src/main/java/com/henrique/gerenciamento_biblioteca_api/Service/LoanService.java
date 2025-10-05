package com.henrique.gerenciamento_biblioteca_api.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateLoanDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;

public interface LoanService {

    void createLoan(CreateLoanDTO loanDTO, UserModel user);

    void returnLoan(Long id);
}
