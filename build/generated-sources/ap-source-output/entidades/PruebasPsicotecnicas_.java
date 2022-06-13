package entidades;

import entidades.Aspirantes;
import entidades.Perfil;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(PruebasPsicotecnicas.class)
public class PruebasPsicotecnicas_ { 

    public static volatile SingularAttribute<PruebasPsicotecnicas, Date> tiempolimite;
    public static volatile SingularAttribute<PruebasPsicotecnicas, Date> horaenvio;
    public static volatile SingularAttribute<PruebasPsicotecnicas, Perfil> idPerfil;
    public static volatile SingularAttribute<PruebasPsicotecnicas, String> pathPrueba;
    public static volatile SingularAttribute<PruebasPsicotecnicas, Integer> idPrueba;
    public static volatile SingularAttribute<PruebasPsicotecnicas, String> pathPruebaEntregada;
    public static volatile SingularAttribute<PruebasPsicotecnicas, Aspirantes> numerodocumento;

}