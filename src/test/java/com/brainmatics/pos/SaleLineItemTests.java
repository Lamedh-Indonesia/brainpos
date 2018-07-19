package com.brainmatics.pos;

import com.brainmatics.pos.core.product.Product;
import com.brainmatics.pos.core.sale.SaleLineItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SaleLineItemTests {

    @Test
    public void can_get_subtotal_from_sale_line_item() {
        Product p1 = new Product();
        p1.setPrice(BigDecimal.valueOf(500));

//        SaleLineItem sli = new SaleLineItem();
        SaleLineItem sli = new SaleLineItem(p1, 2);
//        sli.setQuantity(2);
//        sli.setProduct(p1);
//        sli.setUnitPrice(p1.getPrice());
        BigDecimal subtotal = sli.getSubtotal();

        assertEquals(BigDecimal.valueOf(1000), subtotal);
    }
}
