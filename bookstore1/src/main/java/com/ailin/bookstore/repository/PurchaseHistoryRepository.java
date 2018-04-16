package com.ailin.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ailin.bookstore.model.PurchaseHistory;


public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

	PurchaseHistory findByUsername(String username);
}
