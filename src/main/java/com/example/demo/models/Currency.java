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

    public Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public List<Conversion> getConversions() {
        return conversions;
    }

    public void addConversion(Currency currency, float rate) {
        Conversion conversion = new Conversion(this, currency, rate);
        conversions.add(conversion);
        currency.getConversions().add(conversion);
    }

    public void removeConversion(Currency currency) {
        Conversion conversion = conversions.stream().filter(c -> c.getSecondCurrency() == currency).findFirst().orElse(null);

        if (conversion != null) {
            conversions.remove(conversion);
            currency.getConversions().remove(conversion);
            conversion.setFirstCurrency(null);
            conversion.setSecondCurrency(null);
        }
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
