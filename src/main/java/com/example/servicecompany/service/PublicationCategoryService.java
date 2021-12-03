package com.example.servicecompany.service;


import com.example.servicecompany.domain.PublicationCategory;
import com.example.servicecompany.repository.PublicationCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation
 */
@Service
@Transactional
public class PublicationCategoryService {

    private final Logger log = LoggerFactory.getLogger(PublicationCategoryService.class);

    private final PublicationCategoryRepository publicationCategoryRepository;

    public PublicationCategoryService(PublicationCategoryRepository publicationCategoryRepository) {

        this.publicationCategoryRepository = publicationCategoryRepository;
    }

    /**
     * Save a publicationCategory.
     */
    public PublicationCategory save(PublicationCategory publicationCategory) {
        log.debug("Request to save PublicationCategory : {}", publicationCategory);
        return publicationCategoryRepository.save(publicationCategory);
    }

    /**
     * Get all the publicationCategories.
     */
    @Transactional(readOnly = true)
    public Page<PublicationCategory> findAll(Pageable pageable) {
        log.debug("Request to get all PublicationCategories");
        return publicationCategoryRepository.findAll(pageable);
    }


    /**
     * Get one publicationCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PublicationCategory> findOne(Long id) {
        log.debug("Request to get PublicationCategory : {}", id);
        return publicationCategoryRepository.findById(id);
    }

    /**
     * Delete the publicationCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PublicationCategory : {}", id);
        publicationCategoryRepository.deleteById(id);
    }



}
