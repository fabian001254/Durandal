(function() {
  'use strict';
  window.addEventListener('load', function() {
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
          Swal.fire({
            icon: 'error',
            title: 'Datos no completados',
            text: 'Acceso concedido',
            showConfirmButton: true,
            timer: 2500
        });
        form.classList.add('was-validated');
        };
      },
      false);
    });
  }, false);
})();
