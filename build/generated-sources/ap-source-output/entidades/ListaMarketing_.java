package entidades;

import entidades.Campaña;
import entidades.ClientesPotenciales;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-08T16:42:13")
@StaticMetamodel(ListaMarketing.class)
public class ListaMarketing_ { 

    public static volatile SingularAttribute<ListaMarketing, String> titulo;
    public static volatile SingularAttribute<ListaMarketing, Campaña> idDeCampaña;
    public static volatile ListAttribute<ListaMarketing, ClientesPotenciales> clientesPotencialesList;
    public static volatile SingularAttribute<ListaMarketing, Integer> idDeLista;

}