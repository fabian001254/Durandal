package entidades;

import entidades.Aspirantes;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-08T16:42:13")
@StaticMetamodel(Especialidad.class)
public class Especialidad_ { 

    public static volatile SingularAttribute<Especialidad, Integer> idEspecialidad;
    public static volatile SingularAttribute<Especialidad, String> nombreespecialidad;
    public static volatile ListAttribute<Especialidad, Aspirantes> aspirantesList;

}