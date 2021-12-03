package com.example.servicecompany.repository;

import com.example.servicecompany.domain.Bateau;
import com.example.servicecompany.domain.PublicationSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Depot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicationSubCategoryRepository extends JpaRepository<PublicationSubCategory, Long> {

}
