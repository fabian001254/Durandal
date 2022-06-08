function validarUsuario(){
    var email = document.getElementById("textemail").value;
    var clave = document.getElementById("textpassword").value;
    if(email === "gestionhumana@gmail.com" && clave === "clave1234"){
        window.location.href = "gestion_humana.html";
        return false;
    }

    if(email === "cliente@gmail.com" && clave === "clave1234"){
        window.location.href = "cliente.html";
        return false;
    }
    
    if(email === "aspirante@gmail.com" && clave === "clave1234"){
        window.location.href = " personal-reclutado.html "; /* personal-reclutado.html personal_no_reclutado.html */
        return false;
    }
    if(email === "gestioncomercial@gmail.com" && clave === "clave1234"){
        window.location.href = "gestion_comercial.html";
        return false;
    }
    else{
        Swal.fire({
            icon: 'error',
            title: 'Credenciales de acceso',
            text: 'Acceso denegado, ingrese correctamente el correo y/o password',
            showConfirmButton: true,
            timer: 2500
        });
        return false;
    }

}