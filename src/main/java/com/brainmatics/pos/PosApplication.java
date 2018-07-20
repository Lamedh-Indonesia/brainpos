package com.brainmatics.pos;

import com.brainmatics.pos.core.employee.Address;
import com.brainmatics.pos.core.employee.Employee;
import com.brainmatics.pos.core.employee.EmployeeRepo;
import com.brainmatics.pos.core.product.Product;
import com.brainmatics.pos.core.product.ProductRepo;
import com.brainmatics.pos.core.product.ProductService;
import com.brainmatics.pos.core.sale.Sale;
import com.brainmatics.pos.core.sale.SaleLineItem;
import com.brainmatics.pos.core.sale.SaleRepo;
import com.brainmatics.pos.infra.data.inmemory.ProductMemRepo;
import com.brainmatics.pos.infra.data.jdbc.ProductJdbcRepo;
import com.brainmatics.pos.infra.data.jdbc.SaleJdbcRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class PosApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    ProductRepo productRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    SaleRepo saleRepo;

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        initDb();

//        saleRepo.findById(200).ifPresent(s -> System.out.println(s.getId()));
//        saleRepo.findById(200)
//                .ifPresent(s -> System.out.println(s.getCashier().getName()));
//        saleRepo.findById(200).ifPresent(s -> {
//            for (SaleLineItem sli: s.getLineItems()) {
//                System.out.println(sli.getQuantity());
//            }
//        });

//        int page = 1;
//        Page<Sale> salePages = saleRepo.findAll(PageRequest.of(page, 2));
//        System.out.printf("Page %d of %d\n", page, salePages.getTotalPages());
//        salePages.forEach(s -> {
//            System.out.println(s.getId());
//        });
    }

    public void initDb() {
        Product p1 = new Product();
        p1.setId(1);
        p1.setCode("P01");
        p1.setName("Momogi");
        p1.setPrice(BigDecimal.valueOf(500));
        Object o;
        Product p2 = new Product();
        p2.setId(2);
        p1.setCode("P02");
        p2.setName("Pepsi");
        p2.setPrice(BigDecimal.valueOf(5000));

        Product p3 = new Product();
        p3.setId(3);
        p1.setCode("P03");
        p3.setName("Ajam");
        p3.setPrice(BigDecimal.valueOf(50_000));

        productRepo.save(p1);
        productRepo.save(p2);
        productRepo.save(p3);

        Employee e1 = new Employee();
        e1.setId(1);
        e1.setCode("E01");
        e1.setName("Michael Suyama");
        e1.setBirthDate(LocalDate.of(1970, 3, 20));

        Employee e2 = new Employee();
        e2.setId(2);
        e1.setCode("E02");
        e2.setName("Nancy Davolio");
        e2.setBirthDate(LocalDate.of(1960, 3, 3));

        employeeRepo.save(e1);
        employeeRepo.save(e2);

        Sale sale1 = new Sale();
        sale1.setCashier(e1);
        sale1.setId(200);
        sale1.addLineItem(p1, 2);
        sale1.addLineItem(p2, 1);

        Sale sale2 = new Sale();
        sale2.setCashier(e2);
        sale2.setId(201);
        sale2.addLineItem(p2, 1);
        sale2.addLineItem(p3, 1);

        Sale sale3 = new Sale();
        sale3.setCashier(e2);
        sale3.setId(202);
        sale3.addLineItem(p1, 8);
        sale3.addLineItem(p3, 1);

        Sale sale4 = new Sale();
        sale4.setCashier(e1);
        sale4.setId(203);
        sale4.addLineItem(p2, 1);
        sale4.addLineItem(p3, 1);

        saleRepo.save(sale1);
        saleRepo.save(sale2);
        saleRepo.save(sale3);
        saleRepo.save(sale4);
    }

    public void howToUseOptional() {
        Optional<Product> opt = productRepo.findById(10);
        opt.map(p -> p.getPrice().multiply(BigDecimal.valueOf(2)))
           .ifPresent(price -> System.out.println(price));

        opt.map(p -> {
            System.out.println(p.getName());
            return 0;
        });

        Product prod = opt.orElse(new Product());
        System.out.println(prod.getName());

        opt.ifPresent(product -> System.out.println(product.getName()));
    }
}
