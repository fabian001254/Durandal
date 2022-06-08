/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.AparteContratacion;
import entidades.Aspirantes;
import entidades.Clientes;
import entidades.InscripcionesOfertas;
import entidades.Mailer;
import entidades.Ofertas;
import entidades.Usuarios;
import facades.AparteContratacionFacade;
import facades.AspirantesFacade;
import facades.ClientesFacade;
import facades.InscripcionesOfertasFacade;
import facades.OfertasFacade;
import facades.UsuariosFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author facat
 */
@Named(value = "ofertasControlador")
@SessionScoped
public class OfertasControlador implements Serializable {

    //Variables
    public OfertasControlador() {
    }
    String campo;
    Ofertas ofertas;
    InscripcionesOfertas insofertas;
    Aspirantes aspirantes;
    Clientes clientes;
    AparteContratacion apartes;

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    
    public Ofertas getOfertas() {
        return ofertas;
    }

    public void setOfertas(Ofertas ofertas) {
        this.ofertas = ofertas;
    }

    public Aspirantes getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }

    @EJB
    OfertasFacade ofertasfacade;
    @EJB
    InscripcionesOfertasFacade insofertasfacade;
    @EJB
    AspirantesFacade aspirantesfacade;
    @EJB
    AparteContratacionFacade apartesfacade;
    @EJB
    ClientesFacade clientesfacade;
    @EJB
    UsuariosFacade usuariosfacade;
    
    @PostConstruct
    public void init(){
        ofertas = new Ofertas();
        insofertas = new InscripcionesOfertas();
        aspirantes = new Aspirantes();
        clientes = new Clientes();
        apartes = new AparteContratacion();
    }
    
    //Consultas
    public List<Ofertas> BuscarOfertasPerfil(Long doc){
        String perfil = aspirantesfacade.find(doc).getIdPerfil().getPl();
        return ofertasfacade.consulta(perfil);
    }
    
    public void buscarOferta(int id){
        ofertas = ofertasfacade.find(id);
    }

    public String getCampo() {
        return campo;
    }
    
    public List<InscripcionesOfertas> BuscarInscripciones(long doc){
        return insofertasfacade.consulta(aspirantesfacade.find(doc));
    }
    
     public List<AparteContratacion> buscarApartes(Long doc){
         clientes = clientesfacade.find(doc);
         return apartesfacade.consultaA(clientes);
     }
    
    public List<InscripcionesOfertas> BuscarInscripcionescl(long doc){
        List<AparteContratacion> ap = buscarApartes(doc);
        List<InscripcionesOfertas> ins;
        ins = new ArrayList<>();
        for(int i=0;i<ap.size();i++){
            ofertas = ofertasfacade.consulta1(ap.get(i));
            ins.addAll(insofertasfacade.consulta1(ofertas));
        }
        return ins;        
    }
    public void consultaEstado(Long doc){
        aspirantes = aspirantesfacade.find(doc);
        try {
            if(aspirantes.getEstado().equals("Inscrito a oferta")){
                campo = "Por ende usted no podra inscribirse a más ofertas hasta que finalice el proceso ";
                FacesContext.getCurrentInstance().getExternalContext().redirect("aspiranteioc.xhtml");
        }else if(aspirantes.getEstado().equals("Contratado")){
                campo = "Por lo cual no puede inscribirse a una oferta hasta finalizar el contrato con la empresa correspondiente";
                FacesContext.getCurrentInstance().getExternalContext().redirect("aspiranteioc.xhtml");
        }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    //Funciones
    public void InscribirseEnLaOferta(int id,Long doc){
        try {
            buscarOferta(id);
            aspirantes = aspirantesfacade.find(doc);            
            aspirantes.setEstado("Inscrito a oferta");
            aspirantesfacade.edit(aspirantes);
            insofertas.setIdOferta(ofertasfacade.find(id));
            insofertas.setNumeroDocumento(aspirantesfacade.find(doc));
            insofertasfacade.create(insofertas);
            enviarCorreo("Inscripcion a oferta exitosa",1);
            aspirantes.setEstado("");
        } catch (Exception e) {
        }
    }
    public void Rechazar(Long doc, int id){
        try {            
            aspirantes = aspirantesfacade.find(doc);
            insofertasfacade.remove(insofertasfacade.find(id));
            insofertas = insofertasfacade.find(id);
            aspirantes.setEstado("Reclutado");
            aspirantesfacade.edit(aspirantes);
            enviarCorreo("Rechazado de la oferta",2);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Eliminado exitosamente"));
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }
    
    //Correos
    public void enviarCorreo(String asun,int op){
             try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Inscrito exitosamente"));
                 String asunto = asun;
                 Usuarios usu = usuariosfacade.find(aspirantes.getNumerodocumento());
                 String destinatario =  usu.getCorreo();
                 if(op == 1){
                     Mailer.send(destinatario,asunto,mensajeConEstiloInscripcion(usu.getNombre1(),usu.getApellido1())); 
                 }else if(op == 2){
                     Mailer.send(destinatario,asunto,mensajeConEstiloR(usu.getNombre1(),usu.getApellido1())); 
                 }
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
    public String mensajeConEstiloInscripcion(String nombre1, String apellido1){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a) : "+nombre1+" "+apellido1
                    + "<p> El dia de hoy usted se inscribio a la oferta de la empresa: \n"+ofertas.getIdApartes().getNumerodocumentocliente().getRazonsocial()  
                    +"<p> Esto hace que no se pueda inscribir a otras ofertas mientras este inscrito a una, tendra que esperar a que termine el proceso</p></br><p>Ahora su estado es: INSCRITO A OFERTA</p>"
                    + "<p> ¿Que quiere decir este estado?: "
                    +"<p>Actualmente esta inscrito a una oferta,esto hace que no se pueda incribir a otras ofertas mientras este inscrito a una, tendra que esperar a que termine el proceso de la oferta"
                    +"<p style=\"color: #green; font-\"Lamentamos que no pueda ser reclutado por parte de OPTA :( "
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";
    }
    
    public String mensajeConEstiloR(String nombre1, String apellido1){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a) : "+nombre1+" "+apellido1
                    + "<p> Usted ha sido rechazado en la oferta de la empresa: \n"+ofertas.getIdApartes().getNumerodocumentocliente().getRazonsocial()  
                    +"<p> A partir de este momento podra inscribirse a otra oferta</p></br><p>Ahora su estado es: RECLUTADO</p>"
                    + "<p> ¿Que quiere decir este estado?: "
                    +"<p>Este estado quiere decir que actualmente usted esta registrado y reclutado por OPTA, por lo cual podra inscribirse en una de las convocatorias que se encuentren disponibles"
                    +"<p style=\"color: #green; font-\"Lamentamos que no pueda ser reclutado por parte de OPTA :( "
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";
    }
        
    
}
