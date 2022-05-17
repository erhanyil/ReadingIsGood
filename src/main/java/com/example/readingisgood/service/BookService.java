package com.example.readingisgood.service;

import com.example.readingisgood.dto.book.BookRequestDTO;
import com.example.readingisgood.dto.book.BookResponseDTO;
import com.example.readingisgood.model.BookModel;

import java.util.List;

public interface BookService {
    BookResponseDTO save(BookRequestDTO bookRequestDTO);

    BookResponseDTO get(Long id);

    BookModel findById(Long id);

    List<BookResponseDTO> getAll(int page, int index);

    BookResponseDTO updateStock(Long id, BookRequestDTO bookRequestDTO);

    BookResponseDTO decreaseStock(Long id, int quantity);
}
