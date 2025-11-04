package co.edu.local.parcial2.model;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="asignatura")
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(name = "asig_nombre")
	private String nombre;
	
	@NonNull
	@Column(name = "asig_descripcion")
	private String descripcion;
	
	@NonNull
	@Column(name="asig_salon")
	private int salon;
	
	@NonNull
	@Column(name = "asig_h_comienzo")
	private int hComienzo;
	
	@NonNull
	@Column(name = "asig_h_final")
	private int hFinal;
	
	@Nonnull
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
    fetch = FetchType.EAGER)
	@JoinColumn(name = "docente_id")
	private Usuarios docente;

	public Asignatura(String nombre, String descripcion, int salon, int hComienzo, int hFinal, Usuarios docente) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.salon = salon;
		this.hComienzo = hComienzo;
		this.hFinal = hFinal;
		this.docente = docente;
	}
	
	
}
