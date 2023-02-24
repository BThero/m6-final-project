package com.example.demo.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "conversions")
public class Conversion implements Serializable {
    @EmbeddedId
    private ConversionKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("firstCurrencyId")
    private Currency firstCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("secondCurrencyId")
    private Currency secondCurrency;

    @Column(nullable = false)
    private float rate;

    protected Conversion() {

    }

    public Conversion(Currency firstCurrency, Currency secondCurrency, float rate) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.id = new ConversionKey(firstCurrency.getId(), secondCurrency.getId());
        this.rate = rate;
    }

    public void setFirstCurrency(Currency firstCurrency) {
        this.firstCurrency = firstCurrency;
    }

    public void setSecondCurrency(Currency secondCurrency) {
        this.secondCurrency = secondCurrency;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Currency getFirstCurrency() {
        return firstCurrency;
    }

    public Currency getSecondCurrency() {
        return secondCurrency;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Conversion that = (Conversion) o;
        return Objects.equals(firstCurrency, that.firstCurrency) &&
                Objects.equals(secondCurrency, that.secondCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCurrency, secondCurrency);
    }
}
