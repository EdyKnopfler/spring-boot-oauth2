## Configuração do Keycloak

Os arquivos `application-<environment>.properties` fazem referência ao realm `derco`. Este realm deve ser configurado e dentro dele deve ser criado um cliente.

Foi criado um cliente OpenID com as opções habilitadas:

* Client Authentication: acesso não confidencial
* Standard flow: para acesso via _authorization code_ (interessante para frontend)
* Direct access grants: para acesso via credenciais (testar com o comando `curl` abaixo)

## URLs de configuração

Após subir o Keycloak, confira em http://localhost:8081/realms/derco/.well-known/openid-configuration.

## Obter token

```bash
curl -iX POST http://localhost:8081/realms/<REALM>/protocol/openid-connect/token -d 'grant_type=password&username=<USUARIO>&password=<SENHA>&client_id=<CLIENT_ID>&client_secret=<CLIENT_SECRET>'
```

