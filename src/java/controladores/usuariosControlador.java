/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Aspirantes;
import entidades.Clientes;
import entidades.Documentos;
import entidades.Especialidad;
import entidades.GestionComercial;
import entidades.GestionHumana;
import entidades.Mailer;
import entidades.Mailers;
import entidades.Perfil;
import entidades.Usuarios;
import facades.AspirantesFacade;
import facades.ClientesFacade;
import facades.DocumentosFacade;
import facades.EspecialidadFacade;
import facades.GestionComercialFacade;
import facades.GestionHumanaFacade;
import facades.PerfilFacade;
import facades.UsuariosFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
@Named(value = "usuariosControlador")
@SessionScoped
public class usuariosControlador implements Serializable {

    //Variables
    public usuariosControlador() {
    }    
    private Part hv;
    private String contraseña,correo;
    private Aspirantes aspirantes;
    private Usuarios usuarios;
    private Clientes clientes;
    private Documentos documentos;
    private Especialidad especialidad;
    private Perfil perfil;
    private GestionHumana gestionh;
    private GestionComercial gestionc;
    private String pathReal,pathr,nombre;
    String asun,cont;
    
    public String getAsun() {
        return asun;
    }

    public void setAsun(String asun) {
        this.asun = asun;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
    public Part getHv() {
        return hv;
    }

    public void setHv(Part hv) {
        this.hv = hv;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Aspirantes getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }

    public Documentos getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documentos documentos) {
        this.documentos = documentos;
    }
    
    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    

    @EJB
    private UsuariosFacade usuariosfacade;
    @EJB
    private DocumentosFacade documentosfacade;
    @EJB
    private ClientesFacade clientesfacade;
    @EJB
    private DocumentosFacade docfacade;
    @EJB
    private AspirantesFacade aspfacade;
    @EJB
    private EspecialidadFacade espfacade;
    @EJB
    private PerfilFacade perfilfacade;
    @EJB
    private GestionHumanaFacade ghfacade;
    @EJB
    private GestionComercialFacade gcfacade;
    @PostConstruct
    public void init(){
        usuarios = new Usuarios();
        clientes = new Clientes();
        documentos = new Documentos();
        aspirantes = new Aspirantes();
        especialidad = new Especialidad();
        perfil = new Perfil();
        asp = new Aspirantes();
        gestionh = new GestionHumana();
        gestionc = new GestionComercial();
    }
    
