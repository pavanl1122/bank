package com.wecp.progressive.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    @Query("select c from Customers c where c.customerId=:customerId")
    public Customers findByCustomerId(@Param("customerId") int customerId);
    // @Query("delete from Customers c where c.customerId=:customerId")
    // public void deleteByCustomerId(@Param("customerId") int customerId);
    @Query("select c from Customers c where c.email=:email")
    public Customers findByEmail(@Param("email") String email);
    @Query("select c from Customers c where c.username=:username")
    public Customers findByUsername(@Param("username") String username);
    @Modifying
    @Transactional
    @Query("delete from Customers c where c.customerId = :customerId")
    void deleteByCustomerId(@Param("customerId") int customerId);

}