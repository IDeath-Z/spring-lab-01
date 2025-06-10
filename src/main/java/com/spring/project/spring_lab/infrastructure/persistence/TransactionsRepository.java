package com.spring.project.spring_lab.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.spring_lab.domain.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, UUID> {

}
