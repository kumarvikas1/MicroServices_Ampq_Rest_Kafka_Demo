package com.kumarvikas1.core.assets.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by vikakumar on 12/31/17.
 */
@Configuration
@EnableRabbit
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RabbitConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setAddresses("localhost:5672");
		return connectionFactory;
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("core");
	}


	@Bean
	public Binding bindingBanking(DirectExchange exchange) {
		return BindingBuilder.bind(queueBanking())
				.to(exchange)
				.with("banking-test");
	}

	@Bean
	public Binding bindingStocks(DirectExchange exchange) {
		return BindingBuilder.bind(queueStocks())
				.to(exchange)
				.with("stocks-test");
	}


	@Bean
	public Queue queueBanking() {
		return new Queue("banking-test");
	}

	@Bean
	public Queue queueStocks() {
		return new Queue("stocks-test");
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
