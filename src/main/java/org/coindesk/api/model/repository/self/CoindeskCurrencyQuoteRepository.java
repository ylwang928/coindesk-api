package org.coindesk.api.model.repository.self;

import org.coindesk.api.model.domain.entity.self.CoindeskCurrencyQuoteEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoindeskCurrencyQuoteRepository extends PagingAndSortingRepository
    <CoindeskCurrencyQuoteEntity, Long> {
}
