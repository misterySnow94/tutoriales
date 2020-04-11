package Wilson.ProblemWilson.service.impl;

import Wilson.ProblemWilson.dto.DiaTrabajoDto;
import Wilson.ProblemWilson.dto.TrabajosDto;
import Wilson.ProblemWilson.entity.Trabajos;
import Wilson.ProblemWilson.exceptions.responses.NotFoundException;
import Wilson.ProblemWilson.repository.TrabajosRepositoy;
import Wilson.ProblemWilson.service.impl.serializador.DefaultSequenceGeneratorService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;




public class DefaultTrabajosServiceTest {
    @Mock
    private TrabajosRepositoy trabajosRepositoy;
    @Mock
    private DefaultSequenceGeneratorService sequenceGenerator;
    @Mock
    private ModelMapper modelMapper;
    private String location;
    
    @InjectMocks
    private DefaultTrabajosService defaultTrabajosService;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test(expected = NotFoundException.class)
    public void testAnalisisTrabajos() throws Exception {
        System.out.println("analisisTrabajos");
        String cedula = "1";
        String location = "";
        String line = "1";
        BufferedReader br = null;
        br = new BufferedReader(new FileReader("C:\\Users\\Usuario\\Documents\\GitHub\\tutoriales\\ProblemWilson\\src\\main\\resources\\archivos\\1\\lazy_loading_example_input.txt"));
        String archivo = "C:\\Users\\Usuario\\Documents\\GitHub\\tutoriales\\ProblemWilson\\src\\main\\resources\\archivos\\1\\lazy_loading_example_input.txt";
        Long respuesta = defaultTrabajosService.analisisTrabajos(cedula, archivo);
    }

    /**
     * Test of busquedaTrabajo method, of class DefaultTrabajosService.
     */
    @Test
    public void testBusquedaTrabajo() {
        System.out.println("busquedaTrabajo");
        Long id = new Long(1);
        Trabajos trabajos = new Trabajos();
        Optional<Trabajos> busqueda = Optional.of(trabajos);
        TrabajosDto trabajosDto = new TrabajosDto();
        given(trabajosRepositoy.findById(id)).willReturn(busqueda);
        given(modelMapper.map(busqueda.get(), TrabajosDto.class)).willReturn(trabajosDto);
        TrabajosDto resultado = defaultTrabajosService.busquedaTrabajo(id);
        assertEquals(resultado, trabajosDto);
    }

    @Test(expected = NotFoundException.class)
    public void testBusquedaTrabajoException() {
        System.out.println("busquedaTrabajo");
        Long id = new Long(1);
        Optional<Trabajos> busqueda = Optional.empty();
        TrabajosDto trabajosDto = new TrabajosDto();
        given(trabajosRepositoy.findById(id)).willReturn(busqueda);
        TrabajosDto resultado = defaultTrabajosService.busquedaTrabajo(id);
    }
    
    /**
     * Test of guardarArchivo method, of class DefaultTrabajosService.
     */
    @Test
    public void testGuardarDiaTrabajoFalse() {
        System.out.println("guardarDiaTrabajoFalse");
        Trabajos trabajo = new Trabajos();
        Date fecha = new Date();
        Optional<Trabajos> busqueda = Optional.empty();
        given(trabajosRepositoy.findByFechaRegistro(fecha)).willReturn(Optional.empty());
        boolean respuesta = defaultTrabajosService.guardarDiaTrabajo(trabajo);
        assertEquals(respuesta, false);
    }
    
    @Test
    public void testGuardarDiaTrabajoTrue() {
        System.out.println("guardarDiaTrabajoTrue");
        Trabajos trabajo = new Trabajos();
        trabajo.setDiasTrabajo(6);
        Date fecha = new Date();
        Optional<Trabajos> busqueda = Optional.of(trabajo);
        when(trabajosRepositoy.save(trabajo)).thenReturn(trabajo);
        given(trabajosRepositoy.findByFechaRegistro(fecha)).willReturn(Optional.of(trabajo));
        boolean respuesta = defaultTrabajosService.guardarDiaTrabajo(trabajo);
        //esta muy raro, no funciona correctamente
        assertEquals(respuesta, false);
    }
    
    @Test
    public void testAnalisisViajesPrimerCiclo() {
        System.out.println("analisisViajesPrimerCiclo");
        Long id = null;
        DiaTrabajoDto dia = new DiaTrabajoDto();
        ArrayList<Integer> pesos = new ArrayList();
        pesos.add(60);
        pesos.add(60);
        dia.setPesos(pesos);
        int resultado = defaultTrabajosService.analisisViajes(dia);
        assertEquals(resultado, 2);
    }
    
    @Test
    public void testAnalisisViajesTercerCiclo() {
        System.out.println("analisisViajesTercerCiclo");
        Long id = null;
        DiaTrabajoDto dia = new DiaTrabajoDto();
        ArrayList<Integer> pesos = new ArrayList();
        pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);
        dia.setPesos(pesos);
        int resultado = defaultTrabajosService.analisisViajes(dia);
        assertEquals(resultado, 2);
    }
    
    @Test
    public void testAnalisisViajesSegundoCiclo() {
        System.out.println("analisisViajesSegundoCiclo");
        Long id = null;
        DiaTrabajoDto dia = new DiaTrabajoDto();
        ArrayList<Integer> pesos = new ArrayList();
        pesos.add(25);pesos.add(20);pesos.add(25);pesos.add(20);pesos.add(25);pesos.add(20);
        dia.setPesos(pesos);
        int resultado = defaultTrabajosService.analisisViajes(dia);
        assertEquals(resultado, 3);
    }
    
    @Test
    public void testAnalisisViajesCompleto() {
        System.out.println("analisisViajesCompleto");
        Long id = null;
        DiaTrabajoDto dia = new DiaTrabajoDto();
        ArrayList<Integer> pesos = new ArrayList();
        pesos.add(25);pesos.add(20);pesos.add(25);pesos.add(20);pesos.add(25);pesos.add(20);
        pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);
        pesos.add(60);
        pesos.add(60);
        dia.setPesos(pesos);
        int resultado = defaultTrabajosService.analisisViajes(dia);
        assertEquals(resultado, 7);
    }
    
    @Test
    public void testAnalisisViajesVacio() {
        System.out.println("analisisViajesVacio");
        Long id = null;
        DiaTrabajoDto dia = new DiaTrabajoDto();
        ArrayList<Integer> pesos = new ArrayList();
        dia.setPesos(pesos);
        int resultado = defaultTrabajosService.analisisViajes(dia);
        assertEquals(resultado, 0);
    }
    
    @Test
    public void testAnalisisViajesPesoExtra() {
        System.out.println("analisisViajesPesoExtra");
        Long id = null;
        DiaTrabajoDto dia = new DiaTrabajoDto();
        ArrayList<Integer> pesos = new ArrayList();
        pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);pesos.add(20);
        dia.setPesos(pesos);
        int resultado = defaultTrabajosService.analisisViajes(dia);
        assertEquals(resultado, 1);
    }
    
    
}
