package entidades;

import entidades.AparteContratacion;
import entidades.InscripcionesOfertas;
import entidades.TipoDeContrato;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(Ofertas.class)
public class Ofertas_ { 

    public static volatile SingularAttribute<Ofertas, String> descripcion;
    public static volatile SingularAttribute<Ofertas, AparteContratacion> idApartes;
    public static volatile SingularAttribute<Ofertas, Integer> idOfertas;
    public static volatile SingularAttribute<Ofertas, String> estado;
    public static volatile SingularAttribute<Ofertas, String> requerimientos;
    public static volatile SingularAttribute<Ofertas, Integer> sueldo;
    public static volatile SingularAttribute<Ofertas, Integer> cantvacantes;
    public static volatile SingularAttribute<Ofertas, Date> fechafinal;
    public static volatile SingularAttribute<Ofertas, Date> fechadepublicacion;
    public static volatile SingularAttribute<Ofertas, Date> fechainicio;
    public static volatile SingularAttribute<Ofertas, TipoDeContrato> idTipoContrato;
    public static volatile SingularAttribute<Ofertas, String> pl;
    public static volatile ListAttribute<Ofertas, InscripcionesOfertas> inscripcionesOfertasList;
    public static volatile SingularAttribute<Ofertas, String> tiempoexperiencia;

}