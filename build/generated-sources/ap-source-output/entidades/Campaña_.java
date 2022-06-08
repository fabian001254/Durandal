package entidades;

import entidades.ListaMarketing;
import entidades.TipoDeRedSocial;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-08T16:42:13")
@StaticMetamodel(Campaña.class)
public class Campaña_ { 

    public static volatile ListAttribute<Campaña, ListaMarketing> listaMarketingList;
    public static volatile SingularAttribute<Campaña, String> nombrecampaña;
    public static volatile SingularAttribute<Campaña, Integer> idDeCampaña;
    public static volatile SingularAttribute<Campaña, Date> fechainicial;
    public static volatile SingularAttribute<Campaña, TipoDeRedSocial> idTpRedSocial;
    public static volatile SingularAttribute<Campaña, Date> fechafinal;

}