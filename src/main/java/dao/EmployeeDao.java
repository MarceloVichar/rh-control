package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Employee;
import util.HibernateUtil;

public class EmployeeDao {

	public void saveEmployee(Employee employee) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível cadastrar o empregado");
			}
		}
	}
public void updateEmployee(Employee employee) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(employee);
			transaction.commit();
			
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível atualizar o empregado");
			}
		}
	}
public Employee getEmployeeById(long id) {
	
	Transaction transaction = null;
	Employee employee = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		employee = session.get(Employee.class, id);
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível buscar o usuário com id " + id);
		}
	}
	return employee;
}

public List<Employee> listAllEmployees() {
	
	Transaction transaction = null;
	List<Employee> employees = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		employees = session.createQuery("from employee").list();
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível listar os empregados");
		}
	}
	
	return employees;
}

public void deleteEmployee(long id) {
	
	Transaction transaction = null;
	Employee employee = null;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//start the transaction
		transaction = session.beginTransaction();
		//delete the studendt object
		employee = session.get(Employee.class, id);
		session.delete(employee);
		
		//commit the transaction
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Erro ao remover empregado");
		}
	}
}
	
	
}
