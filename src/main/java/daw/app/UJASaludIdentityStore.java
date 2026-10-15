package daw.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import daw.app.model.dao.UsuarioDAO;
import daw.app.model.Usuario;
import daw.app.qualifiers.DAOJpa;

import java.util.HashSet;
import java.util.Set;

import static daw.app.model.Usuario.Rol.ADMINISTRADOR;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static daw.app.model.Usuario.Rol.MEDICO;

@ApplicationScoped
public class UJASaludIdentityStore implements IdentityStore {

    @Inject
    //@DAOMap
    @DAOJpa
    private UsuarioDAO usuarioDAO;

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();

        Usuario usuario = usuarioDAO.buscaUsuarioPorNombre(username);

        if (usuario != null && usuario.getContrasena().equals(password)) {
            Set<String> roles = new HashSet<>();
            if (usuario.getRol() == MEDICO) {
                roles.add("MEDICOS");
            } else if (usuario.getRol() == ADMINISTRADOR){
                roles.add("ADMINISTRADORES");
            }else{
                roles.add("USUARIOS");
            }
            return new CredentialValidationResult(username, roles);
        }
        return INVALID_RESULT;
    }
}
