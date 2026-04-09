package com.RESTurantManager.demo.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.RESTurantManager.demo.service.AuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter class for JWT authorization. Intercepts incoming HTTP requests and checks for a valid JWT token in the Authorization header.
 * If a valid token is found, it sets the authentication in the SecurityContext.
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    /**
     * Constructor for JwtAuthorizationFilter.
     * @param authenticationService the service for validating JWT tokens and extracting user information
     */
    public JwtAuthorizationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Filters incoming HTTP requests and checks for a valid JWT token in the Authorization header.
     * If a valid token is found, it sets the authentication in the SecurityContext.
     * @param request the incoming HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain to pass the request and response to the next filter in the chain
     * @throws ServletException if an error occurs during filtering
     * @throws IOException if an I/O error occurs during filtering
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (authenticationService.validateToken(token)) {
                String email = authenticationService.getEmailFromToken(token);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email,null,Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}