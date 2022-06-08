/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controladores;

import entidades.Campaña;
import entidades.ClientesPotenciales;
import entidades.Documentos;
import entidades.ListaMarketing;
import entidades.Mailers;
import entidades.TipoDeRedSocial;
import facades.CampañaFacade;
import facades.ClientesPotencialesFacade;
import facades.DocumentosFacade;
import facades.ListaMarketingFacade;
import facades.TipoDeRedSocialFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
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
@Named(value = "campañaControlador")
@SessionScoped
public class CampañaControlador implements Serializable {

    /**
     * Creates a new instance of CampañaControlador
     */
    public CampañaControlador() {
    }
    ClientesPotenciales clientesp;
    Campaña campaña;
    ListaMarketing listas;
    TipoDeRedSocial tpred;
    Documentos docs;
    LocalDate fi,ff;
    String asun,cont;
    int idl;

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
    
    public LocalDate getFi() {
        return fi;
    }

    public void setFi(LocalDate fi) {
        this.fi = fi;
    }

    public LocalDate getFf() {
        return ff;
    }

    public void setFf(LocalDate ff) {
        this.ff = ff;
    }
    
    public ClientesPotenciales getClientesp() {
        return clientesp;
    }

    public void setClientesp(ClientesPotenciales clientesp) {
        this.clientesp = clientesp;
    }

    public Campaña getCampaña() {
        return campaña;
    }

    public void setCampaña(Campaña campaña) {
        this.campaña = campaña;
    }

    public ListaMarketing getListas() {
        return listas;
    }

    public void setListas(ListaMarketing listas) {
        this.listas = listas;
    }

    public TipoDeRedSocial getTpred() {
        return tpred;
    }

    public void setTpred(TipoDeRedSocial tpred) {
        this.tpred = tpred;
    }

    public Documentos getDocs() {
        return docs;
    }

    public void setDocs(Documentos docs) {
        this.docs = docs;
    }
    
    @PostConstruct
    public void init(){
        clientesp = new ClientesPotenciales();
        campaña = new Campaña();
        listas = new ListaMarketing();
        tpred = new TipoDeRedSocial();
        docs = new Documentos();
        createBarModels();
    }
    
    
    @EJB
    ClientesPotencialesFacade clientespfacade;
    @EJB
    CampañaFacade campañafacade;
    @EJB
    ListaMarketingFacade listasfacade;
    @EJB
    TipoDeRedSocialFacade tpredfacade;
    @EJB
    DocumentosFacade tpdoc;
    
    //Consultas
    public List<TipoDeRedSocial> listadored(){
        return tpredfacade.findAll();
    }
    public List<Campaña> listarcampañas(){
        return campañafacade.findAll();
    }
    public void buscarInfo(int id){
        campaña = campañafacade.find(id);
    }
    List<ClientesPotenciales> listaclientes = null;    
    public void irlista(int id){
        try {
            idl=id;
            listas = listasfacade.Comprobar(campañafacade.find(id));
            if(listas != null){
                listaclientes=clientespfacade.consulta(listas);
                FacesContext.getCurrentInstance().getExternalContext().redirect("consultar_clientes_potenciales.xhtml");
            }else{
                listas.setIdDeLista(id);
            }
        } catch (IOException e) {
            System.out.println("Error: "+e);
        }
    }
    public void irlista2(){
        listas = listasfacade.Comprobar(campañafacade.find(idl));
        if(listas != null){
            listaclientes=clientespfacade.consulta(listas);
        }
    }
    public List<ClientesPotenciales> listarCl(){        
        return listaclientes;
    }
    public List<Documentos> listartp(){
        return tpdoc.findAll();
    }
    public void buscarInfoC(Long doc){
        clientesp = clientespfacade.find(doc);
    }
    //Funciones
    public void crear(){
        try {            
            campaña.setFechainicial(Date.valueOf(fi));
            campaña.setFechafinal(Date.valueOf(ff));
            campaña.setIdTpRedSocial(tpred);
            Campaña campañaaux = campañafacade.Comprobar1(campaña.getNombrecampaña());
            if(campañaaux != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se puede crear una campaña con el mismo nombre"));
            }else{
                campañafacade.create(campaña);
                listas.setIdDeCampaña(campaña);
                listas.setTitulo("Lista de la campaña "+campaña.getNombrecampaña());
                listasfacade.create(listas);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Campaña creada con exito!, el nombre de la lista de marketing es: "+listas.getTitulo()));
            }
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }    
    public void operar(int camp,int op){
        listas = listasfacade.Comprobar(campañafacade.find(camp));
        if(op == 1){            
            Campaña campañaaux = campañafacade.Comprobar(campaña.getNombrecampaña(),campaña.getIdDeCampaña());
            if(campañaaux != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se puede crear una campaña con el mismo nombre"));
            }else{
                campañafacade.edit(campaña);
                listas.setIdDeCampaña(campaña);                
                listas.setTitulo("Lista de la campaña "+campaña.getNombrecampaña());                
                listasfacade.edit(listas);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Campaña ha actualizado con exito!, el nombre de la lista de marketing es: "+listas.getTitulo()));
            }
        }else if(op == 2){
            List<ClientesPotenciales> listarP = clientespfacade.consulta(listas);
            for(int i = 0; i<listarP.size();i++){
                clientesp = clientespfacade.find(listarP.get(i).getNumerodocumento());
                clientespfacade.remove(clientesp);
            }
            listasfacade.remove(listas);
            campañafacade.remove(campaña);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Campaña ha eliminado exito!"));
        }
    }
           
    public void crearP(){
        clientesp.setIdDeLista(listas);
        clientesp.setIdDeDocumento(docs);
        clientespfacade.create(clientesp);
        irlista2();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado el cliente potencial"));
    }
        
    public void operar2(int op,Long doc){
        if(op==1){
            clientespfacade.edit(clientesp);
            irlista2();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha modificado con exito"));
        }else if(op == 2){
            buscarInfoC(doc);
            clientespfacade.remove(clientesp);
            irlista2();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha eliminado con exito"));
        }
    }
    //Correos
    public void enviarContenido(){
        try {            
            List<String> lista = clientespfacade.consultaC();
            Mailers.send(lista, asun,mensajeConEstilo());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha enviado con exito el correo"));
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
            estado.setLabel("Listas");
            List<ListaMarketing> cont = listasfacade.consultacant(); 
            for (ListaMarketing conteolista : cont) {
                estado.set(conteolista.getTitulo(), conteolista.getIdDeLista());
            }
            graficoBarra.addSeries(estado);    
                graficoBarra.setTitle("Conteo clientes potenciales inscritos a listas");
                graficoBarra.setLegendPosition("ne");

                Axis xAxis = graficoBarra.getAxis(AxisType.X);
                xAxis.setLabel("Lista");

                Axis yAxis = graficoBarra.getAxis(AxisType.Y);
                yAxis.setLabel("Cantidad de clientes");
                yAxis.setMin(0);
    }
}
