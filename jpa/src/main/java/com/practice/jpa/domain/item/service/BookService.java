package com.practice.jpa.domain.item.service;

import com.practice.jpa.domain.item.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {
    private BookRepository bookRepository;
}
