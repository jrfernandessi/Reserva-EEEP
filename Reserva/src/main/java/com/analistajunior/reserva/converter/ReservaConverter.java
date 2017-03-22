package com.analistajunior.reserva.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.analistajunior.reserva.model.Reserva;
import com.analistajunior.reserva.repository.Reservas;
import com.analistajunior.reserva.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Reserva.class)
public class ReservaConverter implements Converter{
	
	private Reservas reservas;
	
	public ReservaConverter() {
		reservas = CDIServiceLocator.getBean(Reservas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Reserva retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = reservas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Reserva reserva = (Reserva) value;
			return reserva.getId() == null ? null : reserva.getId().toString();
		}
		
		return "";
	}
	
	
	
}
