package com.sofka.services.app.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sofka.services.app.config.RabbitConf;
import com.sofka.services.app.entity.ProcesosMonitoreados;
import com.sofka.services.app.repository.IMonitoredProcess;

import reactor.rabbitmq.Receiver;

@Component
public class RabbitMqReceiver implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(RabbitMqReceiver.class);

	private final Receiver receiver;

	private final Gson gson;

	private final IMonitoredProcess iMonitoredProcess;

	public RabbitMqReceiver(Receiver receiver, Gson gson, IMonitoredProcess iMonitoredProcess) {
		this.receiver = receiver;
		this.gson = gson;
		this.iMonitoredProcess = iMonitoredProcess;
	}

	@Override
	public void run(String... args) throws Exception {

		receiver.consumeAutoAck(RabbitConf.QUEUE_LOGS).doOnNext(message -> {

			ProcesosMonitoreados p = gson.fromJson(new String(message.getBody()), ProcesosMonitoreados.class);
			log.info("Consumiendo: " + new String(message.getBody()) + " de cola 1");

			iMonitoredProcess.save(p).subscribe();

		}).subscribe();

	}
}
