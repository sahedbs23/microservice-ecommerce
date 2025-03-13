package com.kids.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provision")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Provision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 55)
    private String name;

    @Column(name = "short_name", length = 2)
    private String shortName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Country country;
}
