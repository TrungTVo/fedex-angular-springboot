package com.trung.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table(name = "user")
public class User {
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "confirmPassword")
	private String confirmPassword;
	
	@ManyToMany(targetEntity=Role.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="User_Role", 
		joinColumns= {@JoinColumn(name="email")}, 
		inverseJoinColumns={@JoinColumn(name="role")}
	)
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Consignment.class)
	private Set<Consignment> consignments = new HashSet<>();
		
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
	}
	
	public void addConsignment(Consignment consignment) {
		consignments.add(consignment);
	}
	
	public void removeConsignment(Consignment consignment) {
		consignments.remove(consignment);
	}
	
	public User () {
		
	}
	
	public User(String firstName, String lastName, String email, String password, String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Consignment> getConsignments() {
		return consignments;
	}

	public void setConsignments(Set<Consignment> consignments) {
		this.consignments = consignments;
	}
	
	
}
