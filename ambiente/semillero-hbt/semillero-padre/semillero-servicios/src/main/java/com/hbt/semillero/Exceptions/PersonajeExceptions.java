package com.hbt.semillero.Exceptions;

public class PersonajeExceptions extends Exception {

	private String codigo;
	private String mensaje;

	public PersonajeExceptions(String codigo, String mensaje, Throwable causa) {
		super(mensaje, causa);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public PersonajeExceptions(String string, String string2) {
		super();
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
