package technology.grameen.gaccounting.network;

import technology.grameen.gaccounting.projection.authserver.AdminAcessToken;

import java.util.List;

public interface HttpClient {

    AdminAcessToken getAdminAccessToken();

    List<Object> getRealmRoles(AdminAcessToken token);

    List<Object> getClientRoles(AdminAcessToken token);
}
