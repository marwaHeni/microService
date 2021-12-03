package com.example.servicecompany.repository;

import com.example.servicecompany.domain.PublicationVideo;
import com.example.servicecompany.domain.Sauvee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data  repository for the Depot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SauveeRepository extends JpaRepository<Sauvee, UUID> {

}
