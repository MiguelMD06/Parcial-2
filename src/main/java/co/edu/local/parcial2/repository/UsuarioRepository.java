package co.edu.local.parcial2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.local.parcial2.model.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{
	
	public Usuarios findByNombre(String nombre);

}
