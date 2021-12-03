package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.PublicationImage;
import com.example.servicecompany.service.PublicationImageService;
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
public class PublicationImageResource {

    private final Logger log = LoggerFactory.getLogger(PublicationImage.class);

    private static final String ENTITY_NAME = "publicationImage";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublicationImageService publicationImageService;

    public PublicationImageResource(PublicationImageService publicationImageService) {

        this.publicationImageService = publicationImageService;
    }

    /**
     * {@code POST  /publication-images} : Create a new publicationImage.
     */
    @PostMapping("/publication-images")
    public ResponseEntity<PublicationImage> createPublicationImage(@Valid @RequestBody PublicationImage publicationImage) throws URISyntaxException {
        log.debug("REST request to save BaseTva : {}", publicationImage);
        if (publicationImage.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PublicationImage result = publicationImageService.save(publicationImage);
        return ResponseEntity.created(new URI("/api/publication-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /publication-images} : Updates an existing publicationImage.
     */
    @PutMapping("/publication-images")
    public ResponseEntity<PublicationImage> updatePublicationImage(@Valid @RequestBody PublicationImage publicationImage) throws URISyntaxException, IOException {

        log.debug("REST request to update PublicationImage : {}", publicationImage);
        if (publicationImage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PublicationImage result = publicationImageService.save(publicationImage);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, publicationImage.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /} : get all the PublicationImages.
     */
    @GetMapping("/publication-images")
    public ResponseEntity<List<PublicationImage>> getAllPublicationImages(Pageable pageable) {
        log.debug("REST request to get a page of Publications");
        Page<PublicationImage> page = publicationImageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" publicationImage.
     */
    @GetMapping("/publication-images/{id}")
    public ResponseEntity<PublicationImage> getPublicationImage(@PathVariable UUID id) {
        log.debug("REST request to get PublicationImage : {}", id);
        Optional<PublicationImage> publicationImage = publicationImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publicationImage);
    }

    /**
     * {@code DELETE  /bank-enums/:id} : delete the "id" publicationImage.
     */
    @DeleteMapping("/publication-images/{id}")
    public ResponseEntity<Void> deletePublicationImage(@PathVariable UUID id) {
        log.debug("REST request to delete PublicationImage : {}", id);
        publicationImageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
