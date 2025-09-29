package com.henrique.gerenciamento_biblioteca_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long>{
    
}
