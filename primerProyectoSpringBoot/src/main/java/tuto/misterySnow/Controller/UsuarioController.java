package tuto.misterySnow.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UsuarioController {
    
    @GetMapping
    public ResponseEntity<?> saludo(@RequestParam("nombre") String nombre){
        return ResponseEntity.ok("hola como estas " + nombre + " ?");
    }
    
}
