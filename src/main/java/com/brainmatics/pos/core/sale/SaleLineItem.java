package com.brainmatics.pos.core.sale;

import com.brainmatics.pos.core.product.Product;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

//@Embeddable
//public class SaleLineItem {
//
//    private int quantity;
//    private BigDecimal unitPrice;
//
//    @ManyToOne
//    private Product product;
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public BigDecimal getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(BigDecimal unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//        setUnitPrice(product.getPrice());
//    }
//
//    public SaleLineItem(Product product, int quantity) {
//        setProduct(product);
//        setQuantity(quantity);
//        setUnitPrice(product.getPrice());
//    }
//
//    public SaleLineItem() { }
//
//    public BigDecimal getSubtotal() {
//        return unitPrice.multiply(BigDecimal.valueOf(quantity));
//    }
//}

@Embeddable
public class SaleLineItem {

    private int quantity;
    private BigDecimal unitPrice;
    @ManyToOne
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public SaleLineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    SaleLineItem() { }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
