package entidades;

import entidades.Documentos;
import entidades.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(GestionHumana.class)
public class GestionHumana_ { 

    public static volatile SingularAttribute<GestionHumana, Documentos> idDeDocumento;
    public static volatile SingularAttribute<GestionHumana, Long> numerodocumento;
    public static volatile SingularAttribute<GestionHumana, String> cargo;
    public static volatile SingularAttribute<GestionHumana, Usuarios> usuarios;

}