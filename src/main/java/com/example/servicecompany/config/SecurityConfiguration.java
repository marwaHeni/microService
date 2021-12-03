package com.example.servicecompany.config;

import com.example.servicecompany.security.AuthoritiesConstants;
import com.example.servicecompany.security.jwt.JWTConfigurer;
import com.example.servicecompany.security.jwt.TokenProvider;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
//import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    //private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(TokenProvider tokenProvider) {  //,, SecurityProblemSupport problemSupport
        this.tokenProvider = tokenProvider;
     //   this.problemSupport = problemSupport;
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/h2-console/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .exceptionHandling()
//                .authenticationEntryPoint(problemSupport)
//                .accessDeniedHandler(problemSupport)
        .and()
            .headers()
            .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
        .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
        .and()
            .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
        .and()
            .frameOptions()
            .deny()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/createSchema").permitAll()
            //.antMatchers("/api/image/**").authenticated()
            .antMatchers("/api/publication-Categories/**").permitAll()
            .antMatchers("/api/publications/**").permitAll()
            .antMatchers("/api/sauveteurs/**").permitAll()
            .antMatchers("/api/publication-images/**").permitAll()
            .antMatchers("/api/publication-videos/**").permitAll()
            .antMatchers("/api/sauvees/**").permitAll()
            .antMatchers("/api/bateaux/**").permitAll()
            .antMatchers("/api/publication-sub-categories/**").permitAll()
//            .antMatchers("/api/dashboard/**").hasAuthority(AuthoritiesConstants.DASHBOARD)
//            .antMatchers("/api/journals/**").hasAuthority(AuthoritiesConstants.DASHBOARD)
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/prometheus").permitAll()
            .antMatchers("http://localhost:8080/actuator/httptrace").hasAuthority(AuthoritiesConstants.Admin)
        .and()
            .apply(securityConfigurerAdapter());
        // @formatter:on
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
