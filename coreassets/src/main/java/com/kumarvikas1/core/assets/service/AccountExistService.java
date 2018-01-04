package com.kumarvikas1.core.assets.service;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by vikakumar on 1/2/18.
 */
@Service
public class AccountExistService {

	private final List<String> accountIds = ImmutableList.of("1","2","3","4");

	public boolean isAccountKnown(String accountId) {
		return accountIds.contains(accountId);
	}

}

