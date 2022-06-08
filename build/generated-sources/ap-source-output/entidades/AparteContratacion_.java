package entidades;

import entidades.Clientes;
import entidades.Ofertas;
import entidades.TipoDeContrato;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-08T16:42:13")
@StaticMetamodel(AparteContratacion.class)
public class AparteContratacion_ { 

    public static volatile SingularAttribute<AparteContratacion, Integer> idApartes;
    public static volatile SingularAttribute<AparteContratacion, String> descripcion;
    public static volatile SingularAttribute<AparteContratacion, Date> diafinal;
    public static volatile SingularAttribute<AparteContratacion, String> estado;
    public static volatile ListAttribute<AparteContratacion, Ofertas> ofertasList;
    public static volatile SingularAttribute<AparteContratacion, Integer> sueldo;
    public static volatile SingularAttribute<AparteContratacion, Integer> cantpersonal;
    public static volatile SingularAttribute<AparteContratacion, TipoDeContrato> idTipoContrato;
    public static volatile SingularAttribute<AparteContratacion, Date> diainicial;
    public static volatile SingularAttribute<AparteContratacion, String> requrimientos;
    public static volatile SingularAttribute<AparteContratacion, String> pl;
    public static volatile SingularAttribute<AparteContratacion, Clientes> numerodocumentocliente;
    public static volatile SingularAttribute<AparteContratacion, String> tiempoexperiencia;

}