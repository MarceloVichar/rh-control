package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "extraBenefit")

public class ExtraBenefit extends Benefit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "extra_benefit_id")
	private long id;
	
	@ManyToMany(mappedBy = "extraBenefits")
    private Set<Employee> employees = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public void adjustValue() {
		this.value *= 1.05;
		System.out.println("Valor ajustado em 5%");
	}
}