package com.example.servicecompany.service;


import com.example.servicecompany.config.StorageProperties;
import com.example.servicecompany.domain.Sauveteur;
import com.example.servicecompany.repository.SauveteurRepository;
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
public class SauveteurService {

    private final Logger log = LoggerFactory.getLogger(SauveteurService.class);

    private final SauveteurRepository sauveteurRepository;

    private final StorageProperties storageProps;

    public SauveteurService(SauveteurRepository sauveteurRepository, StorageProperties storageProps) {

        this.sauveteurRepository = sauveteurRepository;
        this.storageProps = storageProps;
    }

    /**
     * Save a sauveteur.
     */
    public Sauveteur save(MultipartFile files, Sauveteur sauveteur) throws IOException {
        log.debug("Request to save Sauveteur : {}", sauveteur);


        if(files != null) {
            /*Image*/


            String path = storageProps.getPath();
            // do your stuff here
            System.out.println(path);


            String realPath = path.substring(7,path.length());
            System.out.println(realPath);
            String articleSubCategoriesFolder = realPath+"/sauveteurImages/sauveteurs/";
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

            String newPath = storageProps.getUrl()+"/company-service/resources/sauveteurImages/sauveteurs/"+newNameOfImage;
            sauveteur.setImage(newPath);
        }

        return sauveteurRepository.save(sauveteur);
    }

    /**
     * Get all the sauveteur.
     */
    @Transactional(readOnly = true)
    public Page<Sauveteur> findAll(Pageable pageable) {
        log.debug("Request to get all Sauveteurs");
        return sauveteurRepository.findAll(pageable);
    }


    /**
     * Get one sauveteur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Sauveteur> findOne(UUID id) {
        log.debug("Request to get Sauveteur : {}", id);
        return sauveteurRepository.findById(id);
    }

    /**
     * Delete the sauveteur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Sauveteur : {}", id);
        sauveteurRepository.deleteById(id);
    }



}
