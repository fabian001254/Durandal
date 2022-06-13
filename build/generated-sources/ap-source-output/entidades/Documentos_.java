package entidades;

import entidades.Aspirantes;
import entidades.Clientes;
import entidades.ClientesPotenciales;
import entidades.GestionComercial;
import entidades.GestionHumana;
import entidades.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(Documentos.class)
public class Documentos_ { 

    public static volatile ListAttribute<Documentos, Usuarios> usuariosList;
    public static volatile SingularAttribute<Documentos, Integer> idDeDocumento;
    public static volatile ListAttribute<Documentos, GestionHumana> gestionHumanaList;
    public static volatile ListAttribute<Documentos, Clientes> clientesList;
    public static volatile ListAttribute<Documentos, GestionComercial> gestionComercialList;
    public static volatile ListAttribute<Documentos, ClientesPotenciales> clientesPotencialesList;
    public static volatile SingularAttribute<Documentos, String> tipodocumento;
    public static volatile ListAttribute<Documentos, Aspirantes> aspirantesList;

}