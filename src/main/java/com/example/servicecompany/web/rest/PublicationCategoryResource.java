package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.PublicationCategory;
import com.example.servicecompany.security.AuthoritiesConstants;
import com.example.servicecompany.service.PublicationCategoryService;
import com.example.servicecompany.utility.HeaderUtil;
import com.example.servicecompany.utility.PaginationUtil;
import com.example.servicecompany.utility.ResponseUtil;
import com.example.servicecompany.web.rest.errors.BadRequestAlertException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
public class PublicationCategoryResource {

    private final Logger log = LoggerFactory.getLogger(PublicationCategory.class);

    private static final String ENTITY_NAME = "publicationCategory";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublicationCategoryService publicationCategoryService;

    public PublicationCategoryResource(PublicationCategoryService publicationCategoryService) {

        this.publicationCategoryService = publicationCategoryService;
    }

    /**
     * {@code POST  /publication-Categories} : Create a new publicationCategory.
     */
    @PostMapping("/publication-categories")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.Admin + "\")")
    public ResponseEntity<PublicationCategory> createPublicationCategory(@Valid @RequestBody PublicationCategory publicationCategory) throws URISyntaxException {
        log.debug("REST request to save BaseTva : {}", publicationCategory);
        if (publicationCategory.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PublicationCategory result = publicationCategoryService.save(publicationCategory);
        return ResponseEntity.created(new URI("/api/publication-Categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /publication-Categories} : Updates an existing publicationCategory.
     */
    @PutMapping("/publication-categories")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.Admin + "\")")
    public ResponseEntity<PublicationCategory> updatePublicationCategory(@Valid @RequestBody PublicationCategory publicationCategory) throws URISyntaxException, IOException {

        log.debug("REST request to update PublicationCategory : {}", publicationCategory);
        if (publicationCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PublicationCategory result = publicationCategoryService.save(publicationCategory);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, publicationCategory.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /} : get all the PublicationCategories.
     */
    @GetMapping("/publication-categories")
    public ResponseEntity<List<PublicationCategory>> getAllPublicationCategories(Pageable pageable) {
        log.debug("REST request to get a page of PublicationCategories");
        Page<PublicationCategory> page = publicationCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" publicationCategory.
     */
    @GetMapping("/publication-categories/{id}")
    public ResponseEntity<PublicationCategory> getPublicationCategory(@PathVariable Long id) {
        log.debug("REST request to get PublicationCategory : {}", id);
        Optional<PublicationCategory> publicationCategory = publicationCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publicationCategory);
    }

    /**
     * {@code DELETE  /bank-enums/:id} : delete the "id" publicationCategory.
     */
    @DeleteMapping("/publication-categories/{id}")
    public ResponseEntity<Void> deletePublicationCategory(@PathVariable Long id) {
        log.debug("REST request to delete PublicationCategory : {}", id);
        publicationCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
