package ua.shop.kdu.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.shop.kdu.entities.Role;
import ua.shop.kdu.filters.JWTAuthenticationFilter;
import ua.shop.kdu.filters.JWTAuthorizationFilter;
import ua.shop.kdu.services.UserService;

import static ua.shop.kdu.filters.SecurityConstants.SIGN_IN_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    private JWTAuthenticationFilter configuredJwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl(SIGN_IN_URL);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .csrf().disable()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/img/**").permitAll()
                        .antMatchers("/api/auth/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/categories", "/api/product/**", "/api/product" ).permitAll()
                        .antMatchers("/api/cart", "/api/cart/**").hasAuthority(Role.USER.getAuthority())
                        .antMatchers(HttpMethod.POST, "/api/orders").hasAuthority(Role.USER.getAuthority())
                        .antMatchers(HttpMethod.GET, "/api/orders**", "/api/orders/**").hasAuthority(Role.USER.getAuthority())
                        .antMatchers("/api/categories", "/api/categories/**", "/api/product/**", "/api/product**", "/api/orders**", "/api/orders/**", "/api/orders/all**").hasAnyAuthority(Role.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    .addFilter(configuredJwtAuthenticationFilter())
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
