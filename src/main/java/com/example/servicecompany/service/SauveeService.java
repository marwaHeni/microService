package com.example.servicecompany.service;


import com.example.servicecompany.config.StorageProperties;
import com.example.servicecompany.domain.Sauvee;
import com.example.servicecompany.repository.SauveeRepository;
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
public class SauveeService {

    private final Logger log = LoggerFactory.getLogger(SauveeService.class);

    private final SauveeRepository sauveeRepository;

    private final StorageProperties storageProps;

    public SauveeService(SauveeRepository sauveeRepository, StorageProperties storageProps) {

        this.sauveeRepository = sauveeRepository;
        this.storageProps = storageProps;
    }

    /**
     * Save a Sauvee.
     */
    public Sauvee save(MultipartFile files, Sauvee sauvee) throws IOException {
        log.debug("Request to save Sauvee : {}", sauvee);


        if(files != null) {
            /*Image*/


            String path = storageProps.getPath();
            // do your stuff here
            System.out.println(path);


            String realPath = path.substring(7,path.length());
            System.out.println(realPath);
            String articleSubCategoriesFolder = realPath+"/sauveteurImages/sauvees/";
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

            String newPath = storageProps.getUrl()+"/company-service/resources/sauveteurImages/sauvees/"+newNameOfImage;
            sauvee.setImage(newPath);
        }


        return sauveeRepository.save(sauvee);
    }

    /**
     * Get all the sauvees.
     */
    @Transactional(readOnly = true)
    public Page<Sauvee> findAll(Pageable pageable) {
        log.debug("Request to get all Sauvees");
        return sauveeRepository.findAll(pageable);
    }


    /**
     * Get one sauvee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Sauvee> findOne(UUID id) {
        log.debug("Request to get Sauvee : {}", id);
        return sauveeRepository.findById(id);
    }

    /**
     * Delete the sauvee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Sauvee : {}", id);
        sauveeRepository.deleteById(id);
    }



}
