package com.rank.interactive.app.model;

import java.math.BigDecimal;

public class WaggerRequest {

	protected long transactionID;
	protected long PlayerID;
	protected BigDecimal amount;
	protected String promoCode;

	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public long getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(long playerID) {
		PlayerID = playerID;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

}
