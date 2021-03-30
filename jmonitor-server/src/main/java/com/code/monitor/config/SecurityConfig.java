package com.code.monitor.config;

import com.code.monitor.security.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/29 15:40
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //这里配置PasswordEncoder,BCryptPasswordEncoder是security提供的PasswordEncorder的一个实现类
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()   //使用表单登录页面
                .loginPage("/login").failureUrl("/login-error")    //登录url
                .loginProcessingUrl("/doLogin")    //登录提交url
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/doRegister", "/login", "/doLogin", "/test", "/login-error").permitAll()
                .antMatchers("/static/**", "/lib/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .rememberMe()
                .tokenRepository(getPersistentTokenRepository())
                //cookie失效时间
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(2 * 24 * 60 * 60);

        //设置TokenRepository

    }

    /*
     * 配置TokenRepository组件。
     * */
    @Bean
    public PersistentTokenRepository getPersistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

}
