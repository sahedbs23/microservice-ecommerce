package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "districts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 55)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provision_id", nullable = false, columnDefinition="INT DEFAULT 0", foreignKey = @ForeignKey(name = "fk_district_provisions"))
    private Provision provision;
}
