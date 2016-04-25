package com.kla.simplecrud.dao;


import com.kla.simplecrud.model.CookieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieTypeRepository extends JpaRepository<CookieType, String> {

    @Query("select c from CookieType c where type = :cookieCode")
    CookieType findCookieByTypeCode(@Param("cookieCode") String cookieCode);
}
