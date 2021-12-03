package com.example.servicecompany.service;


import com.example.servicecompany.domain.PublicationSubCategory;
import com.example.servicecompany.repository.PublicationSubCategoryRepository;
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
public class PublicationSubCategoryService {

    private final Logger log = LoggerFactory.getLogger(PublicationSubCategoryService.class);

    private final PublicationSubCategoryRepository publicationSubCategoryRepository;

    public PublicationSubCategoryService(PublicationSubCategoryRepository publicationSubCategoryRepository) {

        this.publicationSubCategoryRepository = publicationSubCategoryRepository;
    }

    /**
     * Save a publicationSubCategory.
     */
    public PublicationSubCategory save(PublicationSubCategory publicationSubCategory) {
        log.debug("Request to save PublicationSubCategory : {}", publicationSubCategory);
        return publicationSubCategoryRepository.save(publicationSubCategory);
    }

    /**
     * Get all the publicationSubCategories.
     */
    @Transactional(readOnly = true)
    public Page<PublicationSubCategory> findAll(Pageable pageable) {
        log.debug("Request to get all PublicationSubCategories");
        return publicationSubCategoryRepository.findAll(pageable);
    }


    /**
     * Get one publicationSubCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PublicationSubCategory> findOne(Long id) {
        log.debug("Request to get PublicationSubCategory : {}", id);
        return publicationSubCategoryRepository.findById(id);
    }

    /**
     * Delete the publicationSubCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PublicationSubCategory : {}", id);
        publicationSubCategoryRepository.deleteById(id);
    }



}
