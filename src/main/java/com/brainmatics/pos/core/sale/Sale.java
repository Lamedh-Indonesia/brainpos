package com.brainmatics.pos.core.sale;

import com.brainmatics.common.EntityBase;
import com.brainmatics.pos.core.employee.Employee;
import com.brainmatics.pos.core.product.Product;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale extends EntityBase {

    private String code;
    private LocalDateTime time;

    @ManyToOne
    private Employee cashier;
    @ElementCollection
    private List<SaleLineItem> lineItems;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Employee getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }

    public List<SaleLineItem> getLineItems() {
        return lineItems;
    }

    public Sale() {
        time = LocalDateTime.now();
        lineItems = new ArrayList<>();
    }

    public void addLineItem(Product product, int quantity) {
        SaleLineItem sli = new SaleLineItem(product, quantity);
        lineItems.add(sli);
    }

    public BigDecimal getTotal() {
        return lineItems.stream()
                .map(SaleLineItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
