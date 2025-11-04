package co.edu.local.parcial2.dto;

import co.edu.local.parcial2.model.Usuarios;
import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {

	private Long id;
	private String nombre;
	private String descripcion;
	private int salon;
	private int hComienzo;
	private int hFinal;
	private Usuarios docente;
	
	public AsignaturaDTO(String nombre, String descripcion, int salon, int hComienzo, int hFinal, Usuarios docente) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.salon = salon;
		this.hComienzo = hComienzo;
		this.hFinal = hFinal;
		this.docente = docente;
	}

	public AsignaturaDTO(int hComienzo, int hFinal) {
		super();
		this.hComienzo = hComienzo;
		this.hFinal = hFinal;
	}
	
	
	
}
