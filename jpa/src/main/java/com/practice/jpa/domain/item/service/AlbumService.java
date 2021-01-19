package com.practice.jpa.domain.item.service;

import com.practice.jpa.domain.item.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlbumService {
    private AlbumRepository albumRepository;
}
