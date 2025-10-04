package com.henrique.gerenciamento_biblioteca_api.Service;

import java.util.List;

import com.henrique.gerenciamento_biblioteca_api.DTO.AuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.UpdateRequestAuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.AuthorModel;

public interface AuthorService {
    
    AuthorModel createAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAuthors();

    void deleteAuthor(Long id);

    AuthorModel updateAuthor(Long id, UpdateRequestAuthorDTO updateDTO);

}
