package com.practice.jpa.domain.item.service;

import com.practice.jpa.domain.item.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieService {
    private MovieRepository movieRepository;
}
