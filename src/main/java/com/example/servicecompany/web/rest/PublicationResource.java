package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.Publication;
import com.example.servicecompany.domain.PublicationCategory;
import com.example.servicecompany.service.PublicationCategoryService;
import com.example.servicecompany.service.PublicationService;
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
import java.util.UUID;

/**
 * REST controller
 */
@RestController
@RequestMapping("/api")
public class PublicationResource {

    private final Logger log = LoggerFactory.getLogger(Publication.class);

    private static final String ENTITY_NAME = "publication";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublicationService publicationService;

    public PublicationResource(PublicationService publicationService) {

        this.publicationService = publicationService;
    }

    /**
     * {@code POST  /publications} : Create a new publication.
     */
    @PostMapping("/publications")
    public ResponseEntity<Publication> createPublication(@Valid @RequestBody Publication publication) throws URISyntaxException {
        log.debug("REST request to save BaseTva : {}", publication);
        if (publication.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Publication result = publicationService.save(publication);
        return ResponseEntity.created(new URI("/api/publications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /publications} : Updates an existing publication.
     */
    @PutMapping("/publications")
    public ResponseEntity<Publication> updatePublication(@Valid @RequestBody Publication publication) throws URISyntaxException, IOException {

        log.debug("REST request to update Publication : {}", publication);
        if (publication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Publication result = publicationService.save(publication);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, publication.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /} : get all the Publications.
     */
    @GetMapping("/publications")
    public ResponseEntity<List<Publication>> getAllPublications(Pageable pageable) {
        log.debug("REST request to get a page of Publications");
        Page<Publication> page = publicationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" publication.
     */
    @GetMapping("/publications/{id}")
    public ResponseEntity<Publication> getPublication(@PathVariable UUID id) {
        log.debug("REST request to get Publication : {}", id);
        Optional<Publication> publication = publicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publication);
    }

    /**
     * {@code DELETE  /bank-enums/:id} : delete the "id" publication.
     */
    @DeleteMapping("/publications/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable UUID id) {
        log.debug("REST request to delete Publication : {}", id);
        publicationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
