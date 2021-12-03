package com.example.servicecompany.service;


import com.example.servicecompany.domain.PublicationImage;
import com.example.servicecompany.repository.PublicationImageRepository;
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
public class PublicationImageService {

    private final Logger log = LoggerFactory.getLogger(PublicationImageService.class);

    private final PublicationImageRepository publicationImageRepository;

    public PublicationImageService(PublicationImageRepository publicationImageRepository) {

        this.publicationImageRepository = publicationImageRepository;
    }

    /**
     * Save a publication.
     */
    public PublicationImage save(PublicationImage publicationImage) {
        log.debug("Request to save PublicationImage : {}", publicationImage);
        return publicationImageRepository.save(publicationImage);
    }

    /**
     * Get all the publicationImages.
     */
    @Transactional(readOnly = true)
    public Page<PublicationImage> findAll(Pageable pageable) {
        log.debug("Request to get all PublicationImages");
        return publicationImageRepository.findAll(pageable);
    }


    /**
     * Get one publicationImage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PublicationImage> findOne(UUID id) {
        log.debug("Request to get PublicationImage : {}", id);
        return publicationImageRepository.findById(id);
    }

    /**
     * Delete the publicationImage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete PublicationImage : {}", id);
        publicationImageRepository.deleteById(id);
    }



}
