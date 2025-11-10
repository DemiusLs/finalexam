package plants.finalexam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/plants/create", "/plants/*/edit").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST , "/plants/**").hasAuthority("ADMIN")
        .requestMatchers("/plant/index", "/plant/*").hasAnyAuthority("ADMIN", "USER")

        .requestMatchers("/benefits/create", "/benefits/*/edit").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST , "/benefits/**").hasAuthority("ADMIN")
        .requestMatchers("/benefits/index", "/benefits/*").hasAnyAuthority("ADMIN", "USER")

        .requestMatchers("/controindications/create", "/controindications/*/edit").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST , "/controindications/**").hasAuthority("ADMIN")
        .requestMatchers("/controindications/index", "/controindications/*").hasAnyAuthority("ADMIN", "USER")

        .requestMatchers("/families/create", "/families/*/edit").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST , "/families/**").hasAuthority("ADMIN")
        .requestMatchers("/families/index", "/families/*").hasAnyAuthority("ADMIN", "USER")

        .requestMatchers("/**").permitAll())
        .formLogin(Customizer.withDefaults())
        .logout(logout -> logout.logoutSuccessUrl("/login"));
        

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DatabaseUserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    
}
