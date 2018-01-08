package com.kumarvikas1.core.natwest;

import com.kumarvikas1.core.models.Assets;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Created by vikakumar on 1/2/18.
 */
@Component
public class AccountDetails {

	private final Map<String,Assets> assetsHashMap = new HashMap<>();

	@PostConstruct
	public void setUp() {
		Assets assets = new Assets();
		assets.setAccountId("1");
		assets.setTotal(500.1);
		assetsHashMap.put("1",assets);
		Assets assets_1 = new Assets();
		assets_1.setAccountId("2");
		assets_1.setTotal(700.00);
		assetsHashMap.put("2",assets_1);
	}


	public Assets getAccounts(String accountId) {
		return assetsHashMap.get(accountId);
	}

	public void addAccounts(Assets assets){
		assetsHashMap.put(assets.getAccountId(),assets);
	}

}
