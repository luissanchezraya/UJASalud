package daw.app.model.dao;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import daw.app.model.Usuario;
import daw.app.qualifiers.DAOJpa;

import java.io.Serializable;
import java.util.List;

@Dependent
@DAOJpa
@Transactional
public class UsuarioDAOJPA implements UsuarioDAO, Serializable {

    @PersistenceContext(unitName = "UJASaludPU")
    private EntityManager em;

    @Override
    public List<Usuario> buscaTodos() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    @Override
    public Usuario buscaUsuarioPorNombre(String usuario) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario", Usuario.class)
                    .setParameter("usuario", usuario)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuario> buscaUsuariosPorSubcadena(String subcadena) {
        String query = "SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE :subcadena";
        return em.createQuery(query, Usuario.class)
                .setParameter("subcadena", "%" + subcadena.toLowerCase() + "%")
                .getResultList();
    }

    @Override
    public void agrega(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public void elimina(String usuario) {
        Usuario u = buscaUsuarioPorNombre(usuario);
        if (u != null) {
            em.remove(u);
        }
    }

    @Override
    public void actualizarUsuario(String usuario, Usuario usuarioActualizado) {
        Usuario u = buscaUsuarioPorNombre(usuario);
        if (u != null) {
            u.setUsuario(usuarioActualizado.getUsuario());
            u.setNombre(usuarioActualizado.getNombre());
            u.setEmail(usuarioActualizado.getEmail());
            u.setDni(usuarioActualizado.getDni());
            u.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            u.setNumeroDeTarjeta(usuarioActualizado.getNumeroDeTarjeta());
            u.setTelefono(usuarioActualizado.getTelefono());
            u.setDireccion(usuarioActualizado.getDireccion());
            u.setRol(usuarioActualizado.getRol());
            em.merge(u);
        }
    }
}

