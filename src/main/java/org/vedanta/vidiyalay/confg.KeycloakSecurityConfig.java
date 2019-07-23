/*
 *     Copyright (C) 2019  Vikas Kumar Verma
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.vedanta.vidiyalay;

import org.springframework.context.annotation.Configuration;
@Configuration
class KeycloakSecurityConfig {

}
//@Configuration
//@EnableWebSecurity
//class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
//
//    @Bean
//    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
//        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
//        mapper.setConvertToUpperCase(true);
//        return mapper;
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigur() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:8080",
//                                "https://vedanta-ui.herokuapp.com")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
////                        .allowCredentials(true)
//                ;
//            }
//        };
//    }
//    @Override
//    protected KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
//        final KeycloakAuthenticationProvider provider = super.keycloakAuthenticationProvider();
//        provider.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper());
//        return provider;
//    }
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(keycloakAuthenticationProvider());
//    }
//
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new NullAuthenticatedSessionStrategy();
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        super.configure(http);
//        http
//                .authorizeRequests()
//                .antMatchers("/api/*").hasAnyRole("ADMIN", "USER")
//                .anyRequest().permitAll();
//    }
//
//    @Bean
//    protected KeycloakConfigResolver keycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }
//
//    @Bean
//    public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
//            final KeycloakAuthenticationProcessingFilter filter) {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//        registrationBean.setEnabled(false);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
//            final KeycloakPreAuthActionsFilter filter) {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//        registrationBean.setEnabled(false);
//        return registrationBean;
//    }
//}
//
//@Component
//class AbstractLogoutHandler implements LogoutSuccessHandler {
//    @Autowired
//    private Utility utility;
//    @Override
//    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//
//        //perform other required operation
//        final String URL = "http://docker.for.mac.localhost:9001/auth/realms/vedanta-vidiyalay-realm/protocol/openid-connect/logout?redirect_uri="
//                +URLEncoder.encode(utility.getHostAndPortNameWithContextPath(), "UTF-8");
//        httpServletRequest.logout();
//        httpServletResponse.setStatus(HttpStatus.OK.value());
//        httpServletResponse.sendRedirect(URL);
//    }
//}

