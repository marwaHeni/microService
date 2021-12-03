package com.example.servicecompany;

import com.example.servicecompany.security.jwt.MyWayProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@EnableDiscoveryClient //on dit au micro-service qd tu demarre publie ta reference ds l'annuaire(sans cette annotation, le service d'enregistrement ne peut detecter aucune instance de ce micro-service:il a trouver zero instance)
@SpringBootApplication
@EnableConfigurationProperties({MyWayProperties.class})
@EnableSwagger2 // swagger va scan voter projet et génére une documentaion pour votre API
@ComponentScan("com.example.servicecompany")
public class ServiceCompanyApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServiceCompanyApplication.class, args);


//
//    //Autre méthode au liey d'implementer CommandeLineRunner et de refenir la méthode run
//    @Bean //tous ce qui déclarer bean ce sont des methodes qui vont demarrer au démarrage
//    CommandLineRunner start(CompanyRepository companyRepository) { //passer en parametre CompanyRepositoryest equivalent à autowired
//         return args -> {
//             Stream.of("A","B","C").forEach(cn->{
//                 companyRepository.save(new Company(null,cn,100+Math.random()*900));
//             });
//             companyRepository.findAll().forEach(System.out::println); // <=>companyRepository.findAll().forEach(s->{Sytem.out.println(s.toString());forEach par defaut pour chaque methode utilise la méthode toString
//         };
//    }
//
//        Configuration cfg = new Configuration().configure("/hibernate.cfg.xml");
//
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Tva tva = new Tva();
//        tva.setId(1L);
//        tva.setValue(2.0);
//        session.save(tva);
//        // session.close();
//        session.getTransaction().commit();
//        sessionFactory.close();
        

    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }



}