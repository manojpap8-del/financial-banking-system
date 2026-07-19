package manoj.example.financial_banking_system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manoj.example.financial_banking_system.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService customUserDetailsService) {

        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        System.out.println("================================== - JwtAuthenticationFilter.java:40");
        System.out.println("Request URI : - JwtAuthenticationFilter.java:41" + request.getRequestURI());
        System.out.println("Authorization Header : - JwtAuthenticationFilter.java:42" + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            System.out.println("No JWT Token Found - JwtAuthenticationFilter.java:46");

            filterChain.doFilter(request, response);
            return;
        }

        try {

            String jwt = authHeader.substring(7);

            System.out.println("JWT : - JwtAuthenticationFilter.java:56" + jwt);

            String email = jwtService.extractEmail(jwt);

            System.out.println("Email : - JwtAuthenticationFilter.java:60" + email);

            UserDetails userDetails =
                    customUserDetailsService.loadUserByUsername(email);

            System.out.println("User Found : - JwtAuthenticationFilter.java:65" + userDetails.getUsername());

            boolean valid =
                    jwtService.isTokenValid(jwt, userDetails.getUsername());

            System.out.println("Token Valid : - JwtAuthenticationFilter.java:70" + valid);

            if (valid) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);

                System.out.println("Authentication SUCCESS - JwtAuthenticationFilter.java:87");

            } else {

                System.out.println("Authentication FAILED - JwtAuthenticationFilter.java:91");

            }

        } catch (Exception e) {

            System.out.println("JWT ERROR : - JwtAuthenticationFilter.java:97" + e.getMessage());

        }

        System.out.println("================================== - JwtAuthenticationFilter.java:101");

        filterChain.doFilter(request, response);
    }
}