/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	ResourceServerConfig.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月30日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Jing
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(new OAuth2RequestedMatcher()).authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated();
    }

    private static class OAuth2RequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
            boolean haveAccessToken = request.getParameter("access_token") != null;
            return haveOauth2Token || haveAccessToken;
        }
    }
}
