package entidades;

import entidades.Documentos;
import entidades.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(GestionComercial.class)
public class GestionComercial_ { 

    public static volatile SingularAttribute<GestionComercial, Documentos> idDeDocumento;
    public static volatile SingularAttribute<GestionComercial, Long> numerodocumento;
    public static volatile SingularAttribute<GestionComercial, String> cargo;
    public static volatile SingularAttribute<GestionComercial, Usuarios> usuarios;

}