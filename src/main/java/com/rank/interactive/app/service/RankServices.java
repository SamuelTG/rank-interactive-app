package com.rank.interactive.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rank.interactive.app.model.Logs;
import com.rank.interactive.app.model.LogsRequest;
import com.rank.interactive.app.model.WaggerRequest;
import com.rank.interactive.app.model.WinningsRequest;

public interface RankServices {

	ResponseEntity<String> getBalance(long playerID, long transactionID);

	ResponseEntity<String> makeWager(WaggerRequest wager);

	ResponseEntity<String> depositWinnings(WinningsRequest winnings);
	
	List<Logs> getLastTenPlayerLogs(LogsRequest logsRequest );

}
