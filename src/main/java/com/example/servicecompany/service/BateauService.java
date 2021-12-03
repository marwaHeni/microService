package com.example.servicecompany.service;


import com.example.servicecompany.config.StorageProperties;
import com.example.servicecompany.domain.Bateau;
import com.example.servicecompany.repository.BateauRepository;
import com.example.servicecompany.security.SecurityUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation
 */
@Service
@Transactional
public class BateauService {

    private final Logger log = LoggerFactory.getLogger(BateauService.class);

    private final BateauRepository bateauRepository;

    private final StorageProperties storageProps;

    public BateauService(BateauRepository bateauRepository, StorageProperties storageProps) {

        this.bateauRepository = bateauRepository;
        this.storageProps = storageProps;
    }

    /**
     * Save a Bateau.
     */
    public Bateau save(MultipartFile files, Bateau bateau) throws IOException {
        log.debug("Request to save Bateau : {}", bateau);


        if(files != null) {
            /*Image*/


            String path = storageProps.getPath();
            // do your stuff here
            System.out.println(path);


            String realPath = path.substring(7,path.length());
            System.out.println(realPath);
            String articleSubCategoriesFolder = realPath+"/sauveteurImages/bateaux/";
            Path rootArticleSubCategories = Paths.get(articleSubCategoriesFolder);
            String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

            Files.copy(files.getInputStream(), rootArticleSubCategories.resolve(files.getOriginalFilename().replace(files.getOriginalFilename(),
                    FilenameUtils.getBaseName(files.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(files.getOriginalFilename()))));

            /*get name of image with currentDate + extension*/
            String fileName = files.getOriginalFilename();
            int locationofExtension = fileName.lastIndexOf('.');
            String extension = fileName.substring(locationofExtension, fileName.length());
            String nameWithoutExtension = fileName.substring(0, locationofExtension);


            String newNameOfImage = nameWithoutExtension + currentDate + extension;

            String newPath = storageProps.getUrl()+"/company-service/resources/sauveteurImages/bateaux/"+newNameOfImage;
            bateau.setImage(newPath);
        }

        return bateauRepository.save(bateau);
    }

    /**
     * Get all the bateaux.
     */
    @Transactional(readOnly = true)
    public Page<Bateau> findAll(Pageable pageable) {
        log.debug("Request to get all Bateaux");
        return bateauRepository.findAll(pageable);
    }


    /**
     * Get one bateau by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bateau> findOne(UUID id) {
        log.debug("Request to get Bateau : {}", id);
        return bateauRepository.findById(id);
    }

    /**
     * Delete the bateau by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Bateau : {}", id);
        bateauRepository.deleteById(id);
    }



}
