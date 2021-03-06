package com.chengma.devplatform.repository;

import com.chengma.devplatform.domain.TlbMtPrice;
import com.chengma.devplatform.domain.TlbMtPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the SysMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TlbMtPriceHistoryRepository extends JpaRepository<TlbMtPriceHistory,String> {

}
