package Wilson.ProblemWilson.web;

import Wilson.ProblemWilson.dto.TrabajosDto;
import Wilson.ProblemWilson.service.TrabajosService;
import Wilson.ProblemWilson.store.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("api/trabajos")
@CrossOrigin()
@RestController
public class TrabajosWeb {
    @Autowired
    private TrabajosService trabajosService;
    @Autowired
    private FileSystemStorageService storage;
    
    @PostMapping
    public ResponseEntity<Long> analisis(@RequestHeader("archivo") MultipartFile archivo, @RequestParam("cedula") String cedula) throws Exception{
        String direccion = storage.store(archivo, cedula);
        Long respuesta = trabajosService.analisisTrabajos(cedula,direccion);
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping
    public ResponseEntity<TrabajosDto> obtenerTrabajo(@RequestParam("id") Long id){
        TrabajosDto respuesta = new TrabajosDto();
        respuesta = trabajosService.busquedaTrabajo(id);
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/archivo")
    public ResponseEntity<String> obtenerArchivo(@RequestParam("id") Long id){
        String respuesta = trabajosService.guardarArchivo(id);
        return ResponseEntity.ok(respuesta);
    }
}
