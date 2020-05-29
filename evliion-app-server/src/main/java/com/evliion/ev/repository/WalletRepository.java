package com.evliion.ev.repository;

import com.evliion.ev.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository  extends JpaRepository<Wallet, Long> {
}
