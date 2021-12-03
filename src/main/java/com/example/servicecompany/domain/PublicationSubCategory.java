package com.example.servicecompany.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * ItemSubCategory entity.\n@author The Moo3in team.
 */
@ApiModel(description = "PublicationSubCategory entity.\n@author The Moo3in team.")
@Entity
@Table(name = "publication_sub_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicationSubCategory implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 50)
    @Column(name = "description", length = 50)
    private String description;


    @ManyToOne
    @JsonIgnoreProperties(value = "articleSubCategories", allowSetters = true)
    private PublicationCategory publicationCategory;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicationSubCategory name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PublicationCategory getPublicationCategory() {
        return publicationCategory;
    }

    public PublicationSubCategory publicationCategory(PublicationCategory publicationCategory) {
        this.publicationCategory = publicationCategory;
        return this;
    }
    public void setPublicationCategory(PublicationCategory publicationCategory) {
        this.publicationCategory = publicationCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PublicationSubCategory)) {
            return false;
        }
        return id != null && id.equals(((PublicationSubCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PublicationSubCategory{" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}
