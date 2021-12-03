package com.example.servicecompany.service;


import com.example.servicecompany.domain.Publication;
import com.example.servicecompany.repository.PublicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation
 */
@Service
@Transactional
public class PublicationService {

    private final Logger log = LoggerFactory.getLogger(PublicationService.class);

    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {

        this.publicationRepository = publicationRepository;
    }

    /**
     * Save a publication.
     */
    public Publication save(Publication publication) {
        log.debug("Request to save Publication : {}", publication);
        return publicationRepository.save(publication);
    }

    /**
     * Get all the publications.
     */
    @Transactional(readOnly = true)
    public Page<Publication> findAll(Pageable pageable) {
        log.debug("Request to get all Publications");
        return publicationRepository.findAll(pageable);
    }


    /**
     * Get one publication by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Publication> findOne(UUID id) {
        log.debug("Request to get Publication : {}", id);
        return publicationRepository.findById(id);
    }

    /**
     * Delete the publication by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Publication : {}", id);
        publicationRepository.deleteById(id);
    }



}
