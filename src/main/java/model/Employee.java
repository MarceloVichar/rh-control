package model;

import javax.persistence.Table;

import java.text.DecimalFormat;
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
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "salary", nullable = false)
	private double salary;
	
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
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public RolesEnum getRole() {
		return role;
	}

	public void setRole(RolesEnum role) {
		this.role = role;
	}

	public Set<BasicBenefit> getBasicBenefits() {
		return basicBenefits;
	}

	public void setBasicBenefits(Set<BasicBenefit> basicBenefits) {
		this.basicBenefits = basicBenefits;
	}

	public Set<ExtraBenefit> getExtraBenefits() {
		return extraBenefits;
	}

	public void setExtraBenefits(Set<ExtraBenefit> extraBenefits) {
		this.extraBenefits = extraBenefits;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getFormattedSalary() {
		DecimalFormat df = new DecimalFormat("#,###.00");
		String formattedSalary;
		
		try {
			formattedSalary = "R$" + df.format(this.salary);
		}
		catch (NullPointerException e) {
			System.out.println("O salário não pode ser formatado, pois não possui um valor válido");
			formattedSalary = "Nada consta";
		}
		
		return formattedSalary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", phone=" + phone
				+ ", salary=" + salary + ", departament=" + departament + ", role=" + role + ", basicBenefits="
				+ basicBenefits + ", extraBenefits=" + extraBenefits + "]";
	}
	
}
