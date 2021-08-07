package com.rank.interactive.app.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rank.interactive.app.model.Payload;
import com.rank.interactive.app.model.Player;
import com.rank.interactive.app.repo.LogsRepository;
import com.rank.interactive.app.repo.PlayerRepository;
import com.rank.interactive.app.repo.TransactionRepository;
import com.rank.interactive.app.service.RankServices;

@Service
public class RankServicesImpl implements RankServices {

	@Autowired
	TransactionRepository transactionRepo;

	@Autowired
	LogsRepository logsRepo;

	@Autowired
	PlayerRepository playerRepo;

	@Override
	public ResponseEntity<String> getBalance(long playerID, long transactionID) {
		// Get player
		Player player = playerRepo.getOne(playerID);
		if (player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player does not exist");

		}
		// Get player balance
		BigDecimal balance = transactionRepo.getPlayerBalance(playerID, transactionID);

		return ResponseEntity.status(HttpStatus.OK).body("Balance: " + balance);

	}

	@Override
	public ResponseEntity<String> makeWager(Payload wager) {
		// Get player
		Player player = playerRepo.getOne(wager.getPlayerID());
		if (player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player does not exist");

		}

		// Get player balance
		BigDecimal balance = transactionRepo.getPlayerBalance(wager.getPlayerID(), wager.getTransactionID());

		if (balance.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(wager.getAmount()) >= 0) {
			balance = balance.subtract(wager.getAmount());
			transactionRepo.updateBalance(balance, wager.getPlayerID(), wager.getTransactionID());
		}

		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Insufficient amount");

	}

	@Override
	public ResponseEntity<String> depositWinnings(Payload winnings) {
		// Get player
		Player player = playerRepo.getOne(winnings.getPlayerID());
		if (player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player does not exist");

		}
		// Get player balance
		BigDecimal balance = transactionRepo.getPlayerBalance(winnings.getPlayerID(), winnings.getTransactionID());

		balance = balance.add(winnings.getAmount());
		transactionRepo.updateBalance(balance, winnings.getPlayerID(), winnings.getTransactionID());
		return ResponseEntity.status(HttpStatus.OK).body("Balance update with " + winnings.getAmount());
	}

}
