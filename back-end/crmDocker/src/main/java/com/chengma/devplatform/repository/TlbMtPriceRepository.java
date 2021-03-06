package com.chengma.devplatform.repository;

import com.chengma.devplatform.domain.TlbMtPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the SysMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TlbMtPriceRepository extends JpaRepository<TlbMtPrice,String> {

    public TlbMtPrice findTlbMtPriceBySymbolEquals(String symbol);

}
