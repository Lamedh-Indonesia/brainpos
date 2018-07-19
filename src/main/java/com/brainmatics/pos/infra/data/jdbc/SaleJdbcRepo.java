package com.brainmatics.pos.infra.data.jdbc;

import com.brainmatics.pos.core.employee.Employee;
import com.brainmatics.pos.core.product.Product;
import com.brainmatics.pos.core.sale.Sale;
import com.brainmatics.pos.core.sale.SaleLineItem;
import com.brainmatics.pos.core.sale.SaleRepo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleJdbcRepo implements SaleRepo {

    private JdbcTemplate jdbc;

    public SaleJdbcRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Sale getByIdEager(int id) {
        String sql = "SELECT * FROM Sale s join SaleLineItem sli on s.id = sli.saleId join Product p on p.id = sli.productId WHERE s.id = ?";
        return jdbc.query(sql, new Object[] { id }, rs -> {
            Sale sale = new Sale();
            sale.setId(id);
            Employee e = new Employee();
            rs.next();
            e.setId(rs.getInt("employeeId"));
            sale.setCashier(e);
            do  {
                Product p = new Product();
                p.setId(rs.getInt("productId"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                sale.addLineItem(p, rs.getInt("quantity"));
            } while (rs.next());
            return sale;
        });
    }

    @Override
    public int getCount() {
        return 0;
    }

    public Sale getById(int id) {
        String sql = "SELECT * FROM Sale s join SaleLineItem sli on s.id = sli.saleId WHERE s.id = ?";
        return jdbc.query(sql, new Object[] { id }, rs -> {
            Sale sale = new Sale();
            sale.setId(id);
            Employee e = new Employee();
            rs.next();
            e.setId(rs.getInt("employeeId"));
            sale.setCashier(e);
            do  {
                Product p = new Product();
                p.setId(rs.getInt("productId"));
                sale.addLineItem(p, rs.getInt("quantity"));
            } while (rs.next());
            return sale;
        });
    }

    @Override
    public List<Sale> getAll() {
        return null;
    }

    @Override
    public void save(Sale sale) {
//        String saleSql = "INSERT INTO Sale (id, employeeId) VALUES (?, ?)";
//        jdbc.update(saleSql, sale.getId(), sale.getCashier().getId());
//
//        String sliSql = "INSERT INTO SaleLineItem (saleId, productId, quantity, price) VALUES (?, ?, ?, ?)";
//        for (SaleLineItem sli: sale.getLineItems()) {
//            jdbc.update(sliSql, sale.getId(), sli.getProduct().getId(), sli.getQuantity(), sli.getUnitPrice());
//        }

//        String saleSql = String.format("INSERT INTO Sale (id, employeeId) VALUES (%d, %d)",
//                sale.getId(), sale.getCashier().getId());
//
//        StringBuilder sb = new StringBuilder();
//        for (SaleLineItem sli: sale.getLineItems()) {
//            String sliSql = String.format(
//                    "INSERT INTO SaleLineItem (saleId, productId, quantity, price) VALUES (%d, %d, %d, %d);",
//                        sale.getId(), sli.getProduct().getId(), sli.getQuantity(), 500);
//            sb.append(sliSql);
//        }
//
//        jdbc.batchUpdate(saleSql, sb.toString());

        String saleSql = "INSERT INTO Sale (id, employeeId) VALUES (?, ?)";
        jdbc.update(saleSql, sale.getId(), sale.getCashier().getId());
        String sliSql = "INSERT INTO SaleLineItem (saleId, productId, quantity, price) VALUES (?, ?, ?, ?)";

        List paramList = new ArrayList<Object[]>();
        for (SaleLineItem sli: sale.getLineItems()) {
            Object[] params = new Object[] {sale.getId(), sli.getProduct().getId(), sli.getQuantity(), sli.getUnitPrice()};
            paramList.add(params);
        }
        jdbc.batchUpdate(sliSql, paramList);
    }

    @Override
    public void remove(int id) {

    }
}