    //Consultas
    public List<Documentos> mostrarDocumentos(){
        return documentosfacade.findAll();
    }
    public List<Especialidad>listarEspecilidad(){
        return espfacade.findAll();
    }
    public List<Perfil> consultarP(){
        return perfilfacade.findAll();
    }
    public void comprobarSoli(Long doc){
        try {
            String soli = aspfacade.find(doc).getEstado();
            if(soli.equals("En espera") || soli.equals("Rechazado") || soli.equals("Citado a entrevista")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("personal_no_reclutado1.xhtml");
            }              
        } catch (IOException e) {
            System.out.println("Error "+e);
        }     
    }
    public String BuscarNombre(Long doc){
        return usuariosfacade.find(doc).getNombre1();
    }
    public String BuscarApellido(Long doc){
         return usuariosfacade.find(doc).getApellido1();
    }
    public String BuscarHV(Long doc){
        return aspfacade.find(doc).getHojavida();
    }
    public BigInteger BuscarNum(Long doc){
        return usuariosfacade.find(doc).getTelefono();
    }
     public void buscarInfo(Long doc1){
        aspirantes = aspfacade.find(doc1);
        usuarios = usuariosfacade.find(doc1);
    }
    //Funciones
    public void login(){
        try {
            Usuarios usuarioLogin= null;
            usuarioLogin=usuariosfacade.consulta(correo,contraseña);
        if(usuarioLogin!=null){//Validar si se logeo correctamente
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usuarioLogin);
            switch(usuarioLogin.getRol()){
                       case "Gh": 
                           FacesContext.getCurrentInstance().getExternalContext().redirect("gestion_humana.xhtml");                        
                           break;
                       case "Gc":
                           FacesContext.getCurrentInstance().getExternalContext().redirect("gestion_comercial.xhtml");
                           break;
                       case "As":
                           FacesContext.getCurrentInstance().getExternalContext().redirect("personal-reclutado.xhtml");
                           break;
                       case "Cl":
                           FacesContext.getCurrentInstance().getExternalContext().redirect("cliente.xhtml");                   
                           break;
                       case "asn":
                           FacesContext.getCurrentInstance().getExternalContext().redirect("personal_no_reclutado.xhtml");
                           break;
                   }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Nombre de Usuario o Contraseña no válidos"));
        }
        }catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error en acceso a Base de Datos"));
        }
    }
    
    public void validarSesion(){
        try {
            Usuarios usuariologeado = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");          
            if(usuariologeado == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("error404.xhtml");
            }
        } catch (IOException e) {    
            System.out.println("Error "+e);
        }
    }
    
    public void cerrarSesion(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error en direccionamiento"));
        }
    }
    public Usuarios usuarioLogeado(){
        Usuarios usuarioLogeado=(Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return usuarioLogeado;
    }
    
    public void comprobar(String pg){
        try {
            String rol = usuarioLogeado().getRol();
            if(!pg.equals(rol)){
            FacesContext.getCurrentInstance().getExternalContext().redirect("error404.xhtml");
            }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    
    public void registrar(){
        try {
            Usuarios usu = usuariosfacade.Comprobar(usuarios.getCorreo(), usuarios.getNumerodocumento());
            Clientes cl = clientesfacade.comprobar(clientes.getNit());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dia = formatter.format(usuarios.getFechanacimiento());  
            LocalDate dian = LocalDate.parse(dia);
            LocalDate hoy = LocalDate.now();
            Period periodo = Period.between(dian,hoy);
            if(usu!=null || cl!=null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error ya existe un usuario con esta información"));
            }else{
                if(periodo.getYears() >= 18){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario creado con exito, bienevenid@ "+usuarios.getNombre1()+" "+usuarios.getApellido1()));
                    usuarios.setRol("Cl");
                    usuarios.setIdDeDocumento(docfacade.find(documentos.getIdDeDocumento()));
                    usuariosfacade.create(usuarios);
                    clientes.setCorreo(usuarios.getCorreo());
                    clientes.setIdDeDocumento(usuarios.getIdDeDocumento());
                    clientes.setNumerodocumento(usuarios.getNumerodocumento());
                    clientes.setTelefono(usuarios.getTelefono());
                    clientesfacade.create(clientes);                
                    enviarCorreoCliente();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Error debe ser mayor de edad para poderse registrar"));
                }                
            }
            usuarios = new Usuarios();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    
     public void registrarAs(){
        try {            
            Usuarios usu = usuariosfacade.Comprobar(usuarios.getCorreo(), usuarios.getNumerodocumento());            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dia = formatter.format(usuarios.getFechanacimiento());  
            LocalDate dian = LocalDate.parse(dia);
            LocalDate hoy = LocalDate.now();
            Period periodo = Period.between(dian,hoy);
            if(usu!=null ){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error ya existe un usuario con esta información"));
            }else{
                if(periodo.getYears() >= 18){
                    nombre = hv.getSubmittedFileName();
                    String tipo= FilenameUtils.getExtension(nombre);
                    String archi = "pdf";
                    if(hv != null && tipo.equals(archi)){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario creado con exito, bienevenid@ "+usuarios.getNombre1()+" "+usuarios.getApellido1()));
                        usuarios.setRol("asn");
                        usuarios.setIdDeDocumento(docfacade.find(documentos.getIdDeDocumento()));
                        usuariosfacade.create(usuarios);//abajo cambia
                        aspirantes.setNumerodocumento(usuarios.getNumerodocumento());
                        aspirantes.setIdDeDocumento(documentosfacade.find(documentos.getIdDeDocumento()));
                        aspirantes.setIdPerfil(perfilfacade.find(perfil.getIdPerfil()));
                        String perfill = perfilfacade.find(perfil.getIdPerfil()).getPl();
                        String especi = espfacade.find(especialidad.getIdEspecialidad()).getNombreespecialidad();
                        aspirantes.setIdEspecializacion(espfacade.find(especialidad.getIdEspecialidad()));
                        aspirantes.setEstado("Registrado");
                        upload();
                        aspirantes.setHojavida(pathr);
                        aspfacade.create(aspirantes);
                        enviarCorreoAs(perfill,especi);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                    }else{
                         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Error ingrese su hoja de vida en formato PDF"));
                    }
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Error debe ser mayor de edad para poderse registrar"));
                }
                
                usuarios = new Usuarios();
            }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    public void solicitud(){
        try {
            Aspirantes aspi;
            aspi = aspfacade.find(usuarioLogeado().getNumerodocumento());
            aspi.setEstado("En espera");
            aspfacade.edit(aspi);
            enviarCorreoSoli(usuarioLogeado().getCorreo(),usuarioLogeado().getNombre1(),usuarioLogeado().getApellido2());
            FacesContext.getCurrentInstance().getExternalContext().redirect("personal_no_reclutado1.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Ha solicitado con éxito su reclutamiento"));
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    public void actualizarhv(){
        usuarios = usuariosfacade.find(usuarioLogeado().getNumerodocumento());
        aspirantes = aspfacade.find(usuarioLogeado().getNumerodocumento());
        upload();
        aspirantes.setHojavida(pathr);
        aspfacade.edit(aspirantes);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado la hoja de vida con éxito"));
    } 
    
    public String pathFoto(Long doc){
        String foto;
        foto = usuariosfacade.find(doc).getFoto();
        if(foto == null){
            foto = "assets/img/persona.png";
        }
        return foto;
    }
    
     //----restauracion
    String correo1,nombre1,apellido2;
    LocalDate fechanacimiento;
    Long documento;
    String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getCorreo1() {
        return correo1;
    }

    public void setCorreo1(String correo1) {
        this.correo1 = correo1;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }
    
    public void restaurar(){
        Date fechan = Date.valueOf(fechanacimiento);
        Usuarios usurestauracion = usuariosfacade.recuperar(correo1, documento, fechan, nombre1, apellido2);
        if(usurestauracion != null){
            enviarCorreoRestauracion(usurestauracion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha enviado el correo de la su clave"));
            correo1 = "";
            documento = null;
            nombre1 = "";
            apellido2 = "";            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Su usuario no se puede restablecer parámetros incorrectos"));
        }
    }
    public void enviarCorreoRestauracion(Usuarios usurestauracion){
             try {
                 String asunto = "Restauración de contraseña";
                 String destinatario = usurestauracion.getCorreo();
                 Mailer.send(destinatario,asunto,mensajeConEstiloR(usurestauracion)); 
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
     public String mensajeConEstiloR(Usuarios usurestauracion){
               return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr@: " +usurestauracion.getNombre1()+" "+ usurestauracion.getApellido1()+"</p>\n"                
                    + "<br>Usted solicito la restauración de su contraseña el día " + LocalDate.now() + ""
                    + "<br><p style='font-weigth:bold'>Su contraseña es: "+usurestauracion.getClave() +"</p>"
                    +" <br>Si no lo solicito haga caso omiso al correo de restauración"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";

    }
    
    //--------------
     
     
       // subida de documentos
     public void upload() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\Documentos\\";
        try {            
            pathReal = path + usuarios.getNumerodocumento() +"/";
            File directorio = new File(pathReal);
            directorio.mkdirs();            
            //Hoja_de_vida
            nombre = hv.getSubmittedFileName();
            String tipo= FilenameUtils.getExtension(nombre);
            nombre = "Hoja_de_vida-"+ usuarios.getNumerodocumento()+"."+tipo;
            pathReal = "/DurandalS/Archivos/Documentos"+usuarios.getNumerodocumento()+"/"+ nombre;
            String path1 = path +usuarios.getNumerodocumento()+"/" + nombre;
            pathr = "../Archivos/Documentos/"+usuarios.getNumerodocumento()+"/" + nombre;
            InputStream in = hv.getInputStream();
            byte[] data = new byte[in.available()];
            in.read(data);
            File archivo=new File(path1);
            FileOutputStream out = new FileOutputStream(archivo);
            out.write(data);
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }

   
    
    //Correos
    public void enviarCorreoAs(String perfill, String especi){
             try {
                 String asunto = "Registro Confirmado";
                 String destinatario = usuarios.getCorreo();
                 Mailer.send(destinatario,asunto,mensajeConEstiloRegistroAspirante(perfill,especi)); 
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
    public void enviarCorreoCliente(){
        try {
            String asunto = "Registro Confirmado";
            String destinatario = usuarios.getCorreo();
            Mailer.send(destinatario,asunto,mensajeConEstiloRegistroCliente()); 
        }catch (UnsupportedEncodingException e) {
            System.out.println("No se envio correo error: "+e);
        }
    }
    
    public String mensajeConEstiloRegistroCliente(){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> SU USUARIO ES:  \n"
                    + "<p> Nombre 1: "+usuarios.getNombre1()+ "\n"
                    + "<p> Nombre 2: "+usuarios.getNombre2()+ "\n"
                    + "<p> Apellido 1: "+usuarios.getApellido1()+ "\n"
                    + "<p> Apellido 2: "+usuarios.getApellido2()+ "\n"
                    + "<p> Correo: "+usuarios.getCorreo()+ "\n"
                    + "<p> Rol al que se registro: Cliente \n"
                    + "<p> Nit de la empresa: "+clientes.getNit()+ "\n"
                    + "<p> Objeto social de la empresa: "+clientes.getObjetosocial()+ "\n"
                    + "<p> Razón social de la empresa: "+clientes.getRazonsocial()+ "\n"
                    + "</p> \n"
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad :3 </p> ";
    }
    
    

    public String mensajeConEstiloRegistroAspirante(String perfill, String especi){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> SU USUARIO ES:  \n"
                    + "<p> Nombre 1: "+usuarios.getNombre1()+ "\n"
                    + "<p> Nombre 2: "+usuarios.getNombre2()+ "\n"
                    + "<p> Apellido 1: "+usuarios.getApellido1()+ "\n"
                    + "<p> Apellido 2: "+usuarios.getApellido2()+ "\n"
                    + "<p> Correo: "+usuarios.getCorreo()+ "\n" //abajo cambia wait
                    + "<p> Rol al que se registro: Aspirante \n"
                    + "<p> Su perfil es: "+perfill+ "\n"
                    + "<p> Su especialidad es: "+especi+ "\n"
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad :3 </p> ";
    }

    public String mensajeConEstiloSoli(String nombre1,String nombre2){
               return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> Sr(a): "+nombre1+" "+nombre2
                    + "<p> Usted se solicito el reclutamiento por parte de opta y su estado actual es EN ESPERA \n"
                    + "<p> ¿Qué quiere decir este estado?: "
                    +"<p> El estado EN ESPERA es cuando OPTA revisara su hoja de vida y determinara si es apto para ser reclutado, de ser así se enviarán las respectivas pruebas psicotécnicas, de otro modo, será rechazado dando así por finalizado el proceso"
                    +"<p style=\"color: #green; font-\"¡Le deseamos la mejor de las suertes en este proceso! "
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad :3 </p> ";

    }
    public void enviarCorreoSoli(String correo,String nombre1,String nombre2){
             try {
                 String asunto = "Solicitud enviada con éxito";
                 String destinatario = correo;
                 Mailer.send(destinatario,asunto,mensajeConEstiloSoli(nombre1,nombre2)); 
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
    
   public void enviarMantenimiento(){
        try {            
            List<String> lista = usuariosfacade.listarUsuarios();
            Mailers.send(lista, asun,mensajeConEstilo());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha enviado con éxito el correo"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error "+e);
        }
    }
   
    public String mensajeConEstilo(){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> " +cont +" </p>\n"                
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";
    }    
    
    public void enviarPin(String correo,String nombre,String apellido){
             try {
                 String asunto = "Cambio de correo";
                 String destinatario = correo;
                 Mailer.send(destinatario,asunto,mensajeConEstiloPin(nombre,apellido)); 
        } catch (UnsupportedEncodingException e) {
                 System.out.println("No se envio correo error: "+e);
        }
    }
    public String mensajeConEstiloPin(String nombre, String apellido){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\"></p>\n"
                    + "<p>Sr(a): "+nombre+" "+apellido+"</p>\n"
                    + "<p> El pin para cambio de correo es: </p><h1 style=\"font-weight: bold; \">" +pin +"</h1>\n<p> si no deseo cambiar el correo haga caso omiso a este correo </p>\n"                
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad</p> ";
    } 
    
    
    
    public void enviarCorreoCreacionU(){
        try {
            String asunto = "Registro Confirmado";
            String destinatario = usuarios.getCorreo();
            String rol;
            if(usuarios.getRol().equals("Gh")){
                rol = "Gestion Humana";
            }else{
                rol = "Gestion Comercial";
            }
            Mailer.send(destinatario,asunto,mensajeConEstiloRegistroU(cargo,rol)); 
        }catch (UnsupportedEncodingException e) {
            System.out.println("No se envio correo error: "+e);
        }
    }
    
        public String mensajeConEstiloRegistroU(String cargo,String rol){
        return "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Opta/Durandal" + "</h1>" + "<img src='https://i.ibb.co/3cwRywd/imagen.png'/ style=\"float: left;width: 450px; height:300px;\"><p>"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "<p> SU USUARIO ES:  \n"
                    + "<p> Nombre 1: "+usuarios.getNombre1()+ "\n"
                    + "<p> Nombre 2: "+usuarios.getNombre2()+ "\n"
                    + "<p> Apellido 1: "+usuarios.getApellido1()+ "\n"
                    + "<p> Apellido 2: "+usuarios.getApellido2()+ "\n"
                    + "<p> Correo: "+usuarios.getCorreo()+ "\n" 
                     + "<p> Contraseña: "+usuarios.getClave()+ "\n"
                    + "<p> Rol al que se registro: "+rol +" \n"
                    + "<p> Su cargo es: "+cargo+ "\n"
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad :3 </p> ";
    }
        
        

    
    
    public void CrearPersonal(){
        usuarios.setIdDeDocumento(docfacade.find(documentos.getIdDeDocumento()));        
        Usuarios usu = usuariosfacade.find(usuarios.getNumerodocumento());
        Usuarios usu1 = usuariosfacade.Comprobar(usuarios.getCorreo(),usuarios.getNumerodocumento());
        enviarCorreoCreacionU();
        if(usu != null && usu1 != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No se puede crear este usuario, puesto que ya existe"));
        }else{
                    usuariosfacade.create(usuarios);
                    if(usuarios.getRol().equals("Gh")){
                        
                        gestionh.setCargo(cargo);
                        gestionh.setNumerodocumento(usuarios.getNumerodocumento());
                        gestionh.setIdDeDocumento(usuarios.getIdDeDocumento());
                        ghfacade.create(gestionh);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha creado con éxito el usuario de gestión humana"));

                    }else{
                        gestionc.setCargo(cargo); 
                        gestionc.setNumerodocumento(usuarios.getNumerodocumento());
                        gestionc.setIdDeDocumento(usuarios.getIdDeDocumento());
                        gcfacade.create(gestionc);

                    }
        }
        usuarios = new Usuarios();

    }
    
    //--------
    // Configuraciones de cuenta
    
    public void buscarUsuarioT(Long doc){
               usuarios = usuariosfacade.find(doc);
     }  
    
    public void buscarAspiranteB(Long doc){
               aspirantes = aspfacade.find(doc);
     }      
    

        //----------- Cambio de correo -------
           public void buscarUsuario(Long doc){
               usuarios = usuariosfacade.find(doc);
               cambioCorreo();
           }            
           String correoC;
           int pin;
            public String getCorreoC() {
                return correoC;
            }

            public void setCorreoC(String correoC) {
                this.correoC = correoC;
            }
           
            public void cambioCorreo(){
                Random rand = new Random();
                pin = 1000 + rand.nextInt((9999 - 1000) + 1);
                enviarPin(usuarios.getCorreo(),usuarios.getNombre1(),usuarios.getApellido1());                
            }
            int pin2;

            public int getPin2() {
                return pin2;
            }

            public void setPin2(int pin2) {
                this.pin2 = pin2;
            }
            
            public void comprobarPin(){
                if(pin == pin2){
                    usuarios.setCorreo(correoC);
                    usuariosfacade.edit(usuarios);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado el correo con éxito"));
                    pin = 0;
                    usuarios = new Usuarios();
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Pin erroneo"));
                }
            }
        //------- Cambio de clave --------
            String nuevaC;
            String oldC;
            public String getNuevaC() {
                return nuevaC;
            }

            public void setNuevaC(String nuevaC) {
                this.nuevaC = nuevaC;
            }

            public String getOldC() {
                return oldC;
            }

            public void setOldC(String oldC) {
                this.oldC = oldC;
            }
            
            public void CambioContraseña(){
                if(oldC.equals(usuarios.getClave())){
                    usuarios.setClave(nuevaC);
                    usuariosfacade.edit(usuarios);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado la contrseña con éxito"));
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "La contraseña no coincide con la actual, verifique y vuelva a intentarlo"));
                }
            }
            
            //------- Cambio de telefono ------
            BigInteger telefonoN;

            public BigInteger getTelefonoN() {
                return telefonoN;
            }

            public void setTelefonoN(BigInteger telefonoN) {
                this.telefonoN = telefonoN;
            }

            public void CambioTelefono(){
                usuarios.setTelefono(telefonoN);
                usuariosfacade.edit(usuarios);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado el telefono con éxito"));           
            }       
            
            
        //------- Cambio de direccion ------
            String direccionN;

            public String getDireccionN() {
                return direccionN;
            }

            public void setDireccionN(String direccionN) {
                this.direccionN = direccionN;
            }
            
            public void CambioDireccion(){
                usuarios.setDireccion(direccionN);
                usuariosfacade.edit(usuarios);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado la direccion con éxito"));
            }
      //------- Cambio de foto -------
            private Part foto;

            public Part getFoto() {
                return foto;
            }

            public void setFoto(Part foto) {
                this.foto = foto;
            }
            
            public void cambioFoto() {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
                path = path.substring(0, path.indexOf("\\build"));
                path = path + "\\web\\Archivos\\Fotos\\";
                try {           
                    nombre = foto.getSubmittedFileName();
                    String tipo= FilenameUtils.getExtension(nombre);
                    if(tipo.equals("jpg")){
                        pathReal = path + usuarios.getNumerodocumento() +"/";
                        File directorio = new File(pathReal);
                        directorio.mkdirs();            
                        nombre = "foto-"+ usuarios.getNumerodocumento()+"."+tipo;
                        pathReal = "/DurandalS/Archivos/Fotos"+usuarios.getNumerodocumento()+"/"+ nombre;
                        String path1 = path +usuarios.getNumerodocumento()+"/" + nombre;
                        pathr = "Archivos/Fotos/"+usuarios.getNumerodocumento()+"/" + nombre;
                        InputStream in = foto.getInputStream();
                        byte[] data = new byte[in.available()];
                        in.read(data);
                        File archivo=new File(path1);
                        FileOutputStream out = new FileOutputStream(archivo);
                        out.write(data);
                        in.close();
                        out.close();
                        usuarios.setFoto(pathr);
                        usuariosfacade.edit(usuarios);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado la foto con éxito, la foto puede tardar un momento en cargar, por favor espere"));
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Si han pasado más de 5 minutos y no ha cargado la foto subala nuevamente y recargue la página esto solucionara el error"));
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "La foto debe estar en formato JPG"));
                    }                 
                } catch (IOException e) {
                    System.out.println("Error "+e);
                }
            }

         // ------------ Cambiar dias habiles------------
            String dias;
            public String getDias(){
                return dias;
            }
            public void setDias(String dias){
                this.dias = dias;
            }
            public String buscarAspiranteT(Long doc){
                aspirantes = aspfacade.find(doc);
                return  aspfacade.find(doc).getDiashabiles();
            }  
            public void cambioDias(){
                aspirantes.setDiashabiles(dias);
                aspfacade.edit(aspirantes);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado los dias habiles con éxito"));                
            }
        // ------------ Cambiar horas habiles ------------                              
                
                public void buscarAspiranteHora(Long doc){
                    aspirantes = aspfacade.find(doc);
                }  

               Aspirantes asp;
               
                public Aspirantes getAsp() {
                    return asp;
                }

                public void setAsp(Aspirantes asp) {
                    this.asp = asp;
                }
               
                public void cambioHoras(){            
                    aspirantes.setHorainicial(asp.getHorainicial());
                    aspirantes.setHorafinal(asp.getHorafinal());
                    System.out.println(asp.getHorainicial());
                    System.out.println(asp.getHorafinal());
                    aspfacade.edit(aspirantes);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha actualizado las horas habiles con éxito"));
                    asp = new Aspirantes();
                }
}