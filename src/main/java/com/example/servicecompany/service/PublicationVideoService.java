package com.example.servicecompany.service;


import com.example.servicecompany.domain.PublicationVideo;
import com.example.servicecompany.repository.PublicationVideoRepository;
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
public class PublicationVideoService {

    private final Logger log = LoggerFactory.getLogger(PublicationVideoService.class);

    private final PublicationVideoRepository publicationVideoRepository;

    public PublicationVideoService(PublicationVideoRepository publicationVideoRepository) {

        this.publicationVideoRepository = publicationVideoRepository;
    }

    /**
     * Save a publication.
     */
    public PublicationVideo save(PublicationVideo publicationVideo) {
        log.debug("Request to save PublicationVideo : {}", publicationVideo);
        return publicationVideoRepository.save(publicationVideo);
    }

    /**
     * Get all the publicationVideos.
     */
    @Transactional(readOnly = true)
    public Page<PublicationVideo> findAll(Pageable pageable) {
        log.debug("Request to get all PublicationVideos");
        return publicationVideoRepository.findAll(pageable);
    }


    /**
     * Get one publicationVideo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PublicationVideo> findOne(UUID id) {
        log.debug("Request to get PublicationVideo : {}", id);
        return publicationVideoRepository.findById(id);
    }

    /**
     * Delete the publicationVideo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete PublicationVideo : {}", id);
        publicationVideoRepository.deleteById(id);
    }



}
