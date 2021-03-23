package com.practice.jpa.domain.item.domain;

import com.practice.jpa.global.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 상속 관계 구조
 *
 * - `InheritanceType.JOIN`
 *  부모 테이블과 자식 테이블이 별도로 생성 됨
 *
 * - `InheritanceType.SINGLE_TABLE`
 *  부모 테이블만 생성 됨 (정규화되지 않음)
 *
 * - `InheritanceType.TABLE_PER_CLASS`
 *  구현 클래스만 생성됨 (부모 클래스는 추상으로 선언되어 있음을 주목)
 *
 * 예) 공통된 컬럼을 가지면서 (abstract class) 부가적인 고유 컬럼을 가진 형태
 */
@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public abstract class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
