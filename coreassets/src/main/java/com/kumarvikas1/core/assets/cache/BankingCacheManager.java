package com.kumarvikas1.core.assets.cache;

import java.util.Collections;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vikakumar on 1/7/18.
 */
@Configuration
@EnableCaching
public class BankingCacheManager {

	@Bean
	public CacheManager createSimpleCacheManager() {
		final SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		simpleCacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("core-assets")));
		return simpleCacheManager;
	}

}
