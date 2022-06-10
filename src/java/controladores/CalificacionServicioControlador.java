/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Aspirantes;
import entidades.CalificacionServicio;
import entidades.Clientes;
import entidades.InformeDeContratacion;
import entidades.Mailer;
import entidades.Promedio;
import entidades.Usuarios;
import facades.AspirantesFacade;
import facades.CalificacionServicioFacade;
import facades.ClientesFacade;
import facades.InformeDeContratacionFacade;
import facades.UsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
@Named(value = "calificacionServicioControlador")
@SessionScoped
public class CalificacionServicioControlador implements Serializable {

    /**
     * Creates a new instance of CalificacionServicioControlador
     */
    
    //Variables
    public CalificacionServicioControlador() {
    }
    
    private Aspirantes aspirantes;
    private InformeDeContratacion info;
    private Clientes clientes;
    private Usuarios usuarios;
    private CalificacionServicio caliservicio;

    public Aspirantes getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }

    public InformeDeContratacion getInfo() {
        return info;
    }

    public void setInfo(InformeDeContratacion info) {
        this.info = info;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public CalificacionServicio getCaliservicio() {
        return caliservicio;
    }

    public void setCaliservicio(CalificacionServicio caliservicio) {
        this.caliservicio = caliservicio;
    }
    
    @PostConstruct
    public void init(){
        aspirantes = new Aspirantes();
        info = new InformeDeContratacion();
        clientes = new Clientes();
        usuarios = new Usuarios();
        caliservicio = new CalificacionServicio();
        createBarModels();
    }
    
    @EJB
    UsuariosFacade usuariosfacade;
    @EJB
    AspirantesFacade aspirantesfacade;
    @EJB
    ClientesFacade clientesfacade;
    @EJB
    CalificacionServicioFacade caliserviciofacade;
    @EJB
    InformeDeContratacionFacade infofacade;
    
    
    public List<Promedio> listarInformes1(){
        return caliserviciofacade.consulta();
    }
    
    public String buscarNombre(Long doc){
        return usuariosfacade.find(doc).getNombre1();
    }
    public String buscarApellido(Long doc){
        return usuariosfacade.find(doc).getApellido1();
    }
    public String buscarPl(Long doc){
        return aspirantesfacade.find(doc).getIdPerfil().getPl();
    }
    
    //Consultas
    public List<InformeDeContratacion> listarInformes(){
        List<InformeDeContratacion> lista = infofacade.findAll();
        List<InformeDeContratacion> listaaux = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String hoy = formatter.format(date);  
        for(int i = 0; i< lista.size();i++){           
            aspirantes = aspirantesfacade.find(lista.get(i).getNumerodocumentoaspirante().getNumerodocumento());            
            info = infofacade.find(lista.get(i).getIdInforme());
            
            if(aspirantes.getEstado().equals("Contratado") && (formatter.format(info.getFechafinal()).equals(hoy)) ){
                listaaux.add(lista.get(i));
            }          
        }
        return listaaux;
    }
    public void buscarInfo(int id,Long doc1,Long doc2){        
        info.setNumerodocumentoaspirante(aspirantesfacade.find(doc1));
        info.setNumerodocumentocliente(clientesfacade.find(doc2));
        info = infofacade.find(id); 
    }
    
    public List<CalificacionServicio> buscarCalificacionesA(Long doc){
        aspirantes = aspirantesfacade.find(doc);
        return caliserviciofacade.consultaA(aspirantes);
    }
    public List<CalificacionServicio> buscarCalificacionesB(Long doc){
        clientes = clientesfacade.find(doc);
        return caliserviciofacade.consultaB(clientes);
    }
    public List<CalificacionServicio> buscarCalificacionesC(Long doc){
        clientes = clientesfacade.find(doc);
        return caliserviciofacade.consultac(clientes);
    }
    public void masinfo(int id){
        caliservicio = caliserviciofacade.find(id);
    }
    
    //Funciones
    public void calificarpersonal(){
        caliservicio.setEstado("Calificado");
        double prom = (caliservicio.getActiipersonal() + caliservicio.getCalitrabajo() + caliservicio.getPuntualidad() + caliservicio.getResponsabilidad())/4;        
        caliservicio.setPromedio((int) Math.ceil(prom));
        caliserviciofacade.edit(caliservicio);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha calificado con exito al aspirante, ¡Gracias!"));
    }
    
    //Correo-Funcion
    public void notificacion(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String hoy = formatter.format(date);  
        if(!formatter.format(info.getFechafinal()).equals(hoy)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Tiene que crear el formulario el mismo día que finaliza el contrato"+date));
        }else{
            aspirantes = aspirantesfacade.find(info.getNumerodocumentoaspirante().getNumerodocumento());
            aspirantes.setEstado("Reclutado");
            clientes = clientesfacade.find(info.getNumerodocumentocliente().getNumerodocumento());
            caliservicio.setNumerodocumentoaspirante(aspirantes);
            caliservicio.setNumerodocumentocliente(clientes);
            caliservicio.setFechainicio(info.getFechainicio());
            caliservicio.setFechafinal(info.getFechafinal());
            caliservicio.setEstado("Sin contestar");
            caliserviciofacade.create(caliservicio);
            aspirantesfacade.edit(aspirantes);
            enviarCorreo("Calificar el servicio");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha enviado con éxito el formulario"));
        }
    }
    //Correos
    public void enviarCorreo(String asun){
             try {
                 String asunto = asun;
                 Usuarios usu = usuariosfacade.find(clientes.getNumerodocumento());
                 Usuarios usu2 = usuariosfacade.find(aspirantes.getNumerodocumento());                 
                 String destinatario = usu.getCorreo();
                 Mailer.send(destinatario,asunto,mensajeConEstilo(usu.getNombre1(),usu.getApellido1(),usu2.getNombre1(),usu.getApellido1())); 
                 destinatario = usu2.getCorreo();
                 Mailer.send(destinatario,asunto,mensajeConEstilo1(usu2.getNombre1(),usu.getApellido1(),clientes.getRazonsocial())); 

        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
    
     public String mensajeConEstilo(String nombre1,String nombre2,String nombre11,String nombre22){
       return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a) : "+nombre1+" "+nombre2
                    + "<p> El contrato con "+nombre11 + " "+ nombre22+ "ha finalizado"+ "\n"
                    +"<p> Agradeceríamos su calificación en la página de OPTA sobre el personal, con esto nos ayudara a mejorar </p>"
                    + "<p> ¿Como hacer esto?: </p>"
                    +"<p> Inicie sesión y dirijase a la parte de '’Calificar personal’' allí vera al personal que contrato y podrá calificarle en algunos aspectos de 1 a 5 donde 1 es la más baja y 5 la más alta </p>"
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad :3 </p> ";

    }
     public String mensajeConEstilo1(String nombre1,String nombre2,String nombre11){
            return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a): "+nombre1+" "+nombre2
                    + "<p> El contrato con "+nombre11+" ha finalizado"+ "\n"
                    + "<p> Su estado actual pasara a ser RECLUTADO </p>"                    
                    + "<p> ¿Qué quiere decir este estado?: "
                    +"<p> Este estado quiere decir que actualmente usted está registrado y reclutado por OPTA, por lo cual podrá inscribirse en una de las convocatorias que se encuentren disponibles"                    
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad :3 </p> ";

    }
    
    //Graficos
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
            estado.setLabel("Perfil");
            List<Promedio> cont = caliserviciofacade.grafica(); 
            for (Promedio conteocali : cont) {
                estado.set(conteocali.getPromedio(),conteocali.getDocumento());
            }
            graficoBarra.addSeries(estado);    
                graficoBarra.setTitle("Conteo de calificaciones");
                graficoBarra.setLegendPosition("ne");

                Axis xAxis = graficoBarra.getAxis(AxisType.X);
                xAxis.setLabel("Puntuacion de calificacion");

                Axis yAxis = graficoBarra.getAxis(AxisType.Y);
                yAxis.setLabel("Cantidad de veces que se registra");
                yAxis.setMin(0);
    }
}
