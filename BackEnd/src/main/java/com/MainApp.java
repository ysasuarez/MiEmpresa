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
    List<Concesionario> concesionarios;

    @PostConstruct
    public void init() {
        createConcesionarios();
        createCoches();
    }

    private void createConcesionarios() {
    	// Crear una lista de concesionarios de ejemplo.
    	concesionarios = Arrays.asList(
        		 new Concesionario("Calle Mayor,123"),
        		 new Concesionario("Avenida de la Constitución, 456"),
        		 new Concesionario("Paseo de la Castellana, 789"),
        		 new Concesionario("Calle Gran Vía, 321"),
        		 new Concesionario("Calle de Serrano, 654"));    
    	
    	// Agregar cada concesionario a través del servicio de Concesionario.
    	concesionarios.forEach(concesionarioService::createConcesionario);
    	
       }

    private void createCoches() {
    	// Crear una lista de coches de ejemplo.
        List<Coche> coches = Arrays.asList(
            new Coche("Ford", 25000.0, "2023-01-01",  "1234ABC", 28000.0, concesionarios.get(0)),
            new Coche("Chevrolet", 22000.0, "2023-02-15", "5678XYZ", 24000.0,concesionarios.get(0)),
            new Coche("Toyota", 28000.0, "2023-03-20",  "4567CDE", 31000.0,concesionarios.get(1)),
            new Coche("Honda", 21000.0, "2023-04-05",  "1234GHI", 23000.0,concesionarios.get(1)),
            new Coche("Nissan", 23000.0, "2023-05-10",  "4567MNK", 26000.0,concesionarios.get(2)),
            new Coche("Kia", 19000.0, "2023-06-15",  "1234OPQ", 21000.0,concesionarios.get(2)),
            new Coche("Hyundai", 20000.0, "2023-07-20",  "5678STU", 22000.0,concesionarios.get(3)),
            new Coche("Mazda", 24000.0, "2023-08-25",  "1234WXY", 27000.0,concesionarios.get(3)),
            new Coche("Subaru", 27000.0, "2023-09-30",  "1111AAB", 29000.0,concesionarios.get(4)),
            new Coche("Volkswagen", 26000.0, "2023-10-01",  "2222CCD", 30000.0,concesionarios.get(4))
        );

        // Agregar cada coche a través del servicio de Coche.
        coches.forEach(cocheService::createCoche);
    }
}
