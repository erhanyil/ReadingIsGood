package com.example.readingisgood.service.impl;

import com.example.readingisgood.constant.SystemMessage;
import com.example.readingisgood.dto.book.BookRequestDTO;
import com.example.readingisgood.dto.book.BookResponseDTO;
import com.example.readingisgood.exception.FriendlyException;
import com.example.readingisgood.model.BookModel;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @Override
    public BookResponseDTO save(BookRequestDTO bookRequestDTO) {
        BookModel bookModel = objectMapper.convertValue(bookRequestDTO, BookModel.class);
        bookRepository.save(bookModel);
        return objectMapper.convertValue(bookModel, BookResponseDTO.class);
    }

    @Override
    public BookResponseDTO get(Long id) {
        return bookRepository.findById(id).map(bookModel -> objectMapper.convertValue(bookModel, BookResponseDTO.class)).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
    }

    @Override
    public BookModel findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
    }

    @Override
    public List<BookResponseDTO> getAll(int page, int index) {
        return bookRepository.findAll(PageRequest.of(page, index)).map(bookModel -> objectMapper.convertValue(bookModel, BookResponseDTO.class)).stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookResponseDTO updateStock(Long id, BookRequestDTO bookRequestDTO) {
        BookModel bookModel = bookRepository.findById(id).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
        bookModel.setStock(bookRequestDTO.getStock());
        bookRepository.save(bookModel);
        return objectMapper.convertValue(bookModel, BookResponseDTO.class);
    }

    @Override
    public BookResponseDTO decreaseStock(Long id, int quantity) {
        BookModel bookModel = bookRepository.findById(id).orElseThrow(() -> new FriendlyException(SystemMessage.RECORD_NOT_FOUND));
        bookModel.setStock(bookModel.getStock() + quantity);
        bookRepository.save(bookModel);
        return objectMapper.convertValue(bookModel, BookResponseDTO.class);
    }
}
