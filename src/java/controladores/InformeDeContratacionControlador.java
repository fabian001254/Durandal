/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Aspirantes;
import entidades.Clientes;
import entidades.InformeDeContratacion;
import entidades.InscripcionesOfertas;
import facades.AspirantesFacade;
import facades.ClientesFacade;
import facades.InformeDeContratacionFacade;
import facades.InscripcionesOfertasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author facat
 */
@Named(value = "informeDeContratacionControlador")
@SessionScoped
public class InformeDeContratacionControlador implements Serializable {
    
    //Variable
    public InformeDeContratacionControlador() {
    }   
    InformeDeContratacion info;
    Aspirantes aspirantes;
    Clientes clientes;
    LocalDate fechai,fechaf;
    InscripcionesOfertas insofertas;
    @EJB
    InformeDeContratacionFacade infofacade;
    @EJB
    AspirantesFacade aspirantesfacade;
    @EJB
    ClientesFacade clientesfacade;
    @EJB
    InscripcionesOfertasFacade insofertasfacade;

    public InformeDeContratacion getInfo() {
        return info;
    }

    public void setInfo(InformeDeContratacion info) {
        this.info = info;
    }

    public Aspirantes getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public LocalDate getFechai() {
        return fechai;
    }

    public void setFechai(LocalDate fechai) {
        this.fechai = fechai;
    }

    public LocalDate getFechaf() {
        return fechaf;
    }

    public void setFechaf(LocalDate fechaf) {
        this.fechaf = fechaf;
    }
    
    @PostConstruct
    public void init(){
        info = new InformeDeContratacion();
        aspirantes = new Aspirantes();
        clientes = new Clientes();
        insofertas = new InscripcionesOfertas();
    }
  
    //Consultas
    public List<Aspirantes> consultarA(){
        return aspirantesfacade.consultaR();
    }
    public List<Clientes> consultarC(){
        return clientesfacade.findAll();
    }
    public List<InformeDeContratacion> consultaInformes(){
        return infofacade.findAll();
    }
    public List<Aspirantes> consultarContratados(){
        return aspirantesfacade.consulta();
    }
    public void buscarInfo(int id,Long doc1,Long doc2){
        info.setNumerodocumentoaspirante(aspirantesfacade.find(doc1));
        info.setNumerodocumentocliente(clientesfacade.find(doc2));
        info = infofacade.find(id);
    }
    
    public List<InformeDeContratacion> buscarInformePersonaA(Long doc){
        aspirantes = aspirantesfacade.find(doc);
        return infofacade.consultaInformesA(aspirantes);
    }
    public List<InformeDeContratacion> buscarInformePersonaB(Long doc){
        clientes = clientesfacade.find(doc);
        return infofacade.consultaInformesB(clientes);
    }
    public String BuscarRazon(Long doc){
        clientes = clientesfacade.find(doc);
        return clientesfacade.find(doc).getRazonsocial();
    }
    //Funciones
    public void crear(){
        try {
            Date fecha1 = Date.valueOf(fechai);
            Date fecha2 = Date.valueOf(fechaf);
            info.setNumerodocumentoaspirante(aspirantesfacade.find(aspirantes.getNumerodocumento()));
            info.setNumerodocumentocliente(clientesfacade.find(clientes.getNumerodocumento()));
            info.setFechainicio(fecha1);
            info.setFechafinal(fecha2);           
            InformeDeContratacion in = infofacade.consulta(info);
            if(in != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Error este informe de contratacion ya existe!"));
            }else{                
                infofacade.create(info);
                Aspirantes asp = aspirantesfacade.find(aspirantes.getNumerodocumento());
                if(asp.getEstado().equals("Inscrito a oferta")){
                        insofertas = insofertasfacade.consultaA(asp);
                        insofertasfacade.remove(insofertas);
                }
                asp.setEstado("Contratado");
                aspirantesfacade.edit(asp);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Informe de contratacion creado con exito!"));
            }
            
        } catch (Exception e) {
            System.out.println("Error "+e);
        } 
    }
    
    public void operar(){
        try {
            infofacade.edit(info);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Informe de contratacion ha actualizado con exito!"));
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }
    public void Eliminar(int id,Long doc1,Long doc2){
        try {
            buscarInfo(id,doc1,doc2);
            infofacade.remove(info);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Informe de contratacion se ha borrado con exito!"));
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }       
}
