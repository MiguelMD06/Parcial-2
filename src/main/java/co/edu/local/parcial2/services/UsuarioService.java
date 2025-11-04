package co.edu.local.parcial2.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import co.edu.local.parcial2.dto.UsuarioDTO;
import co.edu.local.parcial2.model.Usuarios;

public interface UsuarioService extends UserDetailsService{
	
	public Usuarios save(UsuarioDTO usuarioDTO);
	public Usuarios saveAdmin(UsuarioDTO usuarioDTO);
	public boolean validarUsername(UsuarioDTO usuarioDTO);
	public List<Usuarios> listarUsuarios();
	public List<Usuarios> listarDocentes();
}
