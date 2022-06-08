/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Aspirantes;
import entidades.CalificacionServicio;
import entidades.ConteoEstado;
import entidades.Entrevista;
import entidades.Mailer;
import entidades.Promedio;
import entidades.PruebasPsicotecnicas;
import entidades.Usuarios;
import entidades.entrevistaaux;
import facades.AspirantesFacade;
import facades.CalificacionServicioFacade;
import facades.EntrevistaFacade;
import facades.PerfilFacade;
import facades.PruebasPsicotecnicasFacade;
import facades.UsuariosFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author facat
 */
@Named(value = "aspirantesControlador")
@SessionScoped
public class AspirantesControlador implements Serializable {

    /**
     * Creates a new instance of AspirantesControlador
     */
    //variables
    public AspirantesControlador() {
    }
    private LocalDate dia;
    private LocalTime hora;
    private ConteoEstado estado;
    private Aspirantes aspirantes;
    private Entrevista entrevista;
    private Usuarios usuarios;
    private PruebasPsicotecnicas pruebas;
    JasperPrint jasperPrint;
    private entrevistaaux entre;
    Long docu;
    @EJB
    private AspirantesFacade aspirantesfacade;
    @EJB
    private EntrevistaFacade entrevistasfacade;
    @EJB
    private UsuariosFacade usuariosfacade;
    @EJB
    private CalificacionServicioFacade califacade;
    @EJB
    private PruebasPsicotecnicasFacade psicotecnicasfacade;
    @EJB
    private PerfilFacade perfilfacade;
    @PostConstruct
    public void init(){
        pruebas = new PruebasPsicotecnicas();
        estado = new ConteoEstado();
        entrevista = new Entrevista();
        usuarios = new Usuarios();
        createBarModels();
    }

