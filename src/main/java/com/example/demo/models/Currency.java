package com.example.demo.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "currencies")
public class Currency implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "firstCurrency",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Conversion> conversions = new ArrayList<>();

    protected Currency() {

    }

    /**
     * Initializes a new currency with a specified name.
     * @param name The currency's name
     */
    public Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Currency{id=%d, name=%s}", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency that = (Currency) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
