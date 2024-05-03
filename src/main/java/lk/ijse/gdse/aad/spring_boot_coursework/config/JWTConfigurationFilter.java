package lk.ijse.gdse.aad.spring_boot_coursework.config;


import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.aad.spring_boot_coursework.service.JWTService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JWTConfigurationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String user_email;
        final String jwt;
        String authorization_header = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authorization_header) || !authorization_header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authorization_header.substring(7);
        user_email = jwtService.extractUsername(jwt);

        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        if (StringUtils.isNotEmpty(user_email) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.USER_DETAILS_SERVICE().loadUserByUsername(user_email);

            //Validation of Token Status
            if (jwtService.istokenValid(jwt, userDetails)) {
                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource());
                emptyContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(emptyContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
