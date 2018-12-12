package com.sady.bean;

public class Card {
	
	private int cardId;
	private String cardName;
	private int expYear;
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public int getExpYear() {
		return expYear;
	}
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	
	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", cardName=" + cardName + ", expYear=" + expYear + "]";
	}
	
	
	

}
