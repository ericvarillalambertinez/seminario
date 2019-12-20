package com.hbt.semillero.ejb;

import javax.ejb.Local;

@FunctionalInterface
@Local
public interface IgestionarPrecioLocal {
	
	public double CalcularIva(String nombre, double precio);

}
