package Wilson.ProblemWilson.service.impl;

import Wilson.ProblemWilson.dto.DiaTrabajoDto;
import Wilson.ProblemWilson.dto.TrabajosDto;
import Wilson.ProblemWilson.entity.DiaTrabajo;
import Wilson.ProblemWilson.entity.Trabajador;
import Wilson.ProblemWilson.entity.Trabajos;
import Wilson.ProblemWilson.exceptions.responses.NotFoundException;
import Wilson.ProblemWilson.repository.TrabajosRepositoy;
import Wilson.ProblemWilson.service.TrabajosService;
import Wilson.ProblemWilson.service.impl.serializador.DefaultSequenceGeneratorService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DefaultTrabajosService implements TrabajosService{
    @Value("${staticresourceloader.fileLocation.path}")
    private Path location;

    private ModelMapper modelMapper;
    private TrabajosRepositoy trabajosRepositoy;
    private DefaultSequenceGeneratorService sequenceGenerator;

    public DefaultTrabajosService(ModelMapper modelMapper, TrabajosRepositoy trabajosRepositoy, DefaultSequenceGeneratorService sequenceGenerator) {
        this.modelMapper = modelMapper;
        this.trabajosRepositoy = trabajosRepositoy;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public Long analisisTrabajos(String cedula, String archivo) throws Exception {
        String file = location + "\\" + archivo;
        String line = "";
        int dias = 0, contador = 0, viajes = 0;
        boolean bandera = true;
        int elementos = 0;
        ArrayList<DiaTrabajo> diasTrabajos = new ArrayList();
        Trabajos trabajo = new Trabajos();
        Trabajador trabajador = new Trabajador();
        trabajador.setCedula(cedula);
        trabajo.setTrabajador(trabajador);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            if((line = br.readLine()) != null){
                dias = Integer.parseInt(line);
                trabajo.setDiasTrabajo(dias);
            }
            line = br.readLine();
            contador = Integer.parseInt(line);
            while(dias>=1) {
                ArrayList<Integer> pesos = new ArrayList();
                elementos = contador;
                DiaTrabajo diaTrabajo = new DiaTrabajo();
                while(contador>0){
                    pesos.add(Integer.parseInt(br.readLine()));
                    contador --;
                }
                diaTrabajo.setPesos(pesos);
                diaTrabajo.setElementos(elementos);
                diaTrabajo.setDia(dias);
                viajes = analisisViajes(modelMapper.map(diaTrabajo, DiaTrabajoDto.class));
                diaTrabajo.setViajesDia(viajes);
                diasTrabajos.add(diaTrabajo);
                dias--;
                if(( line = br.readLine()) != null){
                    contador = Integer.parseInt(line);
                }
            }
            trabajo.setDias(diasTrabajos);
        }
        catch (Exception e) {
                throw new NotFoundException("No se pudo realizar el analisis");
        } finally {
           if (null != br) {
              br.close();
           }
           boolean respuesta = guardarDiaTrabajo(trabajo);
           if(respuesta){
               return trabajo.getId();
           }
           throw new NotFoundException("No se pudo guardar la jornada de trabajo");
        }
    }

    public int analisisViajes(DiaTrabajoDto diaTrabajo) {
        ArrayList<Integer> pesos = new ArrayList();
        int viajes = 0,cantidad= 0, mayor = 0;
        boolean bandera = true;
        pesos = diaTrabajo.getPesos();
        Comparator<Integer> comparador = Collections.reverseOrder();
        Collections.sort(pesos, comparador);
        //conteo 1
        while(bandera){
            if(pesos.isEmpty()){
                bandera = false;
            }else{
                if(pesos.get(0) >= 50){
                    viajes++;
                    pesos.remove(0);
                }else{
                    bandera = false;
                }
            }
        }
        bandera = true;
        cantidad=0;
        //conteo 2
        while(bandera){
            if(pesos.isEmpty()){
                bandera = false;
            }else{
                if(pesos.get(0)*pesos.size()>= 50){
                    mayor = pesos.get(0);
                    pesos.remove(0);
                    cantidad= mayor;
                    while(cantidad < 50){
                        if(pesos.size()>0){
                            cantidad = cantidad + mayor;
                            pesos.remove(pesos.size() - 1);
                        }else{
                            cantidad = 50;
                        }
                    }
                    viajes++;
                    cantidad=0;
                }else{
                    bandera = false;
                }
            }
        }
        return viajes;
    }
    
    public boolean guardarDiaTrabajo(Trabajos trabajo){
        Date fecha = new Date();
        trabajo.setFechaRegistro(fecha);
        trabajo.setId(sequenceGenerator.generateSequence(Trabajos.SEQUENCE_NAME));
        trabajo = trabajosRepositoy.save(trabajo);
        Optional<Trabajos> busqueda = trabajosRepositoy.findByFechaRegistro(fecha);
        if(busqueda.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public TrabajosDto busquedaTrabajo(Long id) {
        if(id > 0){
            Optional<Trabajos> busqueda = trabajosRepositoy.findById(id);
            if(busqueda.isPresent()){
                return modelMapper.map(busqueda.get(), TrabajosDto.class);
            }
        }
        throw new NotFoundException("No se pudo guardar la jornada de trabajo");
    }
    
    @Override
    public String guardarArchivo(Long id){
        TrabajosDto trabajo = busquedaTrabajo(id);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(location + "\\" + id +".txt", true);
            pw = new PrintWriter(fichero);
            int i = 1;
            for(DiaTrabajoDto dia: trabajo.getDias()){
                String viaje = Integer.toString(dia.getViajesDia());
                pw.println("Case #" + i +" : " + viaje);
                i++;
            }
        } catch (Exception e) {
            throw new NotFoundException("No se pudo crear el archivo");
        } finally {
            try {
                if (null != fichero){
                    fichero.close();
                    return "http://localhost:8080/resources/" + id +".txt";
                }
            } catch (Exception e2) {
                throw new NotFoundException("No se pudo crear el archivo");
            }
        }
        throw new NotFoundException("No se pudo crear el archivo");
    }
    
}
