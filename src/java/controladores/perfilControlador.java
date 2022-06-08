/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Perfil;
import facades.PerfilFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author facat
 */
@Named(value = "perfilControlador")
@SessionScoped
public class perfilControlador implements Serializable {

    //Variables
    public perfilControlador() {
    }
    Part fichatecnica;
    String pathReal,pathr,nombre;

    public Part getFichatecnica() {
        return fichatecnica;
    }

    public void setFichatecnica(Part fichatecnica) {
        this.fichatecnica = fichatecnica;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public String getPathr() {
        return pathr;
    }

    public void setPathr(String pathr) {
        this.pathr = pathr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    @EJB
    private PerfilFacade perfilfacade;
    
    private Perfil perfil;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    @PostConstruct
    public void init(){
        perfil = new Perfil();
        createBarModels();
    }
    
    
    //Consultas
    public List<Perfil> listarPerfiles(){
        return perfilfacade.findAll();
    }

    
    //Funciones
    
    public void upload() { //Subida de el archivo perfil
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
            path = path.substring(0, path.indexOf("\\build"));
            path = path + "\\web\\Archivos\\Perfil\\";
            pathReal = path + perfil.getPl() +"/";
            File directorio = new File(pathReal);
            directorio.mkdirs();
        try {
            //Perfil
            nombre = fichatecnica.getSubmittedFileName();
            String tipo= FilenameUtils.getExtension(nombre);
            String archi = "pdf";
                if(fichatecnica != null && tipo.equals(archi)){
                    nombre = "Perfil"+ perfil.getPl()+"."+tipo;
                    pathReal = "/SubirArchivo/Archivos/Perfil/"+perfil.getPl()+"/"+ nombre;
                    String path1 = path +perfil.getPl()+"/" + nombre;
                    pathr = "../Archivos/Perfil"+perfil.getPl()+"/" + nombre;
                    InputStream in = fichatecnica.getInputStream();

                    byte[] data = new byte[in.available()];
                    in.read(data);
                    File archivo=new File(path1);
                    FileOutputStream out = new FileOutputStream(archivo);
                    out.write(data);
                    in.close();
                    out.close();
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Perfil "+perfil.getPl()+" debe contener un archivo en formato PDF otro no son aceptados"));
                }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    public void crear(){
        try {
            Perfil per = perfilfacade.consulta(perfil.getPl());
            if(per != null){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ya existe el perfil "+perfil.getPl()));
            }else{
                upload();
                perfil.getPl();
                perfil.setFichatecnica(pathr);
                perfilfacade.create(perfil);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Perfil "+perfil.getPl()+" guardado con exito"));
                perfil.setPl("");
                perfil.setFichatecnica("");
            }
            
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    
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
            List<Perfil> cont = perfilfacade.consulta1(); 
            for (Perfil conteoperfil : cont) {
                estado.set(conteoperfil.getPl(), conteoperfil.getIdPerfil());
            }
            graficoBarra.addSeries(estado);    
                graficoBarra.setTitle("Conteo de perfiles");
                graficoBarra.setLegendPosition("ne");

                Axis xAxis = graficoBarra.getAxis(AxisType.X);
                xAxis.setLabel("Perfil");

                Axis yAxis = graficoBarra.getAxis(AxisType.Y);
                yAxis.setLabel("Cantidad");
                yAxis.setMin(0);
    }
}
