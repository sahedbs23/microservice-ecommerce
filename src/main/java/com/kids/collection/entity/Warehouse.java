package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "house", nullable = false)
    private String address1;

    @Column(name = "street")
    private String address2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_code")
    private PostalCode postcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "police_station", columnDefinition = "BIGINT DEFAULT 0")
    private PoliceStation policeStation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "district", columnDefinition = "BIGINT DEFAULT 0")
    private District district;

    @ManyToOne
    @JoinColumn(name = "country", columnDefinition = "INT DEFAULT 23")
    @Comment("23=Bangladesh")
    private Country country;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
