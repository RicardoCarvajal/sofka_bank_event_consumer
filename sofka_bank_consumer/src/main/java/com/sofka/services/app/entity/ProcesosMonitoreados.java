package com.sofka.services.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("procesos_monitoreados")
public class ProcesosMonitoreados {

	@Id
	private String id;

	private String clase;

	private String metodo;

	private String tipo;

	private Integer linea;

	private Long nanoSegundos;

	private String mensaje;

	private Object datos;

	public ProcesosMonitoreados() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public Long getNanoSegundos() {
		return nanoSegundos;
	}

	public void setNanoSegundos(Long nanoSegundos) {
		this.nanoSegundos = nanoSegundos;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getDatos() {
		return datos;
	}

	public void setDatos(Object datos) {
		this.datos = datos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static ProcesosMonitoreados createProcesosMonitoreados() {
		return new ProcesosMonitoreados();
	}

	public ProcesosMonitoreados id(String id) {
		this.id = id;
		return this;
	}

	public ProcesosMonitoreados clase(String clase) {
		this.clase = clase;
		return this;
	}

	public ProcesosMonitoreados metodo(String metodo) {
		this.metodo = metodo;
		return this;
	}

	public ProcesosMonitoreados linea(Integer linea) {
		this.linea = linea;
		return this;
	}

	public ProcesosMonitoreados fecha(Long nanoSegundos) {
		this.nanoSegundos = nanoSegundos;
		return this;
	}

	public ProcesosMonitoreados mensaje(String mensaje) {
		this.mensaje = mensaje;
		return this;
	}

	public ProcesosMonitoreados datos(Object datos) {
		this.datos = datos;
		return this;
	}

	public ProcesosMonitoreados tipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public ProcesosMonitoreados build() {
		return this;
	}

}
