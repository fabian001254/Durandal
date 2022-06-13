package entidades;

import entidades.Campaña;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(TipoDeRedSocial.class)
public class TipoDeRedSocial_ { 

    public static volatile SingularAttribute<TipoDeRedSocial, Integer> idTpRedSocial;
    public static volatile SingularAttribute<TipoDeRedSocial, String> tpredsocial;
    public static volatile ListAttribute<TipoDeRedSocial, Campaña> campañaList;

}