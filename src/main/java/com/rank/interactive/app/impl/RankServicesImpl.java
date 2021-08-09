package com.rank.interactive.app.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rank.interactive.app.model.WinningsRequest;
import com.rank.interactive.app.model.Logs;
import com.rank.interactive.app.model.LogsRequest;
import com.rank.interactive.app.model.Player;
import com.rank.interactive.app.model.WaggerRequest;
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
		Player player = playerRepo.getPlayerByID(playerID);
		if (player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player does not exist");

		}
		// Get player balance
		BigDecimal balance = transactionRepo.getPlayerBalance(playerID, transactionID);

		return ResponseEntity.status(HttpStatus.OK).body("Balance: " + balance);

	}

	@Override
	public ResponseEntity<String> makeWager(WaggerRequest wager) {

		// Get player
		Player player = playerRepo.getPlayerByID(wager.getPlayerID());
		if (player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player does not exist");

		}

		// set logs
		Logs logs = new Logs();
		logs.setAction("Wager");
		logs.setUsername(player.getUsername());

		// Bonus
		String promoCode = wager.getPromoCode();
		int promoCount = player.getPromoCount();

		if (promoCount < 5 && promoCode != null && !promoCode.isEmpty() && promoCode.equalsIgnoreCase("paper")) {
			// update new promo count
			promoCount = promoCount + 1;
			player.setPromoCount(promoCount);
			playerRepo.save(player);//update promoCount

			// save logs
			logs.setResult("success with promo paper");
			logsRepo.save(logs);

			return ResponseEntity.status(HttpStatus.OK).body("Wager made with paper promotion");

		}

		// Get player balance
		BigDecimal balance = transactionRepo.getPlayerBalance(wager.getPlayerID(), wager.getTransactionID());

		if (balance.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(wager.getAmount()) >= 0) {
			balance = balance.subtract(wager.getAmount());
			transactionRepo.updateBalance(balance, wager.getPlayerID(), wager.getTransactionID());

			// save logs
			logs.setResult("success with wager amount: " + wager.getAmount());
			logsRepo.save(logs);

			return ResponseEntity.status(HttpStatus.OK).body("Wager made: " + wager.getAmount());

		}

		logs.setResult("fail with I_AM_A_TEAPOT");
		logsRepo.save(logs);

		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Insufficient amount");

	}

	@Override
	public ResponseEntity<String> depositWinnings(WinningsRequest winnings) {
		// Get player
		Player player = playerRepo.getPlayerByID(winnings.getPlayerID());
		if (player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player does not exist");

		}
		// set logs
		Logs logs = new Logs();
		logs.setAction("Winnings");
		logs.setUsername(player.getUsername());

		// Get player balance
		BigDecimal balance = transactionRepo.getPlayerBalance(winnings.getPlayerID(), winnings.getTransactionID());

		balance = balance.add(winnings.getAmount());
		transactionRepo.updateBalance(balance, winnings.getPlayerID(), winnings.getTransactionID());

		// save logs
		logs.setResult("success with winning amount: " + winnings.getAmount());
		logsRepo.save(logs);

		return ResponseEntity.status(HttpStatus.OK).body("Balance update with " + winnings.getAmount());
	}

	@Override
	public List<Logs> getLastTenPlayerLogs(LogsRequest logsRequest) {
		if(logsRequest.getPassword().equals("swordfish")) {
			return logsRepo.getPlayerlogs(logsRequest.getPlayerUsername().toLowerCase());
		}
		return null;
	}

}
