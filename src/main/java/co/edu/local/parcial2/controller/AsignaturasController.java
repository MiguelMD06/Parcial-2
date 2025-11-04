package co.edu.local.parcial2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.local.parcial2.dto.AsignaturaDTO;
import co.edu.local.parcial2.services.AsignaturaService;
import co.edu.local.parcial2.services.UsuarioService;

@Controller
@RequestMapping("/rector")
public class AsignaturasController {

	
	@Autowired
	private AsignaturaService asignaturaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios/listar")
	public String mostrarListaUsuarios(Model modelo) {
		modelo.addAttribute("usuarios", usuarioService.listarUsuarios());
		return "listaUsuarios";
	}
	
	
	@ModelAttribute("asignatura")
	public AsignaturaDTO NuevaAsignatura() {
		return new AsignaturaDTO();
	}
	
	@GetMapping("/asignaturas/registro")
	public String mostrarRegistro(Model model) {
		model.addAttribute("asignatura", new AsignaturaDTO());
		model.addAttribute("docentes", usuarioService.listarDocentes());
		return "registroAsignaturas";
	}
	
	@PostMapping("/asignaturas/registro")
	public String registrarUsuario(@ModelAttribute("asignatura") AsignaturaDTO asignaturaDTO) {
		if (asignaturaService.validarAsignatura(asignaturaDTO)) {
			asignaturaService.save(asignaturaDTO);
			return "redirect:/rector/asignaturas/registro?exito";
		}else {
			return "redirect:/rector/asignaturas/registro?error";
		}
	}
	
	@GetMapping("/asignaturas/modificar")
	public String mostrarActualizar(Model modelo) {
		modelo.addAttribute("asignatura", new AsignaturaDTO());
		modelo.addAttribute("asignaturas", asignaturaService.listarAsignaturas());
		modelo.addAttribute("docentes", usuarioService.listarDocentes());
		return "actualizarAsignaturas";
	}
	
	@PostMapping("/asignaturas/modificar")
	public String actualizarAsignatura(@ModelAttribute("asignatura") AsignaturaDTO asignaturaDTO) {
		if (asignaturaService.validarAsignatura(asignaturaDTO)) {
			asignaturaService.actualizarAsignatura(asignaturaDTO);
			return "redirect:/rector/asignaturas/modificar?exito";
		}else {
			if(asignaturaService.validarActualizar(asignaturaDTO)) {
				asignaturaService.actualizarAsignatura(asignaturaDTO);
				return "redirect:/rector/asignaturas/modificar?exito";
			}
		}
		return "redirect:/rector/asignaturas/modificar?error";
	}
	
	
	
}
