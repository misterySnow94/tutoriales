package Wilson.ProblemWilson.service;

import Wilson.ProblemWilson.dto.TrabajosDto;

public interface TrabajosService {
    
    public Long analisisTrabajos(String cedula, String archivo) throws Exception;
    
    public TrabajosDto busquedaTrabajo(Long id);
    
    public String guardarArchivo(Long id);
}
