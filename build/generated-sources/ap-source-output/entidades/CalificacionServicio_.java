package entidades;

import entidades.Aspirantes;
import entidades.Clientes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-08T16:42:13")
@StaticMetamodel(CalificacionServicio.class)
public class CalificacionServicio_ { 

    public static volatile SingularAttribute<CalificacionServicio, Date> fechainicio;
    public static volatile SingularAttribute<CalificacionServicio, Integer> puntualidad;
    public static volatile SingularAttribute<CalificacionServicio, Integer> responsabilidad;
    public static volatile SingularAttribute<CalificacionServicio, String> estado;
    public static volatile SingularAttribute<CalificacionServicio, Integer> calitrabajo;
    public static volatile SingularAttribute<CalificacionServicio, Integer> actiipersonal;
    public static volatile SingularAttribute<CalificacionServicio, Integer> promedio;
    public static volatile SingularAttribute<CalificacionServicio, Integer> idCuestionario;
    public static volatile SingularAttribute<CalificacionServicio, Aspirantes> numerodocumentoaspirante;
    public static volatile SingularAttribute<CalificacionServicio, Clientes> numerodocumentocliente;
    public static volatile SingularAttribute<CalificacionServicio, Date> fechafinal;

}