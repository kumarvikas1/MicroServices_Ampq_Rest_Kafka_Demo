package com.kumarvikas1.core.assets.service;

import com.kumarvikas1.core.models.BankingResponse;

/**
 * Created by vikakumar on 1/2/18.
 */
public interface CoreService {
	BankingResponse getAssets(String accountId);
}
