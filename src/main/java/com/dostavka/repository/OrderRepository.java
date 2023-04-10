package com.dostavka.repository;

import com.dostavka.domain.Order;
import com.dostavka.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT o FROM Order o WHERE o.sum=:sum")
    Optional<Order> findOrderBySum(Double sum);

}
