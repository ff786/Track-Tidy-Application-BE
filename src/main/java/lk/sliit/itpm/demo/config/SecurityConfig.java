package lk.sliit.itpm.demo.config;

import lk.sliit.itpm.demo.filter.JWTAuthenticationFilter;
import lk.sliit.itpm.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/error").permitAll()
                                .requestMatchers(HttpMethod.POST,"/user/register").permitAll()
                                .requestMatchers(HttpMethod.POST,"/user/request/token").permitAll()
                                .requestMatchers(HttpMethod.GET,"/user/getUsers").permitAll()
                                .requestMatchers(HttpMethod.GET,"/admin/getAll").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/user/delete/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST,"/otp/request").permitAll() //Complete User
                                .requestMatchers(HttpMethod.POST,"/inventory/create").permitAll()
                                .requestMatchers(HttpMethod.GET,"/inventory/getAll").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/inventory/update/{id}").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/inventory/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/inventory/delete/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/inventory/delete").permitAll() //Complete Inventory
                                .requestMatchers(HttpMethod.POST,"/service/create").permitAll()
                                .requestMatchers(HttpMethod.GET,"/service/getAll").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/service/update").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/service/update/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/service/delete").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/service/delete/{id}").permitAll() //Complete Service
                                .requestMatchers(HttpMethod.POST,"/grocery/create").permitAll()
                                .requestMatchers(HttpMethod.GET,"/grocery/getAll").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/grocery/update").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/grocery/update/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/grocery/delete").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/grocery/delete/{id}").permitAll()//Complete Grocery
                                .requestMatchers(HttpMethod.POST,"/package/create").permitAll()
                                .requestMatchers(HttpMethod.GET,"/package/getAll").permitAll()
                                .requestMatchers(HttpMethod.GET,"/spring-ai/prompt").permitAll() //Test Message
                                .requestMatchers(HttpMethod.GET,"/track-ai/tracktidy-package").permitAll()
                                .requestMatchers(HttpMethod.POST,"/email/send").permitAll()
                                .requestMatchers(HttpMethod.GET,"/track-ai/generate-package").permitAll()
                                .requestMatchers(HttpMethod.POST,"/track-ai/generate-package").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/auth/reset-password").permitAll() // New endpoint
                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
