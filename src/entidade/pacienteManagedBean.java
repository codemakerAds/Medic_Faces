package entidade;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import util.HibernateUtil;
import entidade.Paciente;

@ManagedBean
@RequestScoped
public class pacienteManagedBean {

	private Paciente paciente = new Paciente();
	private List<Paciente> pacientes = null;

	public void salvar(Paciente paciente) {

		if (paciente.getNome_paciente() != null && !paciente.getNome_paciente().equals("")) {

			EntityManager manager = HibernateUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.persist(paciente);
			manager.getTransaction().commit();
		    manager.close();

			FacesMessage faceMessage = new FacesMessage(
					"Registro inserido com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);

		} else {
			FacesMessage faceMessage = new FacesMessage(
					"Paciente deve ter nome");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		}

	}
	
	
	public void alterar(Paciente paciente) {

		if (paciente.getNome_paciente() != null && !paciente.getNome_paciente().equals("")) {

			EntityManager manager = HibernateUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.merge(paciente);
			manager.getTransaction().commit();
			this.paciente = new Paciente();
			manager.close();

			FacesMessage faceMessage = new FacesMessage(
					"Registro inserido com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);

		} else {
			FacesMessage faceMessage = new FacesMessage(
					"Paciente deve ter nome");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		}

	}
	
	
	public void remove(Paciente paciente) {
		EntityManager manager = HibernateUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(paciente));
		manager.getTransaction().commit();
		manager.close();

		FacesMessage faceMessage = new FacesMessage(
				"Registro removido com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, faceMessage);

	}

	public void consultar() {
		this.pacientes = buscarPorCodigo(paciente);
	}
	
	public void consultarTodos() {
		this.pacientes = buscarTodos(paciente);
	}

	public List<Paciente> buscarPorCodigo(Paciente paciente) {
		EntityManager manager = HibernateUtil.getEntityManager();
		TypedQuery<Paciente> query = manager.createQuery(
				"select p from Paciente p where p.id = :id", Paciente.class);
		query.setParameter("id", paciente.getId());
		List<Paciente> resultados = query.getResultList();
		return resultados;
	}
	
	
	public List<Paciente> buscarTodos(Paciente paciente) {
		EntityManager manager = HibernateUtil.getEntityManager();
		TypedQuery<Paciente> query = manager.createQuery(
				"select p from Paciente p", Paciente.class);
		query.setParameter("id", paciente.getId());
		List<Paciente> resultados = query.getResultList();
		return resultados;
	}

	public List<Paciente> getPacientes() {
		if (this.pacientes == null) {
			EntityManager manager = HibernateUtil.getEntityManager();
			Query query = manager.createQuery("select p from Paciente p", Paciente.class);
			this.pacientes = query.getResultList();
			manager.close();
		}

		return pacientes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
