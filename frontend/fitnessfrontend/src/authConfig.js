export const authConfig = {
  clientId: 'oauth-pkce-client',
  authorizationEndpoint: 'http://localhost:9192/realms/fitness-app/protocol/openid-connect/auth',
  tokenEndpoint: 'http://localhost:9192/realms/fitness-app/protocol/openid-connect/token',
  redirectUri: 'http://localhost:5173/',
  scope: 'openid profile email offline_access',
  onRefreshTokenExpire: (event) => event.logIn(),
}
