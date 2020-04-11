package pruebapractica.model;

import java.util.Date;

public class Administradora {
    private int id;
    private String codigo;
    private String nombre;
    private String cod_tp_id;
    private String nro_id;
    private String naturaleza;
    private int multiple_arp;
    private int fsp;
    private int fusionada;
    private Date fecha_fusion;

    public Administradora(int id, String codigo, String nombre, String cod_tp_id, String nro_id, String naturaleza, int multiple_arp, int fsp, int fusionada, Date fecha_fusion) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cod_tp_id = cod_tp_id;
        this.nro_id = nro_id;
        this.naturaleza = naturaleza;
        this.multiple_arp = multiple_arp;
        this.fsp = fsp;
        this.fusionada = fusionada;
        this.fecha_fusion = fecha_fusion;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod_tp_id() {
        return cod_tp_id;
    }

    public void setCod_tp_id(String cod_tp_id) {
        this.cod_tp_id = cod_tp_id;
    }

    public String getNro_id() {
        return nro_id;
    }

    public void setNro_id(String nro_id) {
        this.nro_id = nro_id;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public int getMultiple_arp() {
        return multiple_arp;
    }

    public void setMultiple_arp(int multiple_arp) {
        this.multiple_arp = multiple_arp;
    }

    public int getFsp() {
        return fsp;
    }

    public void setFsp(int fsp) {
        this.fsp = fsp;
    }

    public int getFusionada() {
        return fusionada;
    }

    public void setFusionada(int fusionada) {
        this.fusionada = fusionada;
    }

    public Date getFecha_fusion() {
        return fecha_fusion;
    }

    public void setFecha_fusion(Date fecha_fusion) {
        this.fecha_fusion = fecha_fusion;
    }
    
}
