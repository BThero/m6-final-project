package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConversionKey implements Serializable {
    @Column(name = "first_currency_id")
    private Long firstCurrencyId;

    @Column(name = "second_currency_id")
    private Long secondCurrencyId;

    protected ConversionKey() {

    }

    public ConversionKey(Long firstCurrencyId, Long secondCurrencyId) {
        this.firstCurrencyId = firstCurrencyId;
        this.secondCurrencyId = secondCurrencyId;
    }

    Long getFirstCurrencyId() {
        return this.firstCurrencyId;
    }

    Long getSecondCurrencyId() {
        return this.secondCurrencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConversionKey that = (ConversionKey) o;
        return Objects.equals(firstCurrencyId, that.firstCurrencyId) && Objects.equals(secondCurrencyId, that.secondCurrencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCurrencyId, secondCurrencyId);
    }
}
