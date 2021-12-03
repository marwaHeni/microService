package com.example.servicecompany.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * ImageItem entity.\n@author The Moo3in team.
 */
@ApiModel(description = "PublicationVideo entity.\n@author The Moo3in team.")
@Entity
@Table(name = "publication_video")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicationVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    
    //base64
    @Transient
    private String avatar;


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

    public PublicationVideo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Publication getPublication() {
        return publication;
    }

    public PublicationVideo publication(Publication publication) {
        this.publication = publication;
        return this;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PublicationVideo)) {
            return false;
        }
        return id != null && id.equals(((PublicationVideo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PublicationVideo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            "}";
    }
}
