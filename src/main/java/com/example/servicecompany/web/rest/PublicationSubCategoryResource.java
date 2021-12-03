package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.PublicationSubCategory;
import com.example.servicecompany.service.PublicationSubCategoryService;
import com.example.servicecompany.utility.HeaderUtil;
import com.example.servicecompany.utility.PaginationUtil;
import com.example.servicecompany.utility.ResponseUtil;
import com.example.servicecompany.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller
 */
@RestController
@RequestMapping("/api")
public class PublicationSubCategoryResource {

    private final Logger log = LoggerFactory.getLogger(PublicationSubCategory.class);

    private static final String ENTITY_NAME = "publicationSubCategory";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublicationSubCategoryService publicationSubCategoryService;

    public PublicationSubCategoryResource(PublicationSubCategoryService publicationSubCategoryService) {

        this.publicationSubCategoryService = publicationSubCategoryService;
    }

    /**
     * {@code POST  /publication-Categories} : Create a new publicationSubCategory.
     */
    @PostMapping("/publication-sub-categories")
    public ResponseEntity<PublicationSubCategory> createPublicationSubCategory(@Valid @RequestBody PublicationSubCategory publicationSubCategory) throws URISyntaxException {
        log.debug("REST request to save BaseTva : {}", publicationSubCategory);
        if (publicationSubCategory.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PublicationSubCategory result = publicationSubCategoryService.save(publicationSubCategory);
        return ResponseEntity.created(new URI("/api/publication-Categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /publication-Categories} : Updates an existing publicationSubCategory.
     */
    @PutMapping("/publication-sub-categories")
    public ResponseEntity<PublicationSubCategory> updatePublicationSubCategory(@Valid @RequestBody PublicationSubCategory publicationSubCategory) throws URISyntaxException, IOException {

        log.debug("REST request to update PublicationSubCategory : {}", publicationSubCategory);
        if (publicationSubCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PublicationSubCategory result = publicationSubCategoryService.save(publicationSubCategory);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, publicationSubCategory.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /} : get all the PublicationSubCategories.
     */
    @GetMapping("/publication-sub-categories")
    public ResponseEntity<List<PublicationSubCategory>> getAllPublicationSubCategories(Pageable pageable) {
        log.debug("REST request to get a page of PublicationSubCategories");
        Page<PublicationSubCategory> page = publicationSubCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" publicationSubCategory.
     */
    @GetMapping("/publication-sub-categories/{id}")
    public ResponseEntity<PublicationSubCategory> getPublicationSubCategory(@PathVariable Long id) {
        log.debug("REST request to get PublicationSubCategory : {}", id);
        Optional<PublicationSubCategory> publicationSubCategory = publicationSubCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publicationSubCategory);
    }

    /**
     * {@code DELETE  /bank-enums/:id} : delete the "id" publicationSubCategory.
     */
    @DeleteMapping("/publication-sub-categories/{id}")
    public ResponseEntity<Void> deletePublicationSubCategory(@PathVariable Long id) {
        log.debug("REST request to delete PublicationSubCategory : {}", id);
        publicationSubCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
