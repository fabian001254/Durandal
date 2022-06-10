/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.AparteContratacion;
import entidades.Clientes;
import entidades.Mailer;
import entidades.Mailers;
import entidades.Ofertas;
import entidades.TipoDeContrato;
import facades.AparteContratacionFacade;
import facades.ClientesFacade;
import facades.OfertasFacade;
import facades.TipoDeContratoFacade;
import facades.UsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author facat
 */
@Named(value = "aparteContratacionControlador")
@SessionScoped
public class AparteContratacionControlador implements Serializable {

    //Variables
    public AparteContratacionControlador() {
    }
    AparteContratacion aparte;
    Ofertas ofertas;
    TipoDeContrato tp;
    Clientes cliente;
    String estado = "Todos";

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @EJB 
    AparteContratacionFacade apartefacade;
    @EJB
    ClientesFacade clientesfacade;
    @EJB
    UsuariosFacade usuariosfacade;
    @EJB
    OfertasFacade ofertasfacade;
    @EJB
    TipoDeContratoFacade tpcfacade;
    public TipoDeContrato getTp() {
        return tp;
    }

    public void setTp(TipoDeContrato tp) {
        this.tp = tp;
    }
    
    public AparteContratacion getAparte() {
        return aparte;
    }

    public void setAparte(AparteContratacion aparte) {
        this.aparte = aparte;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    @PostConstruct
    public void init(){
        aparte = new AparteContratacion();
        ofertas = new Ofertas();
        tp = new TipoDeContrato();
        createBarModels();
    }
    
    //Consultas
    public List<AparteContratacion> ConsultarApartes(){
        if(estado.equals("Todos")){
            return apartefacade.findAll();
        }else{
            return apartefacade.consulta(estado);
        }
    }
    public List<AparteContratacion> ConsultarApartesM(){
            return apartefacade.consulta("En espera");
    }
    public void buscar(int id,int op){
        aparte = apartefacade.find(id);
        if(op == 1){
            crearOferta();
        }else if(op == 2){
            rechazarOferta();
        }
    }
    public List<TipoDeContrato> listartipos(){
        return tpcfacade.findAll();
    }
    public String BuscarRazon(Long doc){
        cliente = clientesfacade.find(doc);
        return clientesfacade.find(doc).getRazonsocial();
    }
    public List<AparteContratacion> buscarApartes(Long doc){
        cliente = clientesfacade.find(doc);
        return apartefacade.consultaA(cliente);
    }
    public void BuscarApartes(int id){
        aparte = apartefacade.find(id);
    }
    //Funciones  
    public void limpiarAp(){
        aparte = new AparteContratacion();
    }
    public void ofertas(int op){
        try {
            if(op == 1){                
                ofertas.setIdApartes(apartefacade.find(aparte.getIdApartes()));                
                ofertas.setIdTipoContrato(aparte.getIdTipoContrato());
                ofertas.setFechainicio(aparte.getDiainicial());
                ofertas.setFechafinal(aparte.getDiafinal());
                ofertas.setDescripcion(aparte.getDescripcion());
                ofertas.setTiempoexperiencia(aparte.getTiempoexperiencia());
                ofertas.setSueldo(aparte.getSueldo());
                ofertas.setRequerimientos(aparte.getRequrimientos());
                ofertas.setFechadepublicacion(Date.valueOf(LocalDate.now()));
                ofertas.setCantvacantes(aparte.getCantpersonal());
                ofertas.setPl(aparte.getPl());
                ofertas.setEstado("En reclutamiento");
                ofertasfacade.create(ofertas);
                aparte.setEstado("Creado");
                apartefacade.edit(aparte);
            }else if(op == 2){
                aparte.setEstado("Rechazado");
                apartefacade.edit(aparte);
            }
        } catch (Exception e) {
        }
    }

    public void crearAparte(){
        try {
            aparte.setNumerodocumentocliente(cliente);
            aparte.setEstado("En espera");
            aparte.setIdTipoContrato(tp);
            apartefacade.create(aparte);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Aparte de contratación creado con éxito!, puede ver el estado en CONSULTAR APARTES"));
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }
    
     public void edits(int id, int op){
         if(op == 2){
             aparte = apartefacade.find(id);
         }
         if(aparte.getEstado().equals("Rechazado") || aparte.getEstado().equals("Creado")){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "El aparte de contratación no es modificable ya que su estado es "+aparte.getEstado()));
         }else{
             if(op==1){
                 apartefacade.edit(aparte);
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El aparte se ha modificado con éxito"));
                 aparte = null;
             }else if(op==2){
                 apartefacade.remove(aparte);
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El aparte se ha eliminado"));
             }
         }
     }
     
     //Correos
     public void enviarCorreo(String asun,int op){
             try {                
                 String asunto = asun;
                 if(op == 1){
                     List<String> usu = usuariosfacade.listarAspirantes();
                     Mailers.send(usu,asunto,mensajeConEstiloAceptar()); 
                 }else{
                     String destinatario = aparte.getNumerodocumentocliente().getCorreo();
                     Mailer.send(destinatario,asunto,mensajeConEstiloRechazar()); 
                 }
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
     
    public void crearOferta(){
        String asun = "Nueva oferta de contratación";
        ofertas(1);
        enviarCorreo(asun,1);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Aparte de contratacion aceptado y oferta creada"));
    }
    public void rechazarOferta(){
        String asun = "Aparte de contratación rechazado";
        ofertas(2);
        enviarCorreo(asun,2);        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Aparte de contratacion rechazado"));
    }
    public String mensajeConEstiloAceptar(){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Se ha generado una oferta </p>\n"                
                    + "<p> El perfil de la oferta es: "+ofertas.getPl()+ " </p>\n" 
                    + "<p> Salario: "+ofertas.getSueldo() +"</p>"
                    + "<p> Descripción: "+ofertas.getDescripcion() +"</p> \n"
                    +"<p> Mas datos en el apartado de OFERTAS DE CONTRATACION </p> \n"
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";
    }
    public String mensajeConEstiloRechazar(){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Se ha rechazado el aparte de contratación </p>\n"                
                    + "<p> El perfil de la oferta es: "+aparte.getPl()+ " </p>\n" 
                    + "<p> Salario: "+aparte.getSueldo() +"</p>"
                    + "<p> Descripción: "+aparte.getDescripcion() +"</p> \n"
                    +"<p> Mas datos en el apartado de OFERTAS DE CONTRATACIÓN </p> \n"
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";
    }
     
    //Graficas
    private BarChartModel graficoBarra;

    public BarChartModel getGraficoBarra() {
        return graficoBarra;
    }

    public void setGraficoBarra(BarChartModel graficoBarra) {
        this.graficoBarra = graficoBarra;
    }
    
    private void createBarModels() {
            graficoBarra = new BarChartModel();
            ChartSeries estado = new ChartSeries();
            estado.setLabel("Aparte de contratacion");
            List<AparteContratacion> cont = apartefacade.consultaap(); 
            for (AparteContratacion conteoperfil : cont) {
                estado.set(conteoperfil.getEstado(), conteoperfil.getCantpersonal());
            }
            graficoBarra.addSeries(estado);    
                graficoBarra.setTitle("Conteo apartes rechazados/aceptados");
                graficoBarra.setLegendPosition("ne");

                Axis xAxis = graficoBarra.getAxis(AxisType.X);
                xAxis.setLabel("Estado");

                Axis yAxis = graficoBarra.getAxis(AxisType.Y);
                yAxis.setLabel("Cantidad");
                yAxis.setMin(0);
    }
}
