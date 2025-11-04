package co.edu.local.parcial2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.local.parcial2.model.Asignatura;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long>{

	public Asignatura findByNombre(String nombre);
	
}
