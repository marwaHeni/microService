package com.example.servicecompany.repository;

import com.example.servicecompany.domain.Sauvee;
import com.example.servicecompany.domain.Sauveteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data  repository for the Depot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SauveteurRepository extends JpaRepository<Sauveteur, UUID> {

}
