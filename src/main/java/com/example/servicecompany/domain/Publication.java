package com.example.servicecompany.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ArticleTag entity.\n@author The Moo3in team.
 */
@ApiModel(description = "Publication entity.\n@author The Moo3in team.")
@Entity
@Table(name = "publication")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_validate")
    private Boolean isValidate;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Column(name = "date", nullable = false)
    private Instant date;

    @JsonManagedReference
    @OneToMany(mappedBy = "publication", orphanRemoval = true)
    @JsonIgnoreProperties(value = "publications", allowSetters = true)
    private Set<PublicationImage> publicationImages = new HashSet<PublicationImage>();


    @OneToMany(mappedBy = "publication", orphanRemoval = true)
    @JsonIgnoreProperties(value = "publications", allowSetters = true)
    private Set<PublicationVideo> publicationVideos = new HashSet<PublicationVideo>();



    @OneToMany(mappedBy = "publication", orphanRemoval = true)
    @JsonIgnoreProperties(value = "publications", allowSetters = true)
    private Set<Sauvee> sauvees = new HashSet<Sauvee>();


    @OneToMany(mappedBy = "publication", orphanRemoval = true)
    @JsonIgnoreProperties(value = "publications", allowSetters = true)
    private Set<Sauveteur> sauveteurs = new HashSet<Sauveteur>();


    @OneToMany(mappedBy = "publication", orphanRemoval = true)
    @JsonIgnoreProperties(value = "publications", allowSetters = true)
    private Set<Bateau> bateaux = new HashSet<Bateau>();

    @ManyToOne
    @JsonIgnoreProperties(value = "articles", allowSetters = true)
    private PublicationSubCategory publicationSubCategory;


    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Publication description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public UUID getUserId() {
        return userId;
    }

    public Publication userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Instant getDate() {
        return date;
    }

    public Publication date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Boolean getIsValidate() {
        return isValidate;
    }

    public Publication isValidate(Boolean isValidate) {
        this.isValidate = isValidate;
        return this;
    }

    public void setIsValidate(Boolean isValidate) {
        this.isValidate = isValidate;
    }

    public Set<PublicationImage> getPublicationImage() {
        return publicationImages;
    }

    public Publication publicationImages(Set<PublicationImage> publicationImages) {
        this.publicationImages = publicationImages;
        return this;
    }

    public Publication addPublicationImage(PublicationImage publicationImage) {
        this.publicationImages.add(publicationImage);
        publicationImage.setPublication(this);
        return this;
    }

    public Publication removePublicationImage(PublicationImage publicationImage) {
        this.publicationImages.remove(publicationImage);
        publicationImage.setPublication(null);
        return this;
    }

    public void setPublicationImages(Set<PublicationImage> publicationImages) {

        //   this.publicationImages = publicationImages;
        this.publicationImages.addAll(publicationImages);
    }


    public Set<PublicationVideo> getPublicationVideos() {
        return publicationVideos;
    }

    public Publication publicationVideos(Set<PublicationVideo> publicationVideos) {
        this.publicationVideos = publicationVideos;
        return this;
    }

    public Publication addPublicationVideo(PublicationVideo publicationVideo) {
        this.publicationVideos.add(publicationVideo);
        publicationVideo.setPublication(this);
        return this;
    }

    public Publication removePublicationVideo(PublicationVideo publicationVideo) {
        this.publicationVideos.remove(publicationVideo);
        publicationVideo.setPublication(null);
        return this;
    }

    public void setPublicationVideos(Set<PublicationVideo> publicationVideos) {

        //   this.publicationImages = publicationImages;
        this.publicationVideos.addAll(publicationVideos);
    }


    public PublicationSubCategory getPublicationSubCategory() {
        return publicationSubCategory;
    }

    public Publication publicationSubCategory(PublicationSubCategory publicationSubCategory) {
        this.publicationSubCategory = publicationSubCategory;
        return this;
    }
    public void setPublicationSubCategory(PublicationSubCategory publicationSubCategory) {
        this.publicationSubCategory = publicationSubCategory;
    }




    public Set<Sauvee> getSauvees() {
        return sauvees;
    }

    public Publication sauvees(Set<Sauvee> sauvees) {
        this.sauvees = sauvees;
        return this;
    }

    public Publication addSauvee(Sauvee sauvee) {
        this.sauvees.add(sauvee);
        sauvee.setPublication(this);
        return this;
    }

    public Publication removeSauvee(Sauvee sauvee) {
        this.sauvees.remove(sauvee);
        sauvee.setPublication(null);
        return this;
    }

    public void setSauvees(Set<Sauvee> sauvees) {

        //   this.publicationImages = publicationImages;
        this.sauvees.addAll(sauvees);
    }




    public Set<Sauveteur> getSauveteurs() {
        return sauveteurs;
    }

    public Publication sauveteurs(Set<Sauveteur> sauveteurs) {
        this.sauveteurs = sauveteurs;
        return this;
    }

    public Publication addSauveteur(Sauveteur sauveteur) {
        this.sauveteurs.add(sauveteur);
        sauveteur.setPublication(this);
        return this;
    }

    public Publication removeSauveteur(Sauveteur sauveteur) {
        this.sauveteurs.remove(sauveteur);
        sauveteur.setPublication(null);
        return this;
    }

    public void setSauveteurs(Set<Sauveteur> sauveteurs) {

        //   this.publicationImages = publicationImages;
        this.sauveteurs.addAll(sauveteurs);
    }



    public Set<Bateau> getBateaux() {
        return bateaux;
    }

    public Publication bateaux(Set<Bateau> bateaux) {
        this.bateaux = bateaux;
        return this;
    }

    public Publication addBateau(Bateau bateau) {
        this.bateaux.add(bateau);
        bateau.setPublication(this);
        return this;
    }

    public Publication removeBateau(Bateau bateau) {
        this.bateaux.remove(bateau);
        bateau.setPublication(null);
        return this;
    }

    public void setBateaux(Set<Bateau> bateaux) {

        //   this.publicationImages = publicationImages;
        this.bateaux.addAll(bateaux);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Publication)) {
            return false;
        }
        return id != null && id.equals(((Publication) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Publication{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }

}
