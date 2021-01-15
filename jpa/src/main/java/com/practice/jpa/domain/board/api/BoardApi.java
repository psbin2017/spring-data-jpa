package com.practice.jpa.domain.board.api;

import com.practice.jpa.domain.board.domain.Board;
import com.practice.jpa.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApi {
    private final BoardService boardService;

    @GetMapping(name = "/api/v1/board/")
    public ResponseEntity<List<Board>> board() {
        return ResponseEntity.ok().body(null);
    }
}
