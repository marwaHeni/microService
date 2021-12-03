package com.example.servicecompany.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * Tiers entity.\n@author The Moo3in team.
 */
@ApiModel(description = "Sauvee entity.\n@author The Moo3in team.")
@Entity
@Table(name = "sauvee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sauvee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(min = 8)
    @Column(name = "phone")
    private String phone;

    @Column(name = "image")
    private String image;

    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email_1")
    private String email;

    @Column(name = "description")
    private String description;


    @Column(name = "address")
    private String address;

    @ManyToOne
    @JsonIgnoreProperties(value = "publicationImages", allowSetters = true)
    private Publication publication;


    // jhipster-needle-entity-add-field - JHipster will add fields here
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getPhone() {
        return phone;
    }

    public Sauvee phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public Sauvee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Sauvee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getImage() {
        return image;
    }

    public Sauvee image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public Sauvee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDescription() {
        return description;
    }

    public Sauvee description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public Sauvee address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Publication getPublication() {
        return publication;
    }

    public Sauvee publication(Publication publication) {
        this.publication = publication;
        return this;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sauvee)) {
            return false;
        }
        return id != null && id.equals(((Sauvee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sauvee{" +
                "id=" + getId() +
                ", lastName='" + getLastName() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", image='" + getImage() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }
}
