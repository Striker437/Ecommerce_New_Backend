package com.Ecommerce.Config;

import javax.annotation.PreDestroy;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.Ecommerce.Service.ProductService;

@Configuration      //integrate with EhCache
public class EhCacheConfig  {
	
	Logger log=LoggerFactory.logger(ProductService.class);

	private net.sf.ehcache.CacheManager cacheManager;
	
	 @PreDestroy
	    public void destroy() {
	        cacheManager.shutdown();
	    }
	
    @Bean
   CacheManager cacheManager(){
        return new EhCacheCacheManager(CacheManager().getObject());
    }

    private EhCacheManagerFactoryBean CacheManager() {
    	log.debug("Starting Ehcache");
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }

	
}
