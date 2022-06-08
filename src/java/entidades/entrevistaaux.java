/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;


import java.sql.Time;
import java.util.Date;

/**
 *
 * @author facat
 */
public class entrevistaaux {
    private Long numero_documento;
    private String nombre;
    private Time hora;
    private Date dia;

    public Long getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(Long numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    
    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }
    
}
