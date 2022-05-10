package ua.university.eventListeners;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jboss.resteasy.spi.HttpRequest;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.*;

import javax.ws.rs.core.MultivaluedMap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegistrationEventListenerProvider implements EventListenerProvider {
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

    public void sendPost(String path, String[] args) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://127.0.0.1/" + path + "/");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("name", args[1]));
        params.add(new BasicNameValuePair("...", args[2]));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

////Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        System.out.println(response.toString());
//        HttpEntity entity = response.getEntity();
//
//        if (entity != null) {
//            try (InputStream instream = entity.getContent()) {
//                // do something useful
//            }
//        }
    }
}