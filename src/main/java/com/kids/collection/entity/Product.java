package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Lob
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @JoinColumn(name = "brand_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_brand"))
    @ManyToOne
    private Brand brand;

    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_category"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 0")
    private int status = 0;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
