package entidades;

import entidades.CalificacionServicio;
import entidades.Documentos;
import entidades.InformeDeContratacion;
import entidades.Usuarios;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile ListAttribute<Clientes, CalificacionServicio> calificacionServicioList;
    public static volatile SingularAttribute<Clientes, Documentos> idDeDocumento;
    public static volatile SingularAttribute<Clientes, String> razonsocial;
    public static volatile SingularAttribute<Clientes, String> correo;
    public static volatile SingularAttribute<Clientes, Integer> nit;
    public static volatile SingularAttribute<Clientes, Long> numerodocumento;
    public static volatile SingularAttribute<Clientes, String> objetosocial;
    public static volatile SingularAttribute<Clientes, BigInteger> telefono;
    public static volatile SingularAttribute<Clientes, Usuarios> usuarios;
    public static volatile ListAttribute<Clientes, InformeDeContratacion> informeDeContratacionList;

}