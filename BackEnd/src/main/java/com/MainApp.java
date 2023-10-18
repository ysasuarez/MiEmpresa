package com;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.models.Coche;
import com.models.Concesionario;
import com.models.services.CocheService;
import com.models.services.ConcesionarioService;

@SpringBootApplication
public class MainApp extends SpringBootServletInitializer {
	
    @Autowired
    private CocheService cocheService;

    @Autowired
    private ConcesionarioService concesionarioService;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @PostConstruct
    public void init() {
        createConcesionarios();
        createCoches();
    }

    private void createConcesionarios() {
        List<String> direcciones = Arrays.asList(
            "CalleMayor,123", "Avenidade laConstitución,456", "Paseode laCastellana,789", "CalleGranVía,321", "CalledeSerrano,654",
            "PaseodelPrado,987", "CalleAlcalá,135", "CallePreciados,246", "PaseodelaReforma,579", "Carrerde laRambla,765");

        direcciones.forEach(direccion -> concesionarioService.createConcesionario(new Concesionario(direccion)));
    }

    private void createCoches() {
        List<Coche> coches = Arrays.asList(
            new Coche("Ford", 25000.0, "2023-01-01", false, "1234ABC", 28000.0),
            new Coche("Chevrolet", 22000.0, "2023-02-15", false, "5678XYZ", 24000.0),
            new Coche("Toyota", 28000.0, "2023-03-20", true, "4567CDE", 31000.0),
            new Coche("Honda", 21000.0, "2023-04-05", false, "1234GHI", 23000.0),
            new Coche("Nissan", 23000.0, "2023-05-10", true, "4567MNK", 26000.0),
            new Coche("Kia", 19000.0, "2023-06-15", false, "1234OPQ", 21000.0),
            new Coche("Hyundai", 20000.0, "2023-07-20", false, "5678STU", 22000.0),
            new Coche("Mazda", 24000.0, "2023-08-25", true, "1234WXY", 27000.0),
            new Coche("Subaru", 27000.0, "2023-09-30", false, "1111AAB", 29000.0),
            new Coche("Volkswagen", 26000.0, "2023-10-01", true, "2222CCD", 30000.0)
        );

        coches.forEach(cocheService::createCoche);
    }
}
