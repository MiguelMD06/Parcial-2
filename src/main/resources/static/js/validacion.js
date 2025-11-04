function validarAlfanumerico() {

	let texto = document.getElementById("nombre");

    const regex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 ]+$/;
    return regex.test(texto.value);
}

function mostrarErrorNombre() {
    const alerta = document.getElementById("nombreHelpBlock");

    alerta.classList.remove("d-none"); 
	alerta.style.color = "red";
}

document.getElementById("miFormulario").addEventListener("submit", function(event) {
		
	if (!validarAlfanumerico()){
			mostrarErrorNombre();
	      event.preventDefault(); 
		 }

	  });
	