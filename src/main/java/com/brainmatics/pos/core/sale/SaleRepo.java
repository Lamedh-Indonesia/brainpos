package com.brainmatics.pos.core.sale;

import com.brainmatics.common.Repository;

public interface SaleRepo extends Repository<Sale> {

    Sale getByIdEager(int id);
}
