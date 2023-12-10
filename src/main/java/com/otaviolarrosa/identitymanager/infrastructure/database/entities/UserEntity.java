package com.otaviolarrosa.identitymanager.infrastructure.database.entities;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", schema = "public")
public class UserEntity implements Serializable {
    
	private static final long serialVersionUID = -1152779434213289790L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;	
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UUID userCode;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public UUID getUserCode() {
		return userCode;
	}

	public void setUserCode(UUID userCode) {
		this.userCode = userCode;
	}
}
