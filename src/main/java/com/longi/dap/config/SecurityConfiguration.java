package com.longi.dap.config;

        import com.longi.dap.service.impl.UserDetailsServiceImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.builders.WebSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 从数据库获取角色权限
        auth.userDetailsService(userDetailsServiceImpl);
//        auth.inMemoryAuthentication().withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
                http.csrf().disable()//禁用了 csrf 功能
                .authorizeRequests()//限定签名成功的请求
                         .anyRequest().authenticated()
//                .antMatchers("/main").hasRole("user")
                .and().formLogin()
                .loginPage("/login").permitAll()
//                        .defaultSuccessUrl("/main")
//                        .failureUrl("/login?error=true")
                        .failureHandler(new CustomFailureHandler())
                        .successHandler(new CustomerSuccessHandler())
                .and().logout().addLogoutHandler(new CustomLogoutHandler()).logoutSuccessHandler(new CustomLogoutSuccessHandler()).deleteCookies("JSESSIONID");
    }
}
