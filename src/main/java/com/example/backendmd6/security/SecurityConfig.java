package com.example.backendmd6.security;

import com.example.backendmd6.security.jwt.CustomAccessDeniedHandler;
import com.example.backendmd6.security.jwt.JwtAuthenticationFilter;
import com.example.backendmd6.security.jwt.RestAuthenticationEntryPoint;
import com.example.backendmd6.service.ProfileEnterpriseService;
import com.example.backendmd6.service.ProfileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public ProfileUserService userService() {
//        return new ProfileUserServiceImpl();
//    }
//    @Bean
//    public ProfileEnterpriseService enterpriseService(){
//        return new ProfileEnterpriseServiceImpl();
//    }
    @Autowired
    private ProfileUserService profileUserService;
    @Autowired
    private ProfileEnterpriseService profileEnterpriseService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(profileUserService).passwordEncoder(passwordEncoder());
    }
    @Autowired
    public void configureGlobalSecurity2(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(profileEnterpriseService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/recruitments/**","/api/cvs/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/admins/**", "/api/profileEnterprises/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/recruitments/**").access("hasRole('ROLE_ENTERPRISE')")
//                .antMatchers(HttpMethod.GET
//                        ).access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers(HttpMethod.DELETE, "/categories",
//                        "/typeOfQuestions",
//                        "/questions",
//                        "/hello").access("hasRole('ROLE_ADMIN')")
//                .antMatchers(HttpMethod.PUT, "/users")
//                .access("hasRole('ROLE_USER')")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}