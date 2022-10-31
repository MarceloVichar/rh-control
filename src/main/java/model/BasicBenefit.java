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
@Table(name = "basicBenefit")

public class BasicBenefit extends Benefit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "basic_benefit_id")
	private long id;
	
	@ManyToMany(mappedBy = "basicBenefits")
    private Set<Employee> employees = new HashSet<>();
	
	@Override
	public void adjustValue() {
		this.value *= 1.07;
		System.out.println("Valor ajustado em 7%");
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
