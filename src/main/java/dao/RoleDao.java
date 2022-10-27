package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Role;
import util.HibernateUtil;

public class RoleDao {

	public void saveRole(Role role) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(role);
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível cadastrar o nível de experiência");
			}
		}
	}
public void updateRole(Role role) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(role);
			transaction.commit();
			
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível atualizar o nível de experiência");
			}
		}
	}
public Role getRoleById(long id) {
	
	Transaction transaction = null;
	Role role = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		role = session.get(Role.class, id);
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível buscar o nível de experiência com id " + id);
		}
	}
	return role;
}

public List<Role> listAllRoles() {
	
	Transaction transaction = null;
	List<Role> roles = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		roles = session.createQuery("from role").list();
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível listar os níveis de experiência");
		}
	}
	
	return roles;
}

public void deleteRole(long id) {
	
	Transaction transaction = null;
	Role role = null;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//start the transaction
		transaction = session.beginTransaction();
		//delete the studendt object
		role = session.get(Role.class, id);
		session.delete(role);
		
		//commit the transaction
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Erro ao remover nível de experiência");
		}
	}
}
	
	
}
