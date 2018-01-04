package com.kumarvikas1.core.stocks;

import com.kumarvikas1.core.models.Assets;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Created by vikakumar on 1/2/18.
 */
@Component
public class StocksDetails {

	private final Map<String,Assets> assetsHashMap = new HashMap<>();

	@PostConstruct
	public void setUp() {
		Assets assets = new Assets();
		assets.setAccountId("1");
		assets.setTotal(44.11);
		assetsHashMap.put("1",assets);
		Assets assets_1 = new Assets();
		assets_1.setAccountId("2");
		assets_1.setTotal(22.22);
		assetsHashMap.put("2",assets_1);
	}


	public Assets getAccounts(String accountId) {
		return assetsHashMap.get(accountId);
	}

}
