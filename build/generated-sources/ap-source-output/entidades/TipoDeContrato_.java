package entidades;

import entidades.AparteContratacion;
import entidades.Ofertas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(TipoDeContrato.class)
public class TipoDeContrato_ { 

    public static volatile SingularAttribute<TipoDeContrato, Integer> idTipoContrato;
    public static volatile ListAttribute<TipoDeContrato, Ofertas> ofertasList;
    public static volatile ListAttribute<TipoDeContrato, AparteContratacion> aparteContratacionList;
    public static volatile SingularAttribute<TipoDeContrato, String> tipocontrato;

}