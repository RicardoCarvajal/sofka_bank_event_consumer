package com.sofka.services.app.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import reactor.core.publisher.Mono;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;

@Configuration
public class RabbitConf {

	Logger logger = LoggerFactory.getLogger(RabbitConf.class);

	public static final String QUEUE_LOGS = "sofkaBank-logs";

	public static final String EXCHANGE_NAME = "logs-exchange";
	public static final String ROUTING_KEY_NAME = "logs.routing.key";

	@Value("${app.rabbit.url}")
	public String uri_name;

	@Bean
	public AmqpAdmin amqpAdmin() {
		logger.info("Conexion RABBITMQ SOFKABANK: " + uri_name);

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(URI.create(uri_name));
		var amqpAdmin = new RabbitAdmin(connectionFactory);
		return amqpAdmin;
	}

	@Bean
	public Mono<Connection> connMono() throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		logger.info("Conexion RABBITMQ SOFKABANK: " + uri_name);
		connectionFactory.setUri(uri_name);
		connectionFactory.useNio();
		return Mono.fromCallable(() -> connectionFactory.newConnection());
	}

	@Bean
	public ReceiverOptions receiverOptions(Mono<Connection> connectionMono) {
		return new ReceiverOptions().connectionMono(connectionMono);
	}

	@Bean
	public Receiver receiver(ReceiverOptions receiverOptions) {
		return RabbitFlux.createReceiver(receiverOptions);
	}

}
