package com.rank.interactive.app.repo;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rank.interactive.app.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Modifying
	@Transactional
	@Query(value = "update Transaction t set t.balance = ? where t.player_id = ? and t.id = ?", nativeQuery = true)
	void updateBalance(BigDecimal balance, Long playerID, Long transactionID);

	@Query(value = "select t.balance from Transaction t where t.player_id = ? and t.id = ?", nativeQuery = true)
	BigDecimal getPlayerBalance(Long playerID, Long transactionID);

	@Query(value = "select * from Transaction t where t.player_id = ? and t.id = ?", nativeQuery = true)
	Transaction getTransaction(Long playerID, Long transactionID);
}