    public Aspirantes getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }

    public Entrevista getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(Entrevista entrevista) {
        this.entrevista = entrevista;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    

    public ConteoEstado getEstado() {
        return estado;
    }

    public void setEstado(ConteoEstado estado) {
        this.estado = estado;
    }

    
    
   
    //Consultas
    public String buscarperfil(Long doc){
        return aspirantesfacade.find(doc).getEstado();
    }
    public List<Aspirantes> consultaAspirantesEnEspera(){
        return aspirantesfacade.consultaEnEspera();
    }
     public List<Aspirantes> consultaAspirantesR(){
        return aspirantesfacade.consultaR();
    }
    public void ponerdoc(Long doc){
        docu = doc;
        aspirantes = aspirantesfacade.find(docu);
    }
    public void buscarInfo(Long doc1){
        aspirantes = aspirantesfacade.find(doc1);
    }
    public List<Entrevista> listarEntrevistas(){    
        listarentrevistasr();
        return entrevistasfacade.findAll();         
    }
    public String BuscarHv(Long doc){
        return aspirantesfacade.find(doc).getHojavida();
    }
    public BigInteger buscarT(Long doc){
         return usuariosfacade.find(doc).getTelefono();
    }
    public String buscarC(Long doc){
          return usuariosfacade.find(doc).getCorreo();
    }
    public String buscarApellido(Long doc){
        return usuariosfacade.find(doc).getApellido1();
    }  
    public void listarentrevistasr(){
        List<Entrevista> listaen = entrevistasfacade.findAll();
        listaen1.clear();
        if(listaen != null){
            for(int i =0;i< listaen.size();i++){
            entrevistaaux entrev = new entrevistaaux();
            entrev.setNumero_documento(listaen.get(i).getDocumento());
            entrev.setNombre(buscarNombre(entrev.getNumero_documento())+ " "+ buscarApellido(entrev.getNumero_documento()));                                               
            Time time = new Time(listaen.get(i).getFecha().getTime());
            entrev.setHora(time);//esperar
            entrev.setDia(listaen.get(i).getFecha());
            listaen1.add(entrev);                
            }
        }        
     }
    List<entrevistaaux> listaen1 = new ArrayList();
    public String buscarNombre(Long doc){
        return usuariosfacade.find(doc).getNombre1();
    }
    
    //Funciones    
    public int promedio(Long doc){
       List<Promedio> prom = califacade.consulta();
       int prome = 0;
       for(int i=0;i< prom.size();i++){  
           Long aux = prom.get(i).getDocumento();
           if(aux.equals(doc)){
                prome = prom.get(i).getPromedio();
           }
       }
       return prome;
    }
    
    //Reporte    
    public void PDF(ActionEvent actionEvent) throws JRException, IOException{
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listaen1);
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//reportes//reporteEntrevista.jasper");
        jasperPrint=JasperFillManager.fillReport(ruta, new HashMap(),beanCollectionDataSource);
        HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporteProducto.pdf");
        ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();     
    }       
    
    //Correos
    public void enviarCorreoAOC(String asun,int ra){
             try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Ha citado con exito al aspirante"));
                 String asunto = asun;
                 Usuarios usu = usuariosfacade.find(aspirantes.getNumerodocumento());
                 String destinatario = usu.getCorreo();
                 switch(ra){
                         case 1:
                            Mailer.send(destinatario,asunto,mensajeConEstiloAcepta(usu)); 
                            break;
                         case 2:
                            Mailer.send(destinatario,asunto,mensajeConEstiloRechaza(usu)); 
                            break;
                 }                 
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
        private String pathPruebas1 = "../Archivos/PruebasPsicotecnicas/Plantillas/CUESTIONARIO1.pdf";
        private String pathPruebas2 = "../Archivos/PruebasPsicotecnicas/Plantillas/CUESTIONARIO2.pdf";
        public void AsignarPruebas(Long doc,int id, Date dia){
         aspirantes = aspirantesfacade.find(doc);
         Random r = new Random();
         int valorDado = r.nextInt(9)+1; 
         if(valorDado > 5){
             pruebas.setPathPrueba(pathPruebas1);
         }else{
              pruebas.setPathPrueba(pathPruebas2);
         }
        pruebas.setIdPerfil(perfilfacade.find(id));
        pruebas.setNumerodocumento(aspirantesfacade.find(doc));
        pruebas.setTiempolimite(dia);
        psicotecnicasfacade.create(pruebas);
    }
        
    public void citar(){
        Aspirantes aspi = aspirantesfacade.find(docu);
        aspi.setEstado("Citado a entrevista");
        aspirantesfacade.edit(aspi);
        entrevista.setDocumento(aspirantes.getNumerodocumento());
        entrevista.setFecha(Date.valueOf(dia));
        entrevista.setHora(Time.valueOf(hora));
        AsignarPruebas(docu,aspirantes.getIdPerfil().getIdPerfil(),Date.valueOf(dia));
        entrevistasfacade.create(entrevista);  
    }
    
    
    public void enviarCorreoEntrevista(){
             try {
                citar();
                String asunto = "Citacion a entrevista";
                Usuarios usu = usuariosfacade.find(aspirantes.getNumerodocumento());
                String destinatario = usu.getCorreo();
                Mailer.send(destinatario,asunto,mensajeConEstiloEntrevista(usu.getNombre1(),usu.getApellido1())); 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha enviado la entrevista con exito"));
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
    public void AceptarEnt(Long doc){
         try {
             aspirantes = aspirantesfacade.find(doc);
             aspirantes.setEstado("Reclutado");
             entrevista = entrevistasfacade.find(doc);
             aspirantesfacade.edit(aspirantes);
             entrevistasfacade.remove(entrevista);
             usuarios = usuariosfacade.find(doc);
             usuarios.setRol("As");
             usuariosfacade.edit(usuarios);
             enviarCorreoAOC("Reclutado con exito",1);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Se ha reclutado con exito"));
         } catch (Exception e) {
             System.out.println("Error "+e);
         }
     }
     public void RechazarEnt(Long doc,int op){
         try {
             aspirantes = aspirantesfacade.find(doc);
             aspirantes.setEstado("Rechazado");
             entrevista = entrevistasfacade.find(doc);
             aspirantesfacade.edit(aspirantes);
             if(op == 1){
                 entrevistasfacade.remove(entrevista);
             }
             enviarCorreoAOC("Reclutamiento Rechazado",2);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Se ha rechazado con exito"));
         } catch (Exception e) {
             System.out.println("Error "+e);
         }
     }



        public String mensajeConEstiloAcepta(Usuarios usu){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a) : "+usu.getNombre1()+" "+usu.getApellido1()
                    + "<p> ¡Felicidades! ha sido reclutado por parte de OPTA, Su estado ahora es RECLUTADO\n"
                    + "<p> ¿Que quiere decir este estado?: "
                    +"<p> Este estado quiere decir que actualmente usted esta registrado y reclutado por OPTA, por lo cual podra inscribirse en una de las convocatorias que se encuentren disponibles"
                    +"<p style=\"color: #green; font-\"¡Felicidades por pasar el proceso exitosamente! "
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad :3 </p> ";
    }
    public String mensajeConEstiloRechaza(Usuarios usu){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a) : "+usu.getNombre1()+" "+usu.getApellido1()
                    + "<p> Tristemente usted no ha sido reclutado por parte de OPTA, su estado actual es RECHAZADO \n"
                    + "<p> ¿Que quiere decir este estado?: "
                    +"<p>Este estdo qui que su hoja de vida o su prueba psicotecnica no cumplio con lo que requiere el perfil o no cumple los reuqisitos para ser reclutado"
                    +"<p style=\"color: #green; font-\"Lamentamos que no pueda ser reclutado por parte de OPTA :( "
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";
    }
        public String mensajeConEstiloEntrevista(String nombre1,String nombre2){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a) : "+nombre1+" "+nombre2
                    + "<p> ¡Felicidades! ha sido citado para una entrevista por parte de OPTA y a partir de ahora su estado pasara a CITADO A ENTREVISTA \n"
                    +"<p> Dia de la citacion: "+dia+" </p>"
                    +"<p> Hora de la citacion: "+hora+" </p>"
                    + "<p> ¿Que quiere decir este estado?: "
                    +"<p> Esto quiere decir que esta citado a una entrevista en la central de OPTA en la fecha y dia indicado"
                    + "<p> Por otro lado tambien debera realizar la prueba psicotecnica, esta la encontrara iniciando sesion, luego presione el apartado de 'Prueba Psicotecnica' y posteriormente presione en 'Descargar prueba' una vez la realice guardelo como archivo pdf. Por ultimo vuelva al apartado anteriormente menciado y presione ''Subir prueba psicotecnica'' alli ingrese su archivo pdf y listo ya habra entregado la prueba con exito. </p>"
                    + "<p style=\"font-weight:bold;\"> La prueba tiene como fecha limite de entrega el dia "+dia+"! </p>"
                    +"<p style=\"color: #green; font-\"¡Le deseamos la mejor de las suertes en este proceso! "
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
            estado.setLabel("Estado");
            List<ConteoEstado> cont = aspirantesfacade.consultaEstados(); 
            for (ConteoEstado conteoEstado : cont) {
                estado.set(conteoEstado.getEstado(), conteoEstado.getConteo());
            }
            graficoBarra.addSeries(estado);    
                graficoBarra.setTitle("Conteo de estados");
                graficoBarra.setLegendPosition("ne");

                Axis xAxis = graficoBarra.getAxis(AxisType.X);
                xAxis.setLabel("Estado");

                Axis yAxis = graficoBarra.getAxis(AxisType.Y);
                yAxis.setLabel("Cantidad");
                yAxis.setMin(0);
    }
}
