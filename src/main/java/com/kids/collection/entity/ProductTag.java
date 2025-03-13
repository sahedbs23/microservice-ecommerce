package com.kids.collection.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_tag")
public class ProductTag {
    @EmbeddedId
    private CompositeKeyProductTag keyProductTag;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(
            name = "product_id", columnDefinition = "BIGINT", nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_tag_product_id"),
            referencedColumnName = "product_id"
    )
    private Product product;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", columnDefinition = "BIGINT", nullable = false, foreignKey = @ForeignKey(name = "fk_product_tag_tag_id"))
    private Tag tag;

    public ProductTag() {}

    public ProductTag(Product product, Tag tag) {
        this.keyProductTag = new CompositeKeyProductTag(product.getProductId(), tag.getId());
        this.product = product;
        this.tag = tag;
    }

    public CompositeKeyProductTag getKeyProductTag() {
        return keyProductTag;
    }

    public void setKeyProductTag(CompositeKeyProductTag keyProductTag) {
        this.keyProductTag = keyProductTag;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
