package entidades;

import entidades.Aspirantes;
import entidades.Clientes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-08T16:42:13")
@StaticMetamodel(InformeDeContratacion.class)
public class InformeDeContratacion_ { 

    public static volatile SingularAttribute<InformeDeContratacion, Date> fechainicio;
    public static volatile SingularAttribute<InformeDeContratacion, Integer> idInforme;
    public static volatile SingularAttribute<InformeDeContratacion, Aspirantes> numerodocumentoaspirante;
    public static volatile SingularAttribute<InformeDeContratacion, Clientes> numerodocumentocliente;
    public static volatile SingularAttribute<InformeDeContratacion, Date> fechafinal;

}