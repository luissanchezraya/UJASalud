package daw.app.model.dao;

import daw.app.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    List<Usuario> buscaTodos();
    Usuario buscaUsuarioPorNombre(String usuario);
    void agrega(Usuario usuario);
    void elimina(String usuario);
    void actualizarUsuario(String usuario, Usuario usuarioActualizado);
    List<Usuario> buscaUsuariosPorSubcadena(String subcadena);
}
