package com.kumarvikas1.core.models;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class TransactionsDeserializer extends JsonDeserializer<Transaction[]> {

	public TransactionsDeserializer() {
		super(Transaction[].class);
	}

}
