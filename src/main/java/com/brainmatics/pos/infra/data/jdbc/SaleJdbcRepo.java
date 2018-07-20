package com.brainmatics.pos.infra.data.jdbc;

import com.brainmatics.pos.core.employee.Employee;
import com.brainmatics.pos.core.product.Product;
import com.brainmatics.pos.core.sale.Sale;
import com.brainmatics.pos.core.sale.SaleRepoNonSpring;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class SaleJdbcRepo implements SaleRepoNonSpring {

    private JdbcTemplate jdbc;

    public SaleJdbcRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Sale getByIdEager(int id) {
        String sql = "SELECT * FROM Sale s join SaleLineItem sli on s.id = sli.saleId join Product p on p.id = sli.productId WHERE s.id = ?";
        return jdbc.query(sql, new Object[]{id}, rs -> {
            Sale sale = new Sale();
            sale.setId(id);
            Employee e = new Employee();
            rs.next();
            e.setId(rs.getInt("employeeId"));
            sale.setCashier(e);
            do {
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
        return jdbc.query(sql, new Object[]{id}, rs -> {
            Sale sale = new Sale();
            sale.setId(id);
            Employee e = new Employee();
            rs.next();
            e.setId(rs.getInt("employeeId"));
            sale.setCashier(e);
            do {
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
        String saleSql = "INSERT INTO Sale (id, employeeId) VALUES (?, ?)";
        jdbc.update(saleSql, sale.getId(), sale.getCashier().getId());

        String sliSql = "INSERT INTO SaleLineItem (saleId, productId, quantity, price) VALUES (?, ?, ?, ?)";
        List<Object[]> paramList = sale.getLineItems()
                .stream()
                .map(sli -> new Object[]{sale.getId(), sli.getProduct().getId(), sli.getQuantity(), sli.getUnitPrice()})
                .collect(Collectors.toList());

        jdbc.batchUpdate(sliSql, paramList);
    }

    @Override
    public void remove(int id) {

    }
}
