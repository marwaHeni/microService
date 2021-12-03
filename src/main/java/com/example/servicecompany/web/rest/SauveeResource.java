package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.Sauvee;
import com.example.servicecompany.domain.Sauveteur;
import com.example.servicecompany.service.SauveeService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
public class SauveeResource {

    private final Logger log = LoggerFactory.getLogger(Sauvee.class);

    private static final String ENTITY_NAME = "sauvee";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SauveeService sauveeService;

    public SauveeResource(SauveeService sauveeService) {

        this.sauveeService = sauveeService;
    }

    /**
     * {@code POST  /sauvees} : Create a new sauvee.
     */
    @PostMapping("/sauvees")
     public ResponseEntity<Sauvee> createSauvee(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("sauvee") String sauveeJson) throws URISyntaxException, IOException {

        Gson g = new Gson();
        Sauvee sauvee = g.fromJson(sauveeJson , Sauvee.class);


        log.debug("REST request to save Sauvee : {}", sauvee);
        if (sauvee.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Sauvee result = sauveeService.save(files, sauvee);
        return ResponseEntity.created(new URI("/api/sauvees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /sauvees} : Updates an existing sauvee.
     */
//    @PutMapping("/sauvee")
//    public ResponseEntity<Sauvee> updateSauvee(@Valid @RequestBody Sauvee sauvee) throws URISyntaxException, IOException {
//
//        log.debug("REST request to update sauvee : {}", sauvee);
//        if (sauvee.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        Sauvee result = sauveeService.save(sauvee);
//        return ResponseEntity.ok()
//                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sauvee.getId().toString()))
//                .body(result);
//    }

    /**
     * {@code GET  /} : get all the Sauvees.
     */
    @GetMapping("/sauvees")
    public ResponseEntity<List<Sauvee>> getAllSauvees(Pageable pageable) {
        log.debug("REST request to get a page of Sauvees");
        Page<Sauvee> page = sauveeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" sauvee.
     */
    @GetMapping("/sauvees/{id}")
    public ResponseEntity<Sauvee> getSauvee(@PathVariable UUID id) {
        log.debug("REST request to get Sauvee : {}", id);
        Optional<Sauvee> sauvee = sauveeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sauvee);
    }

    /**
     * {@code DELETE  /sauvee/:id} : delete the "id" sauvee.
     */
    @DeleteMapping("/sauvees/{id}")
    public ResponseEntity<Void> deleteSauvee(@PathVariable UUID id) {
        log.debug("REST request to delete Sauvee : {}", id);
        sauveeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
