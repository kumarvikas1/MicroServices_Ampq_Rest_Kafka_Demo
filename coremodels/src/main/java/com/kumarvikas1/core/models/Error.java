package com.kumarvikas1.core.models;

import java.io.Serializable;

/**
 * Created by vikakumar on 1/2/18.
 */
public class Error implements BankingResponse, Serializable {

	private String errorDescription;

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorDescription() {
		return errorDescription;
	}
}
