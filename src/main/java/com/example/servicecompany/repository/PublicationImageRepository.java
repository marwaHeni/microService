package com.example.servicecompany.repository;

import com.example.servicecompany.domain.Bateau;
import com.example.servicecompany.domain.PublicationImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data  repository for the Depot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicationImageRepository extends JpaRepository<PublicationImage, UUID> {

}
