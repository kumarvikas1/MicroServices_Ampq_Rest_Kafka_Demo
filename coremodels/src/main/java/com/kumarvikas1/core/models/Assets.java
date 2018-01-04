package com.kumarvikas1.core.models;

import java.io.Serializable;

/**
 * Created by vikakumar on 1/2/18.
 */
public class Assets implements Serializable,BankingResponse{
	private String accountId;

	private Double total;

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getAccountId() {
		return accountId;
	}

	public Double getTotal() {
		return total;
	}
}
