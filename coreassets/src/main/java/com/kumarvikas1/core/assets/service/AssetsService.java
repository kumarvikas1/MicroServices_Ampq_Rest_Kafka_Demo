package com.kumarvikas1.core.assets.service;

import com.kumarvikas1.core.assets.metrics.TimeTaken;
import com.kumarvikas1.core.models.Assets;
import com.kumarvikas1.core.models.BankingResponse;
import com.kumarvikas1.core.models.Error;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vikakumar on 1/2/18.
 */
@Service
@Profile("rest")
public class AssetsService implements CoreService{

	@Autowired
	private AccountExistService accountExistService;
	@Autowired
	private KafkaTemplate kafkaTemplate;
	@Autowired
	private RestTemplate restTemplate;



	@Value("${stocks.server}")
	private String stocksServer;
	@Value("${bank.server}")
	private String bankServer;

	@TimeTaken(name = "assetsService")
	@Cacheable(value = "core-assets",key="#accountId")
	@Override
	public BankingResponse getAssets(String accountId) {
		List<CompletableFuture<Assets>> completableFutures = new ArrayList<>();
		if(accountExistService.isAccountKnown(accountId)) {
			Assets retval = new Assets();
			completableFutures.add(CompletableFuture.supplyAsync(() -> getForObject(accountId, stocksServer)));
			completableFutures.add(CompletableFuture.supplyAsync(() -> getForObject(accountId, bankServer)));

			retval.setAccountId(accountId);
			retval.setTotal(completableFutures.stream().map(f -> {
				try {
					return f.get();
				} catch (Exception e) {
					kafkaTemplate.send("accountError", 2, accountId);
					return null;
				}
			}).filter(Objects::nonNull).mapToDouble(Assets::getTotal).sum());
			return retval;
		} else {
			kafkaTemplate.send("accountError",1,accountId);
			Error retval = new Error();
			retval.setErrorDescription("Account not exisit");
			return retval;
		}
	}

	private Assets getForObject(String accountId,String url){
		return restTemplate.getForObject(getUrl(accountId,url), Assets.class);
	}

	private URI getUrl(String accountId,String url) {
		try {
			return new URI(MessageFormat.format(url, accountId));
		} catch (Exception e){
			throw new IllegalStateException(e);
		}
	}
}
