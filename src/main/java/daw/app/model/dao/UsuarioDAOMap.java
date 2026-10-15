package daw.app.model.dao;

import jakarta.enterprise.context.ApplicationScoped;
import daw.app.model.Usuario;
import daw.app.qualifiers.DAOMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class UsuarioDAOMap implements UsuarioDAO {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public List<Usuario> buscaTodos() {
        return usuarios;
    }

    @Override
    public Usuario buscaUsuarioPorNombre(String usuario) {
        return usuarios.stream()
                .filter(u -> u.getUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void agrega(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void elimina(String usuario) {
        usuarios.removeIf(u -> u.getUsuario().equals(usuario));
    }

    @Override
    public void actualizarUsuario(String usuario, Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(usuario)) {
                usuarios.set(i, usuarioActualizado);
                break;
            }
        }
    }

    @Override
    public List<Usuario> buscaUsuariosPorSubcadena(String subcadena) {
        return usuarios.stream()
                .filter(u -> u.getNombre().toLowerCase().contains(subcadena.toLowerCase()))
                .collect(Collectors.toList());
    }
}
