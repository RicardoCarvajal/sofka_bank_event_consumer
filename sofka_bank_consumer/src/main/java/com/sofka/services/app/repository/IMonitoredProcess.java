package com.sofka.services.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.sofka.services.app.entity.ProcesosMonitoreados;

public interface IMonitoredProcess extends ReactiveMongoRepository<ProcesosMonitoreados, String>  {

}
