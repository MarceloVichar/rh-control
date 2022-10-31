package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.BasicBenefit;
import util.HibernateUtil;

public class BasicBenefitDao {

	public void saveBasicBenefit(BasicBenefit basicBenefit) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(basicBenefit);
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível cadastrar o benefício");
			}
		}
	}
public void updateBasicBenefit(BasicBenefit basicBenefit) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(basicBenefit);
			transaction.commit();
			
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível atualizar o benefício");
			}
		}
	}
public BasicBenefit getBasicBenefitById(long id) {
	
	Transaction transaction = null;
	BasicBenefit basicBenefit = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		basicBenefit = session.get(BasicBenefit.class, id);
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível buscar o benefício com id " + id);
		}
	}
	return basicBenefit;
}

public List<BasicBenefit> listAllBasicBenefit() {
	
	Transaction transaction = null;
	List<BasicBenefit> basicBenefit = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		basicBenefit = session.createQuery("from basicBenefit").list();
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível listar os benefícios");
		}
	}
	
	return basicBenefit;
}

public void deleteBasicBenefit(long id) {
	
	Transaction transaction = null;
	BasicBenefit basicBenefit = null;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		basicBenefit = session.get(BasicBenefit.class, id);
		session.delete(basicBenefit);
		
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Erro ao remover benefício");
		}
	}
}
	
	
}
