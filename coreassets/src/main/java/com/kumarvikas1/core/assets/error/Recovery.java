package com.kumarvikas1.core.assets.error;

import com.kumarvikas1.core.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by vikakumar on 1/7/18.
 */
@Component
@Scope("singleton")
public class Recovery {

	private final List<List<Transaction>> transactions = new ArrayList<>();

	public void addErrorTransactions(List<Transaction> transaction) {
		transactions.add(transaction);
	}

}
