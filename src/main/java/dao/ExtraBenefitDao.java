package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.ExtraBenefit;
import util.HibernateUtil;

public class ExtraBenefitDao {

	public void saveExtraBenefit(ExtraBenefit extraBenefit) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(extraBenefit);
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível cadastrar o benefício");
			}
		}
	}
public void updateExtraBenefit(ExtraBenefit extraBenefit) {
		
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(extraBenefit);
			transaction.commit();
			
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			    System.out.println("Não foi possível atualizar o benefício");
			}
		}
	}
public ExtraBenefit getExtraBenefitById(long id) {
	
	Transaction transaction = null;
	ExtraBenefit extraBenefit = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		extraBenefit = session.get(ExtraBenefit.class, id);
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível buscar o benefício com id " + id);
		}
	}
	return extraBenefit;
}

public List<ExtraBenefit> listAllExtraBenefit() {
	
	Transaction transaction = null;
	List<ExtraBenefit> extraBenefit = null;
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		extraBenefit = session.createQuery("from extraBenefit").list();
		transaction.commit();
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Não foi possível listar os benefícios");
		}
	}
	
	return extraBenefit;
}

public void deleteBasicBenefit(long id) {
	
	Transaction transaction = null;
	ExtraBenefit extraBenefit = null;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		extraBenefit = session.get(ExtraBenefit.class, id);
		session.delete(extraBenefit);
		
		transaction.commit();
		
		
	} catch (Exception e) {
		if(transaction != null) {
			transaction.rollback();
		    System.out.println("Erro ao remover benefício");
		}
	}
}
	
	
}
