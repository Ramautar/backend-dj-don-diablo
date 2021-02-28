package nl.novi.backenddjdondiablo.payload.response;

import nl.novi.backenddjdondiablo.domain.Role;

import java.util.Set;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String facebook;
    private String instagram;
    private String username;
    private String email;
    private Set<Role> roles;

    public UserResponse(Long id, String firstName, String lastName, String country, String facebook, String instagram, String username, String email, Set<Role> roles) {
        this.id= id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.facebook = facebook;
        this.instagram = instagram;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
