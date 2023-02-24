package com.example.demo.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * Initializes a currency conversion with specified currency pair and a conversion rate
     * @param firstCurrency currency to convert from
     * @param secondCurrency currency to convert to
     * @param rate conversion rate
     */
    public Conversion(Currency firstCurrency, Currency secondCurrency, float rate) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.id = new ConversionKey(firstCurrency.getId(), secondCurrency.getId());
        this.rate = rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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

    /**
     * Converts the Conversion object to a map that is easily parsable into JSON
     * @return Map<String, Object>
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("firstCurrency", firstCurrency.getName());
        map.put("secondCurrency", secondCurrency.getName());
        map.put("rate", getRate());
        return map;
    }
}
