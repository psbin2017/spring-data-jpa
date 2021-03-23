package com.practice.jpa.domain.category.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 자기 참조 구조
 *
 * 예) 카테고리 안에 카테고리
 */
@Entity
@Table(name = "CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
     * self 로 양방향 매핑
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    /*
     * self 로 양방향 매핑
     */
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // === 연관관계 편의 메소드 === //

    public void addChildCategory(Category child) {
        if ( ! this.child.contains(child) ) {
            this.child.add(child);
        }
        child.setParent(this);
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
