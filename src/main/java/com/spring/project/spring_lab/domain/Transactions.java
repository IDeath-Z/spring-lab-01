package com.spring.project.spring_lab.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.spring.project.spring_lab.domain.enums.TransactionTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "transactions")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "sender_wallet_id", nullable = false, unique = true)
    private Wallets senderWallet;

    @OneToOne
    @JoinColumn(name = "receiver_wallet_id", nullable = false, unique = true)
    private Wallets receiverWallet;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private TransactionTypes type;

    @Column(nullable = false, updatable = false, insertable = false)
    private LocalDateTime date;
}
