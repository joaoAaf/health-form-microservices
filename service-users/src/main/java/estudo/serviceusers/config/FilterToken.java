package estudo.serviceusers.config;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import estudo.serviceusers.dto.UserLogged;
import estudo.serviceusers.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FilterToken extends OncePerRequestFilter {

	private final TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			var token = authorizationHeader.replace("Bearer ", "");
			if (tokenService.loggedId(token).isEmpty()) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType("application/json");
				response.getWriter().write("{\"error\": \"Token inválido ou expirado\"}");
				LoggerFactory.getLogger(this.getClass()).error("Token inválido ou expirado");
				return;
			}
			var user = new UserLogged(tokenService.loggedId(token).get());
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
			return;
		}
		if (request.getRequestURI().startsWith("/auth")
				|| request.getRequestURI().startsWith("/swagger-ui")
				|| request.getRequestURI().startsWith("/v3/api-docs")
				|| request.getRequestURI().equals("/user/register")) {
			filterChain.doFilter(request, response);
			return;
		}
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.getWriter().write("{\"msg\": \"Token ausente\"}");
		LoggerFactory.getLogger(this.getClass()).error("Token ausente");
	}
}
