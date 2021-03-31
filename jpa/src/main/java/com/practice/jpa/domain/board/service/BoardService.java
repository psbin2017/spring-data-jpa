package com.practice.jpa.domain.board.service;

import com.practice.jpa.domain.board.domain.Board;
import com.practice.jpa.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findByContent(String content) {
        return boardRepository.findByContent(content);
    }

    public List<Board> findByTitle(String title) {
        return boardRepository.findByTitle(title);
    }

}
