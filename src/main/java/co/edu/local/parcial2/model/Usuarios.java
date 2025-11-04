package co.edu.local.parcial2.model;

import java.util.Collection;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "user_nombre"))
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(name="user_nombre")
	private String nombre;
	
	@NonNull
	@Column(name="user_contrasena")
	private String contrasena;
	
	@Nonnull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "usuarios_roles",
				joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
			)
	private Collection<Rol> rol;

	public Usuarios(String nombre, String contrasena, Collection<Rol> rol) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.rol = rol;
	}
	
	
	
	
}
