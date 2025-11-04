package co.edu.local.parcial2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.local.parcial2.dto.AsignaturaDTO;
import co.edu.local.parcial2.dto.UsuarioDTO;
import co.edu.local.parcial2.model.Asignatura;
import co.edu.local.parcial2.services.AsignaturaService;
import co.edu.local.parcial2.services.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioServicio;
	
	@Autowired
	private AsignaturaService asignaturaServicio;

	@GetMapping("/")
	public String motrarInicio() {
		return "index";
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}
	
	
	@ModelAttribute("usuario")
	public UsuarioDTO NuevoUsuario() {
		return new UsuarioDTO();
	}
	
	@GetMapping("/registro")
	public String mostrarRegistro(Model model) {
		model.addAttribute("usuario", new UsuarioDTO());
		return "registro";
	}
	
	@PostMapping("/registro")
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
		if (usuarioServicio.validarUsername(usuarioDTO)) {
			usuarioServicio.save(usuarioDTO);
			return "redirect:/registro?exito";
		}else {
			return "redirect:/registro?error";
		}
	}
	
	
	@GetMapping("/rector/usuarios/registro")
	public String mostrarRegistrarAdminUsuario(Model modelo) {
		modelo.addAttribute("usuario", new UsuarioDTO());
		return "registroAdmin";
	}
	
	@PostMapping("/rector/usuarios/registro")
	public String registrarAdminUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
		if (usuarioServicio.validarUsername(usuarioDTO)) {
			usuarioServicio.saveAdmin(usuarioDTO);
			return "redirect:/rector/usuarios/registro?exito";
		}else {
			return "redirect:/rector/usuarios/registro?error";
		}
	}
	
	@GetMapping("/asignaturas/listar")
	public String mostrarLista(Model modelo) {
		modelo.addAttribute("asignaturas", asignaturaServicio.listarAsignaturas());
		return "listaAsignaturas";
	}
	
	//DOCENTE
	
	@GetMapping("/asignaturas/docente/listar")
	public String mostrarDocenteLista(Model modelo, Authentication authentication) {
		String nombreUsuario = authentication.getName(); 

	    List<Asignatura> asignaturas = asignaturaServicio.listarAsignaturas()
	            .stream()
	            .filter(a -> a.getDocente() != null &&
	                         a.getDocente().getNombre().equals(nombreUsuario))
	            .toList();
		modelo.addAttribute("asignaturas", asignaturas);
		return "listaAsignaturas";
	}
	
	@GetMapping("/asignaturas/docente/modificar")
	public String mostrarDocenteActualizar(Model modelo, Authentication authentication) {
		String nombreUsuario = authentication.getName(); 

	    List<Asignatura> asignaturas = asignaturaServicio.listarAsignaturas()
	            .stream()
	            .filter(a -> a.getDocente() != null &&
	                         a.getDocente().getNombre().equals(nombreUsuario))
	            .toList();
		modelo.addAttribute("asignaturas", asignaturas);
		modelo.addAttribute("asignatura", new AsignaturaDTO());
		return "actualizarAsigsDocente";
	}
	
	@PostMapping("/asignaturas/docente/modificar")
	public String actualizarDocenteAsignatura(@ModelAttribute("asignatura") AsignaturaDTO asignaturaDTO) {
		asignaturaServicio.actualizarDocente(asignaturaDTO);
		return "redirect:/asignaturas/docente/modificar?exito";
	}
	
	@GetMapping("/asignaturas/listar/{id}")
	public String eliminarUsuario(@PathVariable Long id) {
		asignaturaServicio.eliminarAsignatura(id);
		return "redirect:/asignaturas/listar?eliminado";
	}
	
}
