package entidades;

import entidades.Documentos;
import entidades.Especialidad;
import entidades.Perfil;
import entidades.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(Aspirantes.class)
public class Aspirantes_ { 

    public static volatile SingularAttribute<Aspirantes, String> estado;
    public static volatile SingularAttribute<Aspirantes, Documentos> idDeDocumento;
    public static volatile SingularAttribute<Aspirantes, String> hojavida;
    public static volatile SingularAttribute<Aspirantes, Especialidad> idEspecializacion;
    public static volatile SingularAttribute<Aspirantes, Perfil> idPerfil;
    public static volatile SingularAttribute<Aspirantes, Long> numerodocumento;
    public static volatile SingularAttribute<Aspirantes, Date> horainicial;
    public static volatile SingularAttribute<Aspirantes, Date> horafinal;
    public static volatile SingularAttribute<Aspirantes, Usuarios> usuarios;
    public static volatile SingularAttribute<Aspirantes, String> diashabiles;

}