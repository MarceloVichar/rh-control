package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Departament;
import util.HibernateUtil;

public class DepartamentDao {

	public void saveDepartament(Departament departament) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(departament);
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível cadastrar o departamento");
			}
		}
	}
public void updateDepartament(Departament departament) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(departament);
			transaction.commit();
			
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível atualizar o departamento");
			}
		}
	}
public Departament getDepartamentById(long id) {
	
	Transaction transaction = null;
	Departament departament = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		departament = session.get(Departament.class, id);
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível buscar o departamento com o id " + id);
		}
	}
	return departament;
}

public List<Departament> listDepartaments() {
	
	Transaction transaction = null;
	List<Departament> departaments = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		departaments = session.createQuery("from departament").list();
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível listar os departamentos");
		}
	}
	
	return departaments;
}

public void deleteDepartament(long id) {
	
	Transaction transaction = null;
	Departament departament = null;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//start the transaction
		transaction = session.beginTransaction();
		//delete the studendt object
		departament = session.get(Departament.class, id);
		session.delete(departament);
		
		//commit the transaction
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Erro ao remover departamento");
		}
	}
}
	
	
}
