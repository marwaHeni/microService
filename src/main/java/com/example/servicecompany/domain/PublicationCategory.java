package com.example.servicecompany.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ItemCategory entity.\n@author The Moo3in team.
 */
@ApiModel(description = "PublicationCategory entity.\n@author The Moo3in team.")
@Entity
@Table(name = "publication_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicationCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 50)
    @Column(name = "description", length = 50)
    private String description;


    @OneToMany(mappedBy = "publicationCategory")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PublicationSubCategory> publicationSubCategories = new HashSet<>();

//    @Column(name = "is_deleted")
//    private Boolean isDeleted;

//    @OneToMany(orphanRemoval = true, mappedBy= "itemCategory")
//    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//    private Set<ItemSubCategory> itemSubCategories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PublicationCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<PublicationSubCategory> getArticleSubCategories() {
        return publicationSubCategories;
    }

    public PublicationCategory publicationSubCategories(Set<PublicationSubCategory> publicationSubCategories) {
        this.publicationSubCategories = publicationSubCategories;
        return this;
    }

    public PublicationCategory addPublicationSubCategory(PublicationSubCategory publicationSubCategory) {
        this.publicationSubCategories.add(publicationSubCategory);
        publicationSubCategory.setPublicationCategory(this);
        return this;
    }

    public PublicationCategory removeArticleSubCategory(PublicationSubCategory publicationSubCategory) {
        this.publicationSubCategories.remove(publicationSubCategory);
        publicationSubCategory.setPublicationCategory(null);
        return this;
    }
    public void setArticleSubCategories(Set<PublicationSubCategory> publicationSubCategories) {
        this.publicationSubCategories = publicationSubCategories;
    }


//    @PrePersist
//    public void defaultIsDeleted() {
//        if(isDeleted == null) {
//            isDeleted = Boolean.FALSE;
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PublicationCategory)) {
            return false;
        }
        return id != null && id.equals(((PublicationCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PublicationCategory{" +
            "  name=" + getName() +
            ", description='" + getDescription() + "'" +
            "}";
    }




}
