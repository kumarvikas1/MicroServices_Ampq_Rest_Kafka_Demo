package com.kumarvikas1.core.natwest.queue;

import com.kumarvikas1.core.models.Assets;
import com.kumarvikas1.core.natwest.AccountDetails;
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
@RabbitListener(queues = "banking-test")
public class AccountReceiver {

	@Autowired AccountDetails accountDetails;

	@RabbitHandler
	public Assets receive(String accountId) {
		return accountDetails.getAccounts(accountId);
	}

}
