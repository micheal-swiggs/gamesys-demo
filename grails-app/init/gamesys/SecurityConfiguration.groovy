package gamesys

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity

class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("hruser").password("hruser").roles("HR")
            .and()
            .withUser("stduser").password("stduser").roles("STANDARD");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers('/employee/index/**', '/employee/show/**').hasAnyRole('HR', 'STANDARD')
                .antMatchers('/employee/edit/**', '/employee/delete/**','/employee/save/**', '/employee/create/**').hasAnyRole('HR')
                .antMatchers('/').permitAll()
            .and()
                .formLogin().permitAll()
            .and()
                .logout().permitAll()
    }
}
