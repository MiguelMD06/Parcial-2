package co.edu.local.parcial2.dto;

import java.util.Collection;

import co.edu.local.parcial2.model.Rol;
import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nombre;
	private String contrasena;
	private Long rol;
	
	public UsuarioDTO(String nombre, String contrasena, Long rol) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	public UsuarioDTO(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	
	
	
	
}
