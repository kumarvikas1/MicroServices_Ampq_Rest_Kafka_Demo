package com.kumarvikas1.core.assets.service;

import com.kumarvikas1.core.assets.error.Recovery;
import com.kumarvikas1.core.models.BankingResponse;
import com.kumarvikas1.core.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Created by vikakumar on 1/2/18.
 */
public abstract class CoreService {
	public abstract BankingResponse getAssets(String accountId);

	public abstract KafkaTemplate getKafkaTemplate();

	public abstract Recovery recovery();

	public void postAccountDetails(List<Transaction> transactionList) {
		try {
			getKafkaTemplate().send("transactions", transactionList);
		} catch(Exception e){
			recovery().addErrorTransactions(transactionList);
		}
	}

}
