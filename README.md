 ## Configuração do Keycloak

### Rodando

O servidor de autorização (Keycloak) e sua base de dados já estão prontos para rodar via Docker, bastando fazer:

```bash
docker compose up [-d]
```

O Keycloak é exposto na porta 8081 e o login do administrador é (para propósitos de teste local) `admin`, `admin`.

Para mais detalhes sobre ele, consulte a sua documentação.

### Configuração do cliente

Os arquivos `application-<environment>.properties` fazem referência ao realm `derco`. Este realm deve ser configurado e dentro dele deve ser criado um cliente.

Deve ser criado um cliente OpenID com a opção de acesso direto (_Direct access grants_) habilitadas para testes com o comando `curl` (ver abaixo).

## URLs de configuração

Após subir o Keycloak, confira em http://localhost:8081/realms/derco/.well-known/openid-configuration.

## Obter token

```bash
curl -iX POST http://localhost:8081/realms/<REALM>/protocol/openid-connect/token -d 'grant_type=password&username=<USUARIO>&password=<SENHA>&client_id=<CLIENT_ID>&client_secret=<CLIENT_SECRET>'
```

