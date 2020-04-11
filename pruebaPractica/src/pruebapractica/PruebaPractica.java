package pruebapractica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pruebapractica.model.Administradora;


public class PruebaPractica {

    public static void main(String[] args) throws Exception{
        //Validacion de datos de administradoras.
        System.out.println("prueba conexion");
        Conexion conexion = new Conexion();
        System.out.println("conecto");
        String csvFile = "./temporal.txt";
        BufferedReader br = null;
        String line = "";
        boolean bandera = true;
        ArrayList<Administradora> guardado = new ArrayList<>();
        String cvsSplitBy = ";";
        List<String> errores = new ArrayList<>();
        int seq = 0;
        try {
            System.out.println("leyendo");
            br = new BufferedReader(new FileReader(csvFile));
            System.out.println("empezando lineas");
            while ((line = br.readLine()) != null) {
                System.out.println("line: " + line.toString());
                String[] datos = line.split(cvsSplitBy);
                if(revisionLetras(datos[0])){
                    if(revisionLetras(datos[1])){
                        if(revisionTipoIdentificacion(datos[2])){
                            if(revisionNumeros(datos[3])){
                                if(revisionNatural(datos[4])){
                                    java.util.Date fecha = new Date();
                                    Administradora elemento = new Administradora(seq,datos[0],datos[1],datos[2],datos[3],datos[4],revisionX(datos[5]),revisionX(datos[6]),revisionX(datos[7]),fecha);
                                    guardado.add(elemento);
                                }
                                else{
                                    errores.add("Se ha encontrado problemas en: " + datos.toString() + " en la naturaleza");
                                    bandera = false;
                                }
                            }
                            else{
                                errores.add("Se ha encontrado problemas en: " + datos.toString() + " en la identificacion");
                                bandera = false;
                            }
                        }
                        else{
                            errores.add("Se ha encontrado problemas en: " + datos.toString() + " en el tipo de identificacion");
                            bandera = false;
                        }
                    }
                    else{
                        errores.add("Se ha encontrado problemas en: " + datos.toString() + " en el nombre");
                        bandera = false;
                    }
                }
                else{
                    errores.add("Se ha encontrado problemas en: " + datos.toString() + " en el codigo");
                    bandera = false;
                }
            }
            if(bandera==true){
                //guardado
                errores.add("guardados satisfactoriamente");
            }else{
                errores.add("Motivos por lo que no se guardo");
            }
            System.out.println("errores: " + errores);
            errores(errores);
            guardar(conexion.connectDatabase("localhost", "3306", "practica", "root", ""), guardado);
            System.out.println("guardado");
        } catch (Exception e) {
            System.out.println("catch");
      } finally {
         if (null != br) {
            br.close();
             System.out.println("finally");
         } 
      }
    }
    
    
    public static boolean revisionLetras(String cadena){
        if(cadena.matches("\\w.*")){
            return true;
        }
        return false;
    }
    
    public static boolean revisionTipoIdentificacion(String cadena){
        String[] tipos = {"NI", "CC", "PA", "RC"};
        for(String tipo: tipos){
            if(cadena.matches(tipo)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean revisionNumeros(String cadena){
        if(cadena.matches("\\d.*")){
            return true;
        }
        return false;
    }
    
    public static boolean revisionNatural(String cadena){
        String[] tipos = {"PR", "PU", "MI"};
        for(String tipo: tipos){
            if(cadena.matches(tipo)){
                return true;
            }
        }
        return false;
    }
    
    public static int revisionX(String cadena){
        if(cadena.matches("x") || !cadena.isEmpty()){
            return 0;
        }
        return 1;
    }
    
    public static void errores(List<String> mensaje) throws Exception{
        String ruta = "errores.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        String mensaj = "";
        for(String ms:mensaje){
            mensaj = mensaj.concat(ms);
        }
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(mensaj);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(mensaj);
        }
        bw.close();
    }
    
    public static void guardar(Connection conexion, ArrayList<Administradora> guardado) throws SQLException{
      try{
         PreparedStatement consulta;
         for(Administradora ad:guardado){
             consulta = conexion.prepareStatement("INSERT INTO ADMINISTRADORA (id,codigo, nombre,cod_tp_id,nro_id,naturaleza,multiple_arp,fsp,fusionada,fecha_fusion) "
                     + "VALUES(" + Integer.toString(ad.getId()) + "," + ad.getCodigo()+ "," + ad.getNombre() + "," + ad.getCod_tp_id() + "," + ad.getNro_id() + "," + ad.getNaturaleza() + "," + ad.getMultiple_arp() + "," + ad.getFsp() + "," + ad.getFusionada() + "," + ad.getFecha_fusion() +");");
            consulta.executeUpdate();
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
}
