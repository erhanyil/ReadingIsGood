package com.example.readingisgood.controller;

import com.example.readingisgood.constant.SystemMessage;
import com.example.readingisgood.dto.BaseResponseDTO;
import com.example.readingisgood.dto.book.BookRequestDTO;
import com.example.readingisgood.dto.book.BookResponseDTO;
import com.example.readingisgood.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> save(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO response = bookService.save(bookRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> get(@PathVariable Long id) {
        BookResponseDTO response = bookService.get(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int index) {
        List<BookResponseDTO> response = bookService.getAll(page, index);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<BookResponseDTO> updateStock(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO response = bookService.updateStock(id, bookRequestDTO);
        return ResponseEntity.ok(response);
    }
}
