package com.chengma.devplatform.repository;


import com.chengma.devplatform.domain.HppMobileValidate;
import com.chengma.devplatform.domain.MailValidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the MobileValidate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MailValidateRepository extends JpaRepository<MailValidate,String> {
    
}
