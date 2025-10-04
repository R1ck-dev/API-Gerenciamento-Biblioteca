package com.henrique.gerenciamento_biblioteca_api.Service;

import java.util.List;

import com.henrique.gerenciamento_biblioteca_api.DTO.AuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.AuthorModel;

public interface AuthorService {
    
    AuthorModel createAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAuthors();

}
