package com.digitalbooks.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    
    @NotNull(message = "shouldnt not eb null")
    private String userName;
    
    @NotNull
    @Column(unique = true)
    private String userEmailId;
    
    @NotNull
    private String password;
    
    @NotNull
    private String role;
	/*
	 * @JsonProperty("roles")
	 * 
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "user_roles",joinColumns = {@JoinColumn(name =
	 * "user_Id")},inverseJoinColumns = {@JoinColumn(name = "role_Id")} ) private
	 * Set<UserRoleDetails> roles = new HashSet();
	 */
}
