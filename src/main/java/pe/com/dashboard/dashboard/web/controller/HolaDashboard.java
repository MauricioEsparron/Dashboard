package pe.com.dashboard.dashboard.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/saludar")
public class HolaDashboard {
    @GetMapping("/hola")
    public String saludar() {
        return "¡Hola, mundo!";
    }

    /* Al correr el programa, a modo de prueba debes ingresar a esta ruta
       en tu navegador: http://localhost:8080/dashboard/api/v1/saludar/hola
        debería mostrarte el mensaje "¡Hola, mundo!"

    */
    
}
