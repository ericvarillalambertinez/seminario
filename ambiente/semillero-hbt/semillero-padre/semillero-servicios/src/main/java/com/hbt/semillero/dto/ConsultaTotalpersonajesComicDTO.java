package com.hbt.semillero.dto;

public class ConsultaTotalpersonajesComicDTO {

	private Long Total;
	private String comic;
	
	public ConsultaTotalpersonajesComicDTO() {
		
	}
	public ConsultaTotalpersonajesComicDTO(Long total, String comic) {
		super();
		Total = total;
		this.comic = comic;
	}
	public Long getTotal() {
		return Total;
	}
	public void setTotal(Long total) {
		Total = total;
	}
	public String getComic() {
		return comic;
	}
	public void setComic(String comic) {
		this.comic = comic;
	}
	
	
}
