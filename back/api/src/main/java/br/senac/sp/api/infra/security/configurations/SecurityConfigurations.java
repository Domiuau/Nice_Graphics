package br.senac.sp.api.infra.security.configurations;

import br.senac.sp.api.domain.user.UserRole;
import br.senac.sp.api.infra.security.services.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/user/analyze/gpt-4").hasRole("PREMIUM_USER")
                        .requestMatchers(HttpMethod.POST, "/user/analyze/gpt-4-turbo").hasRole("PREMIUM_USER")
                        .requestMatchers(HttpMethod.POST, "/user/analyze/gpt-4o").authenticated()
                        .requestMatchers(HttpMethod.POST, "/user/analyze/gpt-4o-mini").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/analyze/gpt-3.5").permitAll()

                        .requestMatchers(HttpMethod.POST, "/user/analyze/gemini-2.0-flash").authenticated()
                        .requestMatchers(HttpMethod.POST, "/user/analyze/gemini-1.5-pro").authenticated()
                        .requestMatchers(HttpMethod.POST, "/user/analyze/gemini-1.5-flash").permitAll()


                        .requestMatchers(HttpMethod.POST, "/user/analyze/deepseek-chat").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/analyze/deepseek-reasoner").hasRole("PREMIUM_USER")

                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/cadastrar").permitAll()

                        .requestMatchers(HttpMethod.GET, "/analyze/generations/previews").authenticated()
                        .requestMatchers(HttpMethod.GET, "/analyze/generations").authenticated()
                        .requestMatchers(HttpMethod.GET, "/analyze/generation/{id}").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public static AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
