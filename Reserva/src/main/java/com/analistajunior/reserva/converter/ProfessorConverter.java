package com.analistajunior.reserva.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.repository.Professores;



@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter{
	
	@Inject
	Professores professores;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = professores.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Professor professor = (Professor) value;
			return professor.getId() == null ? null : professor.getId().toString();
		}
		
		return "";
	}

}
