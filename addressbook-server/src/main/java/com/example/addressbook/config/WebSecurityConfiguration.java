package com.example.addressbook.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * Web security configuration.
 */
@Profile("!test")
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .cors()
            .and()
            .authorizeRequests().anyRequest().authenticated();

        http
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

    /**
     * Configurer for CORS filter in OAuth2 resource server.
     *
     * @return the {@link ResourceServerConfigurer}
     */
    @Bean
    public ResourceServerConfigurer resourceServerConfigurer(ResourceServerProperties resourceServerProperties) {
        return new CorsResourceServerConfigurer(resourceServerProperties);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Configures CORS filter for OAuth2 resource server.
     */
    public static class CorsResourceServerConfigurer implements ResourceServerConfigurer {

        private final ResourceServerProperties resourceServerProperties;

        /**
         * Constructor.
         *
         * @param resourceServerProperties properties for resource server
         */
        public CorsResourceServerConfigurer(ResourceServerProperties resourceServerProperties) {
            this.resourceServerProperties = resourceServerProperties;
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(resourceServerProperties.getResourceId());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .cors()
                    .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and()
                    .authorizeRequests().anyRequest().authenticated();
        }
    }
}
