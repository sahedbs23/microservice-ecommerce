package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_image_product_id"))
    @ManyToOne
    private Product product;

    @JoinColumn(name = "prod_variant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_image_product_variant_id"))
    @ManyToOne
    private ProductVariant productVariant;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_primary", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isPrimaryImage;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt;
}
