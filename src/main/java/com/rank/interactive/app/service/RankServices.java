package com.rank.interactive.app.service;

import org.springframework.http.ResponseEntity;

import com.rank.interactive.app.model.Payload;

public interface RankServices {

	ResponseEntity<String> getBalance(long playerID, long transactionID);

	ResponseEntity<String> makeWager(Payload wager);

	ResponseEntity<String> depositWinnings(Payload winnings);

}
