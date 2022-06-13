package entidades;

import entidades.Aspirantes;
import entidades.Ofertas;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(InscripcionesOfertas.class)
public class InscripcionesOfertas_ { 

    public static volatile SingularAttribute<InscripcionesOfertas, Integer> idIncripcion;
    public static volatile SingularAttribute<InscripcionesOfertas, Ofertas> idOferta;
    public static volatile SingularAttribute<InscripcionesOfertas, Aspirantes> numeroDocumento;

}