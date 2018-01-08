package com.kumarvikas1.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Created by vikakumar on 1/4/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	@JsonProperty("accountId")
	private String accountId;

	@JsonProperty("creditAmount")
	private String creditAmount;

	@JsonProperty("debitAmount")
	private String debitAmount;

	@JsonProperty("date")
	private String date;

	@JsonProperty("type")
	private Type type;

	public String getAccountId() {
		return accountId;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public String getDate() {
		return date;
	}

	public String getDebitAmount() {
		return debitAmount;
	}

	public Type getType() {
		return type;
	}

	static enum Type {
		Account("Account"),
		Stocks("Stocks");

		private String type;

		Type(String type){
			this.type = type;
		}

		@Override public String toString() {
			return type;
		}
	}

}
