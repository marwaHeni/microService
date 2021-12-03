package com.example.servicecompany.storage;

import com.example.servicecompany.config.StorageProperties;
import com.example.servicecompany.security.SecurityUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl  implements FilesStorageService{

    private final StorageProperties storageProps;

    String pathName = "compaktorImages";

    final private  Path root = Paths.get(pathName);

    public FilesStorageServiceImpl(StorageProperties storageProps) {
        this.storageProps = storageProps;
    }


    @Override
    public void init(String schemaName) throws IOException {

        String path = storageProps.getPath();
        // do your stuff here
        System.out.println(path);


        String realPath = path.substring(7,path.length());
        System.out.println(realPath);
        File file = new File(realPath+"/compaktorImages");
        File fileCompanyConfigs = new File(realPath+"/compaktorImages/"+schemaName+"/companyConfigs");
        File fileItems = new File(realPath+"/compaktorImages/"+schemaName+"/items");
        File fileReglements = new File(realPath+"/compaktorImages/"+schemaName+"/reglements");
        File fileTiers = new File(realPath+"/compaktorImages/"+schemaName+"/tiers");
        File filePdfs = new File(realPath+"/compaktorImages/"+schemaName+"/pdfs");
        File fileEshopPdfs = new File(realPath+"/compaktorImages/"+schemaName+"/eshopPdfs");
        File fileArticleCategories = new File(realPath+"/compaktorImages/"+schemaName+"/articleCategories");
        File fileArticleSubCategories = new File(realPath+"/compaktorImages/"+schemaName+"/articleSubCategories");
        File fileArticleMarks = new File(realPath+"/compaktorImages/"+schemaName+"/articleMarks");
        File fileArticles = new File(realPath+"/compaktorImages/"+schemaName+"/articles");
        File fileShippingConfigs = new File(realPath+"/compaktorImages/"+schemaName+"/shippingConfigs");
        File filePaymentEshopConfigs = new File(realPath+"/compaktorImages/"+schemaName+"/paymentEshopConfigs");
        File filePaymentEshops = new File(realPath+"/compaktorImages/"+schemaName+"/paymentEshops");
        File fileSchema = new File(realPath+"/compaktorImages/"+schemaName);
        if (!file.exists()) {
            file.mkdir();
        }else {
            System.out.println("file compaktorImage exist");
        }

        /*test if folder schema exist*/
        if (!fileSchema.exists()) {
            fileSchema.mkdir();

            fileCompanyConfigs.mkdir();
            fileItems.mkdir();
            fileReglements.mkdir();
            fileTiers.mkdir();
            filePdfs.mkdir();
            fileArticleCategories.mkdir();
            fileArticleSubCategories.mkdir();
            fileArticles.mkdir();
            fileArticles.mkdir();
            fileArticleMarks.mkdir();
            fileShippingConfigs.mkdir();
            filePaymentEshopConfigs.mkdir();
            filePaymentEshops.mkdir();
            fileEshopPdfs.mkdir();

        }else {
            System.out.println("file schema exist");
        }
        /*test if folder schema exist*/

    }

    @Override
    public void save(MultipartFile file  ) {
        try {
          //  String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        //    Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename().replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase()));

            Files.copy(file.getInputStream(),this.root.resolve(file.getOriginalFilename()));

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource loadImageTiers(String filename) {
        try {
            String folderName = SecurityUtils.getSchema();
            //String folderName = "amine";
            System.out.println("schemaname"+folderName);
            String tiersFolder = pathName+"/"+folderName+"/tiers";
            Path rootTiersFolder = Paths.get(tiersFolder);

            Path file = rootTiersFolder.resolve(filename);

            Resource resource = new UrlResource(file.toUri());


            if (resource.exists() || resource.isReadable()) {

                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAllTiers() {

        String folderName = SecurityUtils.getSchema();
        String tiersFolder = pathName+"/"+folderName+"/tiers";
        Path rootTiersFolder = Paths.get(tiersFolder);


        try {
            return Files.walk(rootTiersFolder, 1).filter(path -> !path.equals(rootTiersFolder)).map(rootTiersFolder::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            //System.out.println(file);


            /**//*
            File filee = new File(String.valueOf(file));
            filee.delete();

            *//**/

            if (resource.exists() || resource.isReadable()) {

                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAll() {

        String folderName = SecurityUtils.getSchema();


        String tiersFolder = folderName+"/tiers";




        Path mainRoot = Paths.get(folderName);

        Path rootTiersFolder = Paths.get(tiersFolder);

        try {
            return Files.walk(rootTiersFolder, 1).filter(path -> !path.equals(rootTiersFolder)).map(rootTiersFolder::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public void deleteImage(String filename) {
            Path file = root.resolve(filename);
            System.out.println(file);

            File filee = new File(String.valueOf(file));
            filee.delete();

    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }





}
