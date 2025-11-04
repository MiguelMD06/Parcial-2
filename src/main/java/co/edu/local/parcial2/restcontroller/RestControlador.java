package co.edu.local.parcial2.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.local.parcial2.dto.AsignaturaDTO;
import co.edu.local.parcial2.model.Asignatura;
import co.edu.local.parcial2.services.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/")
@Tag(name = "Asignaturas", description = "API para gestión de asignaturas")
public class RestControlador {

	//Asignaturas
	
	@Autowired
	private AsignaturaService asignaturaServicio;

	@GetMapping
	public List<Asignatura> listarAsignaturas() {
		return asignaturaServicio.listarAsignaturas();
	}

	@Operation(summary = "Actualizar una Asignatura", description = "Permite actualizar los datos de una asignatura existente en la base de datos mediante su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Asignatura actualizada exitosamente"),
			@ApiResponse(responseCode = "404", description = "Asignatura no encontrado"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida") })
	
	@GetMapping("/{id}")
	public Asignatura obtenerAsignatura(@PathVariable Long id) {
		return asignaturaServicio.buscarPorId(id);
	}
	
	
	@Operation(summary = "Crear una Asignatura", description = "Permite crear una asignatura en la base de datos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Asignatura creada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida") })
	@PostMapping
	public Asignatura crearAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
		return asignaturaServicio.save(asignaturaDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Asignatura> actualizarAsignatura(
			@Parameter(description = "ID de la asignatura que se va a actualizar", required = true) @PathVariable Long id,
			@Parameter(description = "Detalles actualizados de la asignatura") @RequestBody AsignaturaDTO asignaturaDTO) {
			
		Optional<Asignatura> asignaturaExiste = asignaturaServicio.findById(id);
		
		if (asignaturaExiste.isPresent()) {
			asignaturaServicio.actualizarAsignatura(asignaturaDTO);
			Asignatura asignaturaActualizada = asignaturaServicio.buscarPorId(id);
			return ResponseEntity.ok(asignaturaActualizada);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Eliminar una asignatura", description = "Permite eliminar una asignatura existente en la base de datos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Asignatura eliminada exitosamente"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida") })
	@DeleteMapping("/{id}")
	public void eliminarAsignatura(@PathVariable Long id) {
		asignaturaServicio.eliminarAsignatura(id);
	}
}
