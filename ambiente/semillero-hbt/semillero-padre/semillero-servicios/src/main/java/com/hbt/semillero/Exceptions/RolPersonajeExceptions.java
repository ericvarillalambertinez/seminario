package com.hbt.semillero.Exceptions;

public class RolPersonajeExceptions extends Exception {

	private String codigo;
	private String mensaje;

	public RolPersonajeExceptions(String codigo, String mensaje, Throwable causa) {
		super(mensaje, causa);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
