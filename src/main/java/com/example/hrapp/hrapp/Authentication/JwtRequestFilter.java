package com.example.hrapp.hrapp.Authentication;

import lombok.RequiredArgsConstructor;
import com.example.hrapp.hrapp.Domain.User;
import com.example.hrapp.hrapp.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            String jwtToken = authorization.substring(7);
            String email = JwtUtil.extractUsernameFromJWT(jwtToken, secretKey);
            User user = (User) userDetailsService.loadUserByUsername(email);

            var authenticationToken = new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}