package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.Bateau;
import com.example.servicecompany.domain.Sauveteur;
import com.example.servicecompany.service.SauveteurService;
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
public class SauveteurResource {

    private final Logger log = LoggerFactory.getLogger(Sauveteur.class);

    private static final String ENTITY_NAME = "sauveteur";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SauveteurService sauveteurService;

    public SauveteurResource(SauveteurService sauveteurService) {

        this.sauveteurService = sauveteurService;
    }

    /**
     * {@code POST  /sauveteurs} : Create a new sauveteur.
     */
    @PostMapping("/sauveteurs")
    public ResponseEntity<Sauveteur> createSauveteur(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("sauveteur") String sauveteurJson) throws URISyntaxException, IOException {

        Gson g = new Gson();
        Sauveteur sauveteur = g.fromJson(sauveteurJson , Sauveteur.class);


        log.debug("REST request to save BaseTva : {}", sauveteur);
        if (sauveteur.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Sauveteur result = sauveteurService.save(files, sauveteur);
        return ResponseEntity.created(new URI("/api/sauveteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /sauveteurs} : Updates an existing sauveteur.
     */
//    @PutMapping("/sauveteurs")
//    public ResponseEntity<Sauveteur> updateSauveteur(@Valid @RequestBody Sauveteur sauveteur) throws URISyntaxException, IOException {
//
//        log.debug("REST request to update Sauveteur : {}", sauveteur);
//        if (sauveteur.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        Sauveteur result = sauveteurService.save(sauveteur);
//        return ResponseEntity.ok()
//                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sauveteur.getId().toString()))
//                .body(result);
//    }

    /**
     * {@code GET  /} : get all the Sauveteurs.
     */
    @GetMapping("/sauveteurs")
    public ResponseEntity<List<Sauveteur>> getAllSauveteurs(Pageable pageable) {
        log.debug("REST request to get a page of Sauveteurs");
        Page<Sauveteur> page = sauveteurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" sauveteur.
     */
    @GetMapping("/sauveteurs/{id}")
    public ResponseEntity<Sauveteur> getSauveteur(@PathVariable UUID id) {
        log.debug("REST request to get Sauveteur : {}", id);
        Optional<Sauveteur> sauveteur = sauveteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sauveteur);
    }

    /**
     * {@code DELETE  /bank-enums/:id} : delete the "id" sauveteur.
     */
    @DeleteMapping("/sauveteurs/{id}")
    public ResponseEntity<Void> deleteSauveteur(@PathVariable UUID id) {
        log.debug("REST request to delete Sauveteur : {}", id);
        sauveteurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
