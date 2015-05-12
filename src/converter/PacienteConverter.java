package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.HibernateUtil;
import entidade.Paciente;

@FacesConverter(forClass = Paciente.class)
public class PacienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
			
			Paciente retorno = null;
			
		if (value != null && !value.equals("")) {

			EntityManager manager = HibernateUtil.getEntityManager();
			TypedQuery<Paciente> query = manager.createQuery("select p from Paciente p where p.id = :id", Paciente.class);
			query.setParameter("id", new Integer(value));
			retorno = query.getSingleResult();
			
			if(retorno == null){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao foi possivel editar", "Nao foi possivel editar");
			throw new ConverterException(message);
			}
		}

			return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if (obj != null) {
			Integer codigo = ((Paciente) obj).getId();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}
}
