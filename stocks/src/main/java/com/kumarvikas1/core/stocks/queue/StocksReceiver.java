package com.kumarvikas1.core.stocks.queue;

import com.kumarvikas1.core.models.Assets;
import com.kumarvikas1.core.stocks.StocksDetails;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by vikakumar on 1/3/18.
 */
@Component
@Profile("queue")
@RabbitListener(queues = "stocks-test")
public class StocksReceiver {

	@Autowired StocksDetails stocksDetails;

	@RabbitHandler
	public Assets receive(String accountId) {
		return stocksDetails.getAccounts(accountId);
	}

}
