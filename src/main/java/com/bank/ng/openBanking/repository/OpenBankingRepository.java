package com.bank.ng.openBanking.repository;

import com.bank.ng.openBanking.entity.OB_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenBankingRepository extends JpaRepository<OB_User, String> {
}
