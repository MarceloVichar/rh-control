package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Student;
import util.HibernateUtil;

public class StudentDao {

	public void saveStudent(Student student) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			//start the transaction
			transaction = session.beginTransaction();
			//save the studendt object
			session.save(student);
			//commit the transaction
			transaction.commit();
			
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("abriu transaction mas falhou");
			}
		}
	}
public void updateStudent(Student student) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			//start the transaction
			transaction = session.beginTransaction();
			//save the studendt object
			session.saveOrUpdate(student);
			//commit the transaction
			transaction.commit();
			
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("abriu transaction mas falhou");
			}
		}
	}
public Student getStudentById(long id) {
	
	Transaction transaction = null;
	Student student = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//start the transaction
		transaction = session.beginTransaction();
		//get the studendt object
		student = session.get(Student.class, id);
		//commit the transaction
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("abriu transaction mas falhou");
		}
	}
	return student;
}

public List<Student> listAllStudents() {
	
	Transaction transaction = null;
	List<Student> students = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//start the transaction
		transaction = session.beginTransaction();
		//get the studendts 
		students = session.createQuery("from student").list();
		//commit the transaction
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("abriu transaction mas falhou");
		}
	}
	
	return students;
}

public void deleteStudent(long id) {
	
	Transaction transaction = null;
	Student student = null;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//start the transaction
		transaction = session.beginTransaction();
		//delete the studendt object
		student = session.get(Student.class, id);
		session.delete(student);
		
		//commit the transaction
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("abriu transaction mas falhou");
		}
	}
}
	
	
}
