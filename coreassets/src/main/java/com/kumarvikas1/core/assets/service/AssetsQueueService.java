package com.kumarvikas1.core.assets.service;

import com.kumarvikas1.core.assets.metrics.TimeTaken;
import com.kumarvikas1.core.models.Assets;
import com.kumarvikas1.core.models.BankingResponse;
import com.kumarvikas1.core.models.Error;
import java.util.concurrent.CompletableFuture;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by vikakumar on 1/2/18.
 */
@Component
@Profile("queue")
public class AssetsQueueService implements CoreService {

	@Autowired RabbitTemplate rabbitTemplate;


	@TimeTaken(name = "assetsQueueService")
	@Cacheable(value = "core-assets",key="#accountId")
	@Override public BankingResponse getAssets(String accountId) {
		Assets assets = new Assets();
		assets.setAccountId(accountId);
		CompletableFuture<Assets> assets_1 = CompletableFuture.supplyAsync(() -> (Assets)rabbitTemplate.convertSendAndReceive("core","banking-test",accountId));
		CompletableFuture<Assets> assets_2 = CompletableFuture.supplyAsync(() -> (Assets)rabbitTemplate.convertSendAndReceive("core","stocks-test",accountId));
		try {
			assets.setTotal(assets_1.get().getTotal() + assets_2.get().getTotal());
		} catch (Exception e){
			assets.setTotal(0.00);
		}
		return assets;
	}
}
