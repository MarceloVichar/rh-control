package model;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import util.MyException;

@MappedSuperclass
public abstract class Benefit implements BaseEntity, BenefitInterface {

	@Column(name = "name")
	private String name;
	@Column(name = "value")
	protected double value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setVaue(double value) {
		this.value = value;
	}

	@Override
	public void adjustValue() {
	}

	@Override
	public void zeroValue() {
		this.value = 0;
	}

	@Override
	public String getFormattedValue() {
		DecimalFormat df = new DecimalFormat("#,###.00");
		String formattedSalary;
		formattedSalary = "R$" + df.format(this.value);

		return formattedSalary;
	}
}
