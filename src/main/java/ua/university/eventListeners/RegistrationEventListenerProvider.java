package ua.university.eventListeners;

import org.jboss.resteasy.spi.HttpRequest;
import org.keycloak.authorization.authorization.AuthorizationTokenService;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.*;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class RegistrationEventListenerProvider  implements EventListenerProvider {
    private final KeycloakSession session;
    private final RealmProvider model;

    public RegistrationEventListenerProvider(KeycloakSession session) {
        this.session = session;
        this.model = session.realms();
    }

    @Override
    public void onEvent(Event event) {

        if (EventType.REGISTER.equals(event.getType())) {
            RealmModel realm = this.model.getRealm(event.getRealmId());
            UserModel newRegisteredUser = this.session.users().getUserById(event.getUserId(), realm);

            org.jboss.resteasy.spi.HttpRequest req = session.getContext().getContextObject(HttpRequest.class);
            MultivaluedMap<String, String> formParameters = req.getFormParameters();

            String ourRole = formParameters.get("role").toString();

            if (Objects.equals(ourRole, "[student]")) {
                RoleModel roleModel = realm.getClientById(realm.getClientByClientId("Faculty").getId()).getRole("student");
                System.out.println("Our role model: " + roleModel.getName());
                newRegisteredUser.grantRole(roleModel);

            }

            if (Objects.equals(ourRole, "[teacher]")) {
                RoleModel roleModel = realm.getClientById(realm.getClientByClientId("Faculty").getId()).getRole("teacher");
                System.out.println("Our role model: " + roleModel.getName());
                newRegisteredUser.grantRole(roleModel);
            }

            System.out.println("Hello, am I alive? Am I? (•_•) ( •_•)>⌐■-■ (⌐■_■) -> " + newRegisteredUser.getUsername());
        }

    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {

    }

    @Override
    public void close() {

    }
}