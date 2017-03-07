package com.analistajunior.reserva.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter{
	
//	@Inject
	Professores professores;
	
	public ProfessorConverter() {
		professores = CDIServiceLocator.getBean(Professores.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor retorno = null;
		
		if (value != null) {
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
