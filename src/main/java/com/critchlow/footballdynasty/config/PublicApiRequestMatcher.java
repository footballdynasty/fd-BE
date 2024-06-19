package com.critchlow.footballdynasty.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class PublicApiRequestMatcher implements RequestMatcher {
    @Override
    public boolean matches(HttpServletRequest request) {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/api/**")
        ).matches(request);
    }
}
