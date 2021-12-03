package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.PublicationVideo;
import com.example.servicecompany.service.PublicationVideoService;
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
public class PublicationVideoResource {

    private final Logger log = LoggerFactory.getLogger(PublicationVideo.class);

    private static final String ENTITY_NAME = "publicationVideo";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublicationVideoService publicationVideoService;

    public PublicationVideoResource(PublicationVideoService publicationVideoService) {

        this.publicationVideoService = publicationVideoService;
    }

    /**
     * {@code POST  /publication-images} : Create a new publicationVideo.
     */
    @PostMapping("/publication-videos")
    public ResponseEntity<PublicationVideo> createPublicationVideo(@Valid @RequestBody PublicationVideo publicationVideo) throws URISyntaxException {
        log.debug("REST request to save BaseTva : {}", publicationVideo);
        if (publicationVideo.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PublicationVideo result = publicationVideoService.save(publicationVideo);
        return ResponseEntity.created(new URI("/api/publication-videos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /publication-images} : Updates an existing publicationVideo.
     */
    @PutMapping("/publication-videos")
    public ResponseEntity<PublicationVideo> updatePublicationVideo(@Valid @RequestBody PublicationVideo publicationVideo) throws URISyntaxException, IOException {

        log.debug("REST request to update PublicationVideo : {}", publicationVideo);
        if (publicationVideo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PublicationVideo result = publicationVideoService.save(publicationVideo);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, publicationVideo.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /} : get all the PublicationVideos.
     */
    @GetMapping("/publication-videos")
    public ResponseEntity<List<PublicationVideo>> getAllPublicationVideos(Pageable pageable) {
        log.debug("REST request to get a page of Publications");
        Page<PublicationVideo> page = publicationVideoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" publicationVideo.
     */
    @GetMapping("/publication-videos/{id}")
    public ResponseEntity<PublicationVideo> getPublicationVideo(@PathVariable UUID id) {
        log.debug("REST request to get PublicationVideo : {}", id);
        Optional<PublicationVideo> publicationVideo = publicationVideoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publicationVideo);
    }

    /**
     * {@code DELETE  /bank-enums/:id} : delete the "id" publicationVideo.
     */
    @DeleteMapping("/publication-videos/{id}")
    public ResponseEntity<Void> deletePublicationVideo(@PathVariable UUID id) {
        log.debug("REST request to delete PublicationVideo : {}", id);
        publicationVideoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
