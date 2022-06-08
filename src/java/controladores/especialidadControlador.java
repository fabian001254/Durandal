/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Especialidad;
import facades.EspecialidadFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author facat
 */
@Named(value = "especialidadControlador")
@SessionScoped
public class especialidadControlador implements Serializable {

    //Variables
    public especialidadControlador() {
    }
    
    private Especialidad especialidad;

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    @EJB
    private EspecialidadFacade especialidadfacade;
    
    @PostConstruct
    public void init(){
        especialidad = new Especialidad();
    }
    
    
    //Funciones
    public void Crear(){
        try {
            Especialidad esp = especialidadfacade.Consulta(especialidad.getNombreespecialidad());
            if(esp != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ya existe una especialidad con el nombre de "+especialidad.getNombreespecialidad()));
            }else{
                especialidad.getNombreespecialidad();
                especialidadfacade.create(especialidad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Especialidad "+especialidad.getNombreespecialidad()+" guardada con exito"));
                especialidad.setNombreespecialidad("");
            }
            
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }
}
