/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Aspirantes;
import entidades.Perfil;
import entidades.PruebasPsicotecnicas;
import entidades.Usuarios;
import facades.AspirantesFacade;
import facades.PerfilFacade;
import facades.PruebasPsicotecnicasFacade;
import facades.UsuariosFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author facat
 */
@Named(value = "psicotecnicasControlador")
@SessionScoped
public class psicotecnicasControlador implements Serializable {


    //Variables
    public psicotecnicasControlador() {
    }
    
    private int numerodocumento,id_perfil;
    private LocalDate horamax;

    public LocalDate getHoramax() {
        return horamax;
    }

    public void setHoramax(LocalDate horamax) {
        this.horamax = horamax;
    }

    public int getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(int numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    private Usuarios usuarios;
    private Perfil perfil;
    private Aspirantes aspirantes;
    private PruebasPsicotecnicas pruebas;
    @EJB
    private UsuariosFacade usuariosfacade;
    @EJB
    private PerfilFacade perfilfacade;
    @EJB
    private AspirantesFacade aspirantesfacade;
    @EJB
    private PruebasPsicotecnicasFacade psicotecnicasfacade;

    public Aspirantes getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }
    
    public PruebasPsicotecnicas getPruebas() {
        return pruebas;
    }

    public void setPruebas(PruebasPsicotecnicas pruebas) {
        this.pruebas = pruebas;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    @PostConstruct
    public void init() {
        usuarios = new Usuarios();
        pruebas = new PruebasPsicotecnicas();
        perfil = new Perfil();
        aspirantes = new Aspirantes();
    }
    
    //Consultas
    public List<Aspirantes> consultar(){
        return (List<Aspirantes>) aspirantesfacade.consultaAspirantesE();
    }
    public List<Perfil> consultarP(){
        return perfilfacade.findAll();
    }    
    public String consultaEntrega(Long doc){
        pruebas = psicotecnicasfacade.Comprobar(aspirantesfacade.find(doc));
        return pruebas.getPathPrueba();
    }
    
    public void comprobarEnvio(Long doc) throws IOException{
        aspirantes = aspirantesfacade.find(doc);
            pruebas = psicotecnicasfacade.Comprobar(aspirantes);
            if(pruebas.getPathPruebaEntregada() == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("personal_no_reclutado1.xhtml");                
            }
            pruebas = new PruebasPsicotecnicas();
            aspirantes = new Aspirantes();
    }
    
    String ruta;
    
    public String consultarPrueba(Long doc) throws IOException{       
        aspirantes = aspirantesfacade.find(doc);
        pruebas = psicotecnicasfacade.Comprobar(aspirantes);
            if(pruebas.getPathPruebaEntregada() == null){
               ruta = "";
            }else{
                ruta = pruebas.getPathPruebaEntregada();
            }
            pruebas = new PruebasPsicotecnicas();
            aspirantes = new Aspirantes();
            return ruta;
    }
    
    //Funciones
    public void crear(){
        pruebas = new PruebasPsicotecnicas();
        pruebas.setNumerodocumento(aspirantesfacade.find(aspirantes.getNumerodocumento()));
        pruebas.setIdPerfil(perfilfacade.find(perfil.getIdPerfil()));//lo mismo del objeto
        Date hora = Date.valueOf(horamax);
        pruebas.setTiempolimite(hora);
        psicotecnicasfacade.create(pruebas);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha creado con éxito la prueba"));
        Aspirantes asp = aspirantesfacade.find(aspirantesfacade.find(aspirantes.getNumerodocumento()));
        asp.setEstado("En espera");
        aspirantesfacade.edit(asp);
    }
    
    //Subida de prueba
    
        public void subirPrueba(Long doc){
            aspirantes = aspirantesfacade.find(doc);
            pruebas = psicotecnicasfacade.Comprobar(aspirantes);
            if(pruebas.getPathPruebaEntregada() == null){
                 upload();
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La prueba psicotécnica se ha subido con exito"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ya ha subido una prueba psicotécnica y no es remplazable"));

            }
           
        }
    
    
        Part prueba;
        String pathReal,pathr,nombre;

    public Part getPrueba() {
        return prueba;
    }

    public void setPrueba(Part prueba) {
        this.prueba = prueba;
    }
    
     public void upload() { 
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
            path = path.substring(0, path.indexOf("\\build"));
            path = path + "\\web\\Archivos\\PruebasPsicotecnicas\\";
            pathReal = path + aspirantes.getNumerodocumento() +"/";
            File directorio = new File(pathReal);
            directorio.mkdirs();
        try {
            //Perfil
            nombre = prueba.getSubmittedFileName();
            String tipo= FilenameUtils.getExtension(nombre);
            String archi = "pdf";
                if(prueba != null && tipo.equals(archi)){
                    nombre = "Psicotecnica"+ aspirantes.getNumerodocumento()+"."+tipo;
                    pathReal = "/SubirArchivo/Archivos/Perfil/"+aspirantes.getNumerodocumento()+"/"+ nombre;
                    String path1 = path +aspirantes.getNumerodocumento()+"/" + nombre;
                    pathr = "../Archivos/PruebasPsicotecnicas/"+aspirantes.getNumerodocumento()+"/" + nombre;
                    InputStream in = prueba.getInputStream();

                    byte[] data = new byte[in.available()];
                    in.read(data);
                    File archivo=new File(path1);
                    FileOutputStream out = new FileOutputStream(archivo);
                    out.write(data);
                    in.close();
                    out.close();
                    
                   pruebas.setPathPruebaEntregada(pathr);
                   psicotecnicasfacade.edit(pruebas);
                   
                   pruebas = new PruebasPsicotecnicas();
                   aspirantes = new Aspirantes();
                    
                    
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "La prueba psicotécnica debe contener un archivo en formato PDF otros no son aceptados"));
                }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
}