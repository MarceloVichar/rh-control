package model;

import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "employee")
public class Employee implements BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Departament departament;
	
	@Enumerated(EnumType.STRING)
	private RolesEnum role;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Employee_BasicBenefit", 
        joinColumns = { @JoinColumn(name = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "basic_benefit_id") }
    )
    Set<BasicBenefit> basicBenefits = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Employee_ExtraBenefit", 
        joinColumns = { @JoinColumn(name = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "extra_benefit_id") }
    )
    Set<ExtraBenefit> extraBenefits = new HashSet<>();
	
	public long getId() {
		return employeeId;
	}
	public void setId(long id) {
		this.employeeId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public RolesEnum getRole() {
		return role;
	}
	public void setRole(RolesEnum role) {
		this.role = role;
	}
}
