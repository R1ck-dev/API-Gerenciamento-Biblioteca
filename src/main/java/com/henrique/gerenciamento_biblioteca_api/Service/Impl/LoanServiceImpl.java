package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateLoanDTO;
import com.henrique.gerenciamento_biblioteca_api.Enum.BookStatusEnum;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;
import com.henrique.gerenciamento_biblioteca_api.Model.LoanModel;
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;
import com.henrique.gerenciamento_biblioteca_api.Repository.BookRepository;
import com.henrique.gerenciamento_biblioteca_api.Repository.LoanRepository;
import com.henrique.gerenciamento_biblioteca_api.Service.LoanService;

import jakarta.transaction.Transactional;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createLoan(CreateLoanDTO loanDTO, UserModel user) {

        BookModel loanBook = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Livro com ID " + loanDTO.getBookId() + " não encontrado!"));

        if (loanBook.getStatus() != BookStatusEnum.AVAILABLE) {
            throw new RuntimeException("Livro Indisponível!");
        }

        LoanModel loanModel = new LoanModel();
        loanModel.setUser_id(user);
        loanModel.setBook_id(loanBook);

        loanModel.setLoan_date(LocalDateTime.now());
        loanModel.setDue_date(LocalDateTime.now().plusDays(14));
        loanModel.setReturn_date(null);

        loanBook.setStatus(BookStatusEnum.BORROWED);

        loanRepository.save(loanModel);
        bookRepository.save(loanBook);
    }

    @Override
    @Transactional // Garante que as duas operações de save ocorram com sucesso
    public void returnLoan(Long id) {
        LoanModel loanModel = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não Encontrado!"));

        BookModel loanBook = bookRepository.findById(loanModel.getBook_id().getId()) // getBook_id na verdade pega o objeto book inteiro
                    .orElseThrow(() -> new RuntimeException("Livro não Encontrado!")); // Acesso direto graças ao @Transactional

        if (loanModel.getReturn_date() != null) {
            throw new RuntimeException("Este livro já foi devolvido.");
        }

        loanBook.setStatus(BookStatusEnum.AVAILABLE);
        loanModel.setReturn_date(LocalDateTime.now());

        // Como o método é transacional, o JPA/Hibernate é inteligente.
        // As alterações nos objetos 'loanModel' e 'loanBook' serão salvas
        // automaticamente no final do método. As chamadas .save() são opcionais aqui.
        loanRepository.save(loanModel);
        bookRepository.save(loanBook);
    }

}
