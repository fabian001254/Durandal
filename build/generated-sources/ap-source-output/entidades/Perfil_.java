package entidades;

import entidades.Aspirantes;
import entidades.PruebasPsicotecnicas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-08T16:42:13")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile ListAttribute<Perfil, PruebasPsicotecnicas> pruebasPsicotecnicasList;
    public static volatile SingularAttribute<Perfil, Integer> idPerfil;
    public static volatile SingularAttribute<Perfil, String> pl;
    public static volatile SingularAttribute<Perfil, String> fichatecnica;
    public static volatile ListAttribute<Perfil, Aspirantes> aspirantesList;

}