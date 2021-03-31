package com.practice.jpa.domain.board.repository;

import com.practice.jpa.domain.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findByContent(String content) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Board> query = cb.createQuery(Board.class);

        // 조회를 시작할 클래스 지정
        Root<Board> b = query.from(Board.class);

        CriteriaQuery<Board> cq = query
                                    .select(b)
                                    .where(cb.equal(b.get("content"), content));

        return em.createQuery(cq).getResultList();
    }

    public List<Board> findByTitle(String title) {
        String sql = "SELECT ID, TITLE, CONTENT FROM BOARD WHERE TITLE = '" +title+ "'";
        return em.createNamedQuery(sql, Board.class).getResultList();
    }

}
