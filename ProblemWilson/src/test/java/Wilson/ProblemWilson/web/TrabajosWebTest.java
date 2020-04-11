/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wilson.ProblemWilson.web;

import Wilson.ProblemWilson.dto.TrabajosDto;
import Wilson.ProblemWilson.service.TrabajosService;
import Wilson.ProblemWilson.store.FileSystemStorageService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@WebMvcTest(TrabajosWeb.class)
public class TrabajosWebTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TrabajosService trabajosService;
    
    @MockBean
    private FileSystemStorageService storage;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of analisis method, of class TrabajosWeb.
     */
    @Test
    public void testAnalisis() throws Exception {
        System.out.println("analisis");
        MultipartFile archivo = null;
        String cedula = "";
        given(storage.store(archivo, cedula)).willReturn("prueba");
        given(trabajosService.analisisTrabajos(cedula,"prueba")).willReturn(new Long(1));
        
        mvc.perform(post("/api/trabajos?cedula="+ cedula,archivo)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /**
     * Test of obtenerTrabajo method, of class TrabajosWeb.
     */
    @Test
    public void testObtenerTrabajo() throws Exception{
        System.out.println("obtenerTrabajo");
        Long id = null;
        TrabajosDto instance = new TrabajosDto();
        instance.setId(new Long(1));
        given(trabajosService.busquedaTrabajo(id)).willReturn(instance);
        mvc.perform(get("/api/trabajos?id=1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /**
     * Test of obtenerArchivo method, of class TrabajosWeb.
     */
    @Test
    public void testObtenerArchivo() throws Exception{
        System.out.println("obtenerArchivo");
        Long id = new Long(1);
        String prueba= "prueba";
        TrabajosWeb instance = new TrabajosWeb();
        given(trabajosService.guardarArchivo(id)).willReturn(prueba);
        mvc.perform(get("/api/trabajos/archivo?id=1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    
}
