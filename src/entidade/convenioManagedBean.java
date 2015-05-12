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
import entidade.Convenio;

@ManagedBean
@RequestScoped
public class convenioManagedBean {

	private Convenio convenio = new Convenio();
	private List<Convenio> convenios = null;

	public void salvar(Convenio convenio) {

		if (convenio.getNome_fantasia() != null && !convenio.getNome_fantasia().equals("")) {

			EntityManager manager = HibernateUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.persist(convenio);
			manager.getTransaction().commit();
		    manager.close();

			FacesMessage faceMessage = new FacesMessage(
					"Registro inserido com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);

		} else {
			FacesMessage faceMessage = new FacesMessage(
					"Covenio deve ter nome");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		}

	}
	
	
	public void alterar(Convenio convenio) {

		if (convenio.getNome_fantasia() != null && !convenio.getNome_fantasia().equals("")) {

			EntityManager manager = HibernateUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.merge(convenio);
			manager.getTransaction().commit();
			this.convenio = new Convenio();
			manager.close();

			FacesMessage faceMessage = new FacesMessage(
					"Registro inserido com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);

		} else {
			FacesMessage faceMessage = new FacesMessage(
					"Covenio deve ter nome");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		}

	}
	
	
	public void remove(Convenio convenio) {
		EntityManager manager = HibernateUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(convenio));
		manager.getTransaction().commit();
		manager.close();

		FacesMessage faceMessage = new FacesMessage(
				"Registro removido com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, faceMessage);

	}

	public void consultar() {
		this.convenios = buscarPorCodigo(convenio);
	}
	
	public void consultarTodos() {
		this.convenios = buscarTodos(convenio);
	}

	public List<Convenio> buscarPorCodigo(Convenio convenio) {
		EntityManager manager = HibernateUtil.getEntityManager();
		TypedQuery<Convenio> query = manager.createQuery(
				"select p from Convenio p where p.id = :id", Convenio.class);
		query.setParameter("id", convenio.getId());
		List<Convenio> resultados = query.getResultList();
		return resultados;
	}
	
	
	public List<Convenio> buscarTodos(Convenio convenio) {
		EntityManager manager = HibernateUtil.getEntityManager();
		TypedQuery<Convenio> query = manager.createQuery(
				"select p from Convenio p", Convenio.class);
		query.setParameter("id", convenio.getId());
		List<Convenio> resultados = query.getResultList();
		return resultados;
	}

	public List<Convenio> getConvenios() {
		if (this.convenios == null) {
			EntityManager manager = HibernateUtil.getEntityManager();
			Query query = manager.createQuery("select p from Convenio p", Convenio.class);
			this.convenios = query.getResultList();
			manager.close();
		}

		return convenios;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

}
