package entidades;

import entidades.Aspirantes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(Entrevista.class)
public class Entrevista_ { 

    public static volatile SingularAttribute<Entrevista, Date> fecha;
    public static volatile SingularAttribute<Entrevista, Date> hora;
    public static volatile SingularAttribute<Entrevista, Aspirantes> aspirantes;
    public static volatile SingularAttribute<Entrevista, Long> documento;

}