package com.rank.interactive.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rank.interactive.app.model.Logs;
import com.rank.interactive.app.model.LogsRequest;
import com.rank.interactive.app.model.WaggerRequest;
import com.rank.interactive.app.model.WinningsRequest;
import com.rank.interactive.app.service.RankServices;

@RestController
public class RankServiceController {

	@Autowired
	RankServices rankService;

	@RequestMapping(value = "/api/getPlayerBalance", method = RequestMethod.GET)
	public ResponseEntity<String> getPlayerBalance(@RequestParam long playerID, @RequestParam long transactionID) {
		return rankService.getBalance(playerID, transactionID);
	}

	@RequestMapping(value = "/api/makeWager", method = RequestMethod.POST)
	public ResponseEntity<String> makeWager(@RequestBody WaggerRequest wager) {
		return rankService.makeWager(wager);
	}

	@RequestMapping(value = "/api/depositWinnings", method = RequestMethod.POST)
	public ResponseEntity<String> depositWinnings(@RequestBody WinningsRequest winnings) {
		return rankService.depositWinnings(winnings);
	}

	@RequestMapping(value = "/api/getLastTenPlayerLogs", method = RequestMethod.POST)
	public List<Logs> getLastTenPlayerLogs(@RequestBody LogsRequest logsRequest) {
		return rankService.getLastTenPlayerLogs(logsRequest);
	}
}
