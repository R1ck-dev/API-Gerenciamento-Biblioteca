package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.AuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.AuthorModel;
import com.henrique.gerenciamento_biblioteca_api.Repository.AuthorRepository;
import com.henrique.gerenciamento_biblioteca_api.Service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorModel createAuthor(AuthorDTO authorDTO) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setName(authorDTO.getName());
        authorRepository.save(authorModel);
        return authorModel;
    }
    
}
