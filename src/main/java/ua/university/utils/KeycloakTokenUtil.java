package ua.university.utils;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class KeycloakTokenUtil {

    public static String getPreferredUsername(HttpServletRequest httpServletRequest){
        KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) httpServletRequest.getUserPrincipal();
        AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();
        return accessToken.getPreferredUsername();

    }

    public static Set<String> getRoles(HttpServletRequest httpServletRequest){
        KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) httpServletRequest.getUserPrincipal();
        Set<String> roles = keycloakPrincipal.getKeycloakSecurityContext()
                .getToken().getResourceAccess("Faculty").getRoles();
        return roles;

    }
}
