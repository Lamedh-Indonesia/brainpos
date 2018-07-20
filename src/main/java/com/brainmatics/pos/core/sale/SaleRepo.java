package com.brainmatics.pos.core.sale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SaleRepo extends PagingAndSortingRepository<Sale, Integer> {
//    @Query("from Sale s where s.id = ?1%")
//    Page<Sale> search(String key, Pageable page);
}
