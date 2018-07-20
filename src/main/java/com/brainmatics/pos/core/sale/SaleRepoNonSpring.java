package com.brainmatics.pos.core.sale;

import com.brainmatics.common.RepositoryNonSpring;

public interface SaleRepoNonSpring extends RepositoryNonSpring<Sale> {

    Sale getByIdEager(int id);
}
