package com.henrique.gerenciamento_biblioteca_api.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.AuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.AuthorModel;

public interface AuthorService {
    
    AuthorModel createAuthor(AuthorDTO authorDTO);

}
