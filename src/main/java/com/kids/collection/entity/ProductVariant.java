package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "product_variants",
        indexes = {
                @Index(name = "idx_product_color_size", columnList = "product_id, color_id, size_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", columnDefinition = "BIGINT")
    private Long variantId;

    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_product_variant"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Product product;

    @Column(name = "sku", length = 55, nullable = false, unique = true)
    private String sku;

    @Column(name = "price", length = 10, precision = 2, nullable = false)
    private Double price;

    @JoinColumn(name = "color_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_variant_color"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Color color;

    @JoinColumn(name = "size_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_variant_size"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Size size;

    @Column(name = "weight", length = 10, precision = 2)
    private Double weight;

    @Column(name = "barcode", nullable = false, length = 55, unique = true)
    private String barcode;

    @Column(name = "dimension", length = 55)
    private String dimension;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt;
}
