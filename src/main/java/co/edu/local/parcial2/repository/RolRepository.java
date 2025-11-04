package co.edu.local.parcial2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.local.parcial2.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{

	public Rol findByNombre(String nombre);
}
