FROM quay.io/keycloak/keycloak:16.0.0

ARG AUTHENTICATOR_JAR=target/OOPLabs2022Spring.jar

# copy the jars ...
COPY ${AUTHENTICATOR_JAR} /opt/jboss/keycloak/standalone/deployments/