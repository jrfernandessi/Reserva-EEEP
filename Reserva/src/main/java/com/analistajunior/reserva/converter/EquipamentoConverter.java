package com.analistajunior.reserva.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.analistajunior.reserva.model.Equipamento;
import com.analistajunior.reserva.repository.Equipamentos;

@FacesConverter(forClass=Equipamento.class)
public class EquipamentoConverter implements Converter{
	@Inject
	Equipamentos equipamentos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Equipamento retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = equipamentos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Equipamento equipamento = (Equipamento) value;
			return equipamento.getId() == null ? null : equipamento.getId().toString();
		}
		
		return "";
	}
}
