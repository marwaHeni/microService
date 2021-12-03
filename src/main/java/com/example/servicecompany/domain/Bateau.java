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
@ApiModel(description = "Bateau entity.\n@author The Moo3in team.")
@Entity
@Table(name = "bateau")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bateau implements Serializable {

    private static final long serialVersionUID = 1L;


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID id;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;


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


    public String getName() {
        return name;
    }

    public Bateau name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImage() {
        return image;
    }

    public Bateau image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public Bateau description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Publication getPublication() {
        return publication;
    }

    public Bateau publication(Publication publication) {
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
        if (!(o instanceof Bateau)) {
            return false;
        }
        return id != null && id.equals(((Bateau) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "bateau{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
