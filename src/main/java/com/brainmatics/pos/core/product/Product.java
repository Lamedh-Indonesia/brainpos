package com.brainmatics.pos.core.product;

import com.brainmatics.common.EntityBase;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product extends EntityBase {

    private String code;
    private String name;
    private BigDecimal price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(int price) {
        setPrice(BigDecimal.valueOf(price));
    }
}
