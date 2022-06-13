package entidades;

import entidades.Aspirantes;
import entidades.Documentos;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:27:05")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> apellido2;
    public static volatile SingularAttribute<Usuarios, String> clave;
    public static volatile SingularAttribute<Usuarios, String> apellido1;
    public static volatile SingularAttribute<Usuarios, String> direccion;
    public static volatile SingularAttribute<Usuarios, Long> numerodocumento;
    public static volatile SingularAttribute<Usuarios, String> nombre2;
    public static volatile SingularAttribute<Usuarios, String> nombre1;
    public static volatile SingularAttribute<Usuarios, String> rol;
    public static volatile SingularAttribute<Usuarios, Documentos> idDeDocumento;
    public static volatile SingularAttribute<Usuarios, String> foto;
    public static volatile SingularAttribute<Usuarios, Date> fechanacimiento;
    public static volatile SingularAttribute<Usuarios, String> correo;
    public static volatile SingularAttribute<Usuarios, Aspirantes> aspirantes;
    public static volatile SingularAttribute<Usuarios, BigInteger> telefono;

}