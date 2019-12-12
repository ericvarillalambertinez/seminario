package com.hbt.semillero.ejb;

import java.math.BigDecimal;

import javax.ejb.Local;

@FunctionalInterface
@Local
public interface IgestionarPrecioLocal {
	
	public double CalcularIva(String nombre, double precio);

}
