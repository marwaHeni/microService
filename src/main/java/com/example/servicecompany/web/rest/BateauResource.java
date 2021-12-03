package com.example.servicecompany.web.rest;

import com.example.servicecompany.domain.Bateau;
import com.example.servicecompany.service.BateauService;
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
public class BateauResource {

    private final Logger log = LoggerFactory.getLogger(Bateau.class);

    private static final String ENTITY_NAME = "bateau";

  //  @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BateauService bateauService;

    public BateauResource(BateauService bateauService) {
        this.bateauService = bateauService;
    }

    /**
     * {@code POST  /bateaux} : Create a new bateau.
     */
    @PostMapping("/bateaux")
    public ResponseEntity<Bateau> createBateau(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("bateau") String bateauJson) throws URISyntaxException, IOException {

        Gson g = new Gson();
        Bateau bateau = g.fromJson(bateauJson , Bateau.class);



        log.debug("REST request to save BaseTva : {}", bateau);
        if (bateau.getId() != null) {
            throw new BadRequestAlertException("A new baseTva cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Bateau result = bateauService.save(files, bateau);
        return ResponseEntity.created(new URI("/api/bateaux/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
     //   return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /bateaux} : Updates an existing bateau.
     */
//    @PutMapping("/bateaux")
//    public ResponseEntity<Bateau> updateBateau(@Valid @RequestBody Bateau bateau) throws URISyntaxException, IOException {
//
//        log.debug("REST request to update Bateau : {}", bateau);
//        if (bateau.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        Bateau result = bateauService.save(bateau);
//        return ResponseEntity.ok()
//                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bateau.getId().toString()))
//                .body(result);
//    }

    /**
     * {@code GET  /} : get all the Bateaux.
     */
    @GetMapping("/bateaux")
    public ResponseEntity<List<Bateau>> getAllBateaux(Pageable pageable) {
        log.debug("REST request to get a page of Bateaux");
        Page<Bateau> page = bateauService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-enums/:id} : get the "id" bateau.
     */
    @GetMapping("/bateaux/{id}")
    public ResponseEntity<Bateau> getBateau(@PathVariable UUID id) {
        log.debug("REST request to get Bateau : {}", id);
        Optional<Bateau> bateau = bateauService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bateau);
    }

    /**
     * {@code DELETE  /bank-enums/:id} : delete the "id" bateau.
     */
    @DeleteMapping("/bateaux/{id}")
    public ResponseEntity<Void> deleteBateau(@PathVariable UUID id) {
        log.debug("REST request to delete Bateau : {}", id);
        bateauService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
