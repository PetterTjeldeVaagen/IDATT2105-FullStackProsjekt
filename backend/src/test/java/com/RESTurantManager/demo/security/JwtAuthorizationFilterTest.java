package com.RESTurantManager.demo.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import com.RESTurantManager.demo.service.AuthenticationService;

import jakarta.servlet.FilterChain;

@ExtendWith(MockitoExtension.class)
class JwtAuthorizationFilterTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private FilterChain filterChain;

    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @BeforeEach
    void setUp() {
        jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationService);
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("doFilterInternal should set authentication when bearer token is valid")
    void doFilterInternalValidTokenTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer valid-token");

        when(authenticationService.validateToken("valid-token")).thenReturn(true);
        when(authenticationService.getEmailFromToken("valid-token")).thenReturn("test@example.com");

        ReflectionTestUtils.invokeMethod(
                jwtAuthorizationFilter,
                "doFilterInternal",
                request,
                response,
                filterChain
        );

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("test@example.com", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        verify(authenticationService).validateToken("valid-token");
        verify(authenticationService).getEmailFromToken("valid-token");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    @DisplayName("doFilterInternal should not set authentication when token is invalid")
    void doFilterInternalInvalidTokenTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer invalid-token");

        when(authenticationService.validateToken("invalid-token")).thenReturn(false);

        ReflectionTestUtils.invokeMethod(
                jwtAuthorizationFilter,
                "doFilterInternal",
                request,
                response,
                filterChain
        );

        assertNull(SecurityContextHolder.getContext().getAuthentication());

        verify(authenticationService).validateToken("invalid-token");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    @DisplayName("doFilterInternal should not set authentication when authorization header is missing")
    void doFilterInternalMissingHeaderTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        ReflectionTestUtils.invokeMethod(
                jwtAuthorizationFilter,
                "doFilterInternal",
                request,
                response,
                filterChain
        );

        assertNull(SecurityContextHolder.getContext().getAuthentication());

        verify(filterChain).doFilter(request, response);
    }

    @Test
    @DisplayName("doFilterInternal should not set authentication when authorization header does not start with Bearer")
    void doFilterInternalInvalidHeaderFormatTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Basic some-token");

        ReflectionTestUtils.invokeMethod(
                jwtAuthorizationFilter,
                "doFilterInternal",
                request,
                response,
                filterChain
        );

        assertNull(SecurityContextHolder.getContext().getAuthentication());

        verify(filterChain).doFilter(request, response);
    }

    @Test
    @DisplayName("doFilterInternal should always continue filter chain")
    void doFilterInternalShouldAlwaysContinueFilterChainTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer invalid-token");

        when(authenticationService.validateToken("invalid-token")).thenReturn(false);

        ReflectionTestUtils.invokeMethod(
                jwtAuthorizationFilter,
                "doFilterInternal",
                request,
                response,
                filterChain
        );

        verify(filterChain).doFilter(request, response);
    }
}