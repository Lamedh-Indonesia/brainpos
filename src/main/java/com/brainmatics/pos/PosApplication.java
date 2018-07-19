package com.brainmatics.pos;

import com.brainmatics.pos.core.employee.Employee;
import com.brainmatics.pos.core.product.Product;
import com.brainmatics.pos.core.product.ProductService;
import com.brainmatics.pos.core.sale.Sale;
import com.brainmatics.pos.core.sale.SaleLineItem;
import com.brainmatics.pos.infra.data.inmemory.ProductMemRepo;
import com.brainmatics.pos.infra.data.jdbc.ProductJdbcRepo;
import com.brainmatics.pos.infra.data.jdbc.SaleJdbcRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class PosApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbc;

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        jdbc.execute("CREATE TABLE Employee (id int primary key, name varchar(50))");
//        jdbc.execute("INSERT INTO Employee (id, name) VALUES (1, 'Michael Suyama')");
//        jdbc.execute("INSERT INTO Employee (id, name) VALUES (2, 'Nancy Davolio')");
//        jdbc.execute("INSERT INTO Employee (id, name) VALUES (3, 'Janet Leverling')");

//        List<Employee> emps = jdbc.query("SELECT id, name FROM Employee", (rs, num) -> {
//            Employee e = new Employee();
//            e.setId(rs.getInt("id"));
//            e.setName(rs.getString("name"));
//            return e;
//        });
//
//        for (Employee e: emps)
//            System.out.println(e.getName());

//        ProductJdbcRepo repo = new ProductJdbcRepo(jdbc);
//        for (Product p: repo.getAll()) {
//            System.out.println(p.getName());
//        }

//        Product p = repo.getById(2);
//        System.out.println(p.getName());
//        System.out.println(repo.getCount());

//        SaleJdbcRepo saleRepo = new SaleJdbcRepo(jdbc);
//        Sale s = saleRepo.getByIdEager(1);
//        for (SaleLineItem sli: s.getLineItems()) {
//            System.out.println(sli.getProduct().getName());
//        }

        initDb();
    }

	public void initDb() {
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Momogi");
        p1.setPrice(BigDecimal.valueOf(500));

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Pepsi");
        p2.setPrice(BigDecimal.valueOf(5000));

        Product p3 = new Product();
        p3.setId(3);
        p3.setName("Ajam");
        p3.setPrice(BigDecimal.valueOf(50_000));

        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("Michael Suyama");
        e1.setBirthDate(LocalDate.of(1970, 3, 20));

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("Nancy Davolio");
        e2.setBirthDate(LocalDate.of(1960, 3, 3));

//        ProductJdbcRepo productRepo = new ProductJdbcRepo(jdbc);
//        productRepo.save(p1);
//        productRepo.save(p2);
//        productRepo.save(p3);

        Sale sale = new Sale();
        sale.setCashier(e2);
        sale.setId(101);
        sale.addLineItem(p2, 1);
        sale.addLineItem(p3, 1);

        SaleJdbcRepo saleRepo = new SaleJdbcRepo(jdbc);
        saleRepo.save(sale);

//        Product prd2 = repo.getById(2);
//        System.out.println(prd2.getName());

        // tampilkan semua product
//        ArrayList<Product> all = repo.getAll();
//        for (Product p: all)
//            System.out.println(p.getName());

//        ProductService service = new ProductService(new ProductMongoRepo());
//        ProductService service = new ProductService(new ProductMemRepo());
//        System.out.println(service.generateCode());
	}
}
