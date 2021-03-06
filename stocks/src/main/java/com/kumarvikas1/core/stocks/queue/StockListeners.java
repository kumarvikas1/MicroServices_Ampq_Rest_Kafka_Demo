package com.kumarvikas1.core.stocks.queue;

import com.kumarvikas1.core.models.Assets;
import com.kumarvikas1.core.models.Transaction;
import com.kumarvikas1.core.models.Transaction.Type;
import com.kumarvikas1.core.stocks.StocksDetails;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
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

	@Autowired StocksDetails stocksDetails;


	@KafkaListener(id = "order",topics = "transactions",containerFactory = "kafkaListenerContainerFactory")
	public void receive(Transaction[] payload,@Header(KafkaHeaders.OFFSET) Integer offset, Acknowledgment ack, ConsumerRecord consumerRecord) {
		Arrays.asList(payload).stream().filter(f -> f.getType().equals(Type.Stocks)).map(getTransactionAssetsFunction())
				.forEach(stocksDetails::addAccounts);
		ack.acknowledge();
	}

	private Function<Transaction, Assets> getTransactionAssetsFunction() {
		return f -> {
			Assets retval = new Assets();
			retval.setAccountId(f.getAccountId());
			retval.setTotal(Double.valueOf(Optional.ofNullable(f.getCreditAmount()).orElse("0.0"))-
					Double.valueOf(Optional.ofNullable(f.getDebitAmount()).orElse("0.0")));
			return retval;
		};
	}


}
