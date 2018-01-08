package com.kumarvikas1.core.stocks.queue;

import com.kumarvikas1.core.models.Transaction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Created by vikakumar on 12/19/17.
 */
@Component
public class StockListeners {

	@Autowired ConsumerFactory consumerFactory;

	@Autowired
	KafkaConsumer kafkaConsumer;


	@KafkaListener(id = "order",topics = "transactions",containerFactory = "kafkaListenerContainerFactory")
	public void receive(Transaction[] payload,@Header(KafkaHeaders.OFFSET) Integer offset, Acknowledgment ack, ConsumerRecord consumerRecord) {
		System.out.println("payload "+payload.length);
		System.out.println(payload[0].getAccountId());
		ack.acknowledge();
	}

}
