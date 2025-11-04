package co.edu.local.parcial2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.local.parcial2.dto.AsignaturaDTO;
import co.edu.local.parcial2.model.Asignatura;
import co.edu.local.parcial2.model.Usuarios;
import co.edu.local.parcial2.repository.AsignaturaRepository;
import co.edu.local.parcial2.repository.UsuarioRepository;

@Service
public class AsignaturaServiceImp implements AsignaturaService{

	@Autowired
	AsignaturaRepository asignaturaRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public Asignatura buscarPorId(Long id) {
		Optional<Asignatura> asignatura =  asignaturaRepository.findById(id);
		
		if (asignatura.isPresent())
			return asignatura.get();
		
		return new Asignatura();
	}
	
	@Override
	public Optional<Asignatura> findById(Long id) {
		return asignaturaRepository.findById(id);
	}
	
	@Override
	public boolean validarAsignatura(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = asignaturaRepository.findByNombre(asignaturaDTO.getNombre());
		
		if (asignatura == null) 
			return true;
		return false;
	}
	
	@Override
	public Asignatura save(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = new Asignatura(asignaturaDTO.getNombre(), asignaturaDTO.getDescripcion(), asignaturaDTO.getSalon(), asignaturaDTO.getHComienzo(), asignaturaDTO.getHFinal(), asignaturaDTO.getDocente());
		return asignaturaRepository.save(asignatura);
	}
	
	@Override
	public List<Asignatura> listarAsignaturas() {
		return asignaturaRepository.findAll();
	}
	
	@Override
	public void actualizarAsignatura(AsignaturaDTO asignaturaDTO) {
		Usuarios usuario = usuarioRepository.findByNombre(asignaturaDTO.getDocente().getNombre()); 
		Asignatura asignatura = asignaturaRepository.findById(asignaturaDTO.getId()).orElseThrow(() -> new RuntimeException("Asignatura no encontrado"));
		asignatura.setNombre(asignaturaDTO.getNombre());
		asignatura.setDescripcion(asignaturaDTO.getDescripcion());
		asignatura.setSalon(asignaturaDTO.getSalon());
		asignatura.setHComienzo(asignaturaDTO.getHComienzo());
		asignatura.setHFinal(asignaturaDTO.getHFinal());
		asignatura.setDocente(asignaturaDTO.getDocente());
		asignaturaRepository.save(asignatura);
	}
	
	@Override
	public void actualizarDocente(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = asignaturaRepository.findById(asignaturaDTO.getId()).orElseThrow(() -> new RuntimeException("Asignatura no encontrado"));
		asignatura.setHComienzo(asignaturaDTO.getHComienzo());
		asignatura.setHFinal(asignaturaDTO.getHFinal());
		asignaturaRepository.save(asignatura);
	}
	
	@Override
	public void eliminarAsignatura(Long id) {
		asignaturaRepository.deleteById(id);	
	}
	
	@Override
	public boolean validarActualizar(AsignaturaDTO asignaturaDTO) {
		Asignatura asignatura = asignaturaRepository.findById(asignaturaDTO.getId()).orElseThrow(() -> new RuntimeException("Asignatura no encontrado"));
		
		if (asignatura.getNombre().equals(asignaturaDTO.getNombre()))
			return true;
		
		return false;
	}
	
}
