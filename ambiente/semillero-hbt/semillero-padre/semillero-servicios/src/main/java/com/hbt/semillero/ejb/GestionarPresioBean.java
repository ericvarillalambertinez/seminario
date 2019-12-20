package com.hbt.semillero.ejb;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los precio de los comics
 * 
 * @author ccastano
 * @version
 */

public class GestionarPresioBean implements IgestionarPrecioLocal{
	private double precio;
	private double precioTotal;

	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	/*
	 * Metodo que calcula el precio de los comics de acuerto a su tematica 
	 * */

	@Override
	public double CalcularIva(String nombre, double precio) {
		// TODO Auto-generated method stub
		if(nombre.equals("AVENTURAS") || nombre.equals("FANTASTICO") || nombre.equals("HISTORICO") ){
			this.setPrecio(precio*0.05);
			this.setPrecioTotal(getPrecio()+precio);
			return this.getPrecioTotal();			
		}else if(nombre.equals("BELICO") || nombre.equals("CIENCIA_FICCION")){
			this.setPrecio(precio*0.16);
			this.setPrecioTotal(getPrecio()+precio);
			return this.getPrecioTotal();		
		}else if(nombre.equals("DEPORTIVO")) {
			this.setPrecio(precio*0.1);
			this.setPrecioTotal(getPrecio()+precio);
			return this.getPrecioTotal();
		}else {
			return precio;	
		}
		
		
		
	}


	
	
}
