package web.badminton.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import web.badminton.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/createUser").permitAll()
                .antMatchers("/registershop").permitAll()
                //role member
                .antMatchers("/profile").hasRole("MEMBER")             
                .antMatchers("/profile-favoriteproduct").hasRole("MEMBER")
                .antMatchers("/profile-ordermanagement").hasRole("MEMBER")
                .antMatchers("/profile-reviewproduct").hasRole("MEMBER")
                .antMatchers("/profile-viewedproduct").hasRole("MEMBER")
                .antMatchers("/cart").hasRole("MEMBER")
                .antMatchers("/pay").hasRole("MEMBER")
                .antMatchers("/buy").hasRole("MEMBER")
                //role shop
                .antMatchers("/addnew").hasRole("SHOP")
                .antMatchers("/saleschannel-settingshop").hasRole("SHOP")             
                .antMatchers("/saleschannel").permitAll()
                .antMatchers("/saleschannel-allproduct-hide").hasRole("SHOP")
                .antMatchers("/saleschannel-allproduct-show").hasRole("SHOP")
                .antMatchers("/saleschannel-allproduct-soldout").hasRole("SHOP")
                .antMatchers("/saleschannel-product-banned").hasRole("SHOP")
                .antMatchers("/waitconfirmation").hasRole("SHOP")
                .antMatchers("/successfuldelivery").hasRole("SHOP")
                .antMatchers("/closepackage").hasRole("SHOP")
                .antMatchers("/cancelorder").hasRole("SHOP")
                .antMatchers("/beingtransported").hasRole("SHOP")
                .antMatchers("/statistical").hasRole("SHOP")      
                //role admin        
                .antMatchers("/admin").hasRole("ADMIN")    
                .antMatchers("/accountManagement").hasRole("ADMIN")  
                .antMatchers("/addCategories").hasRole("ADMIN")  
                .antMatchers("/commentManagement").hasRole("ADMIN")
                .antMatchers("/productManagement").hasRole("ADMIN")
                .antMatchers("/shopManagement").hasRole("ADMIN")  
                .and()
            .formLogin()           
                .loginPage("/login")
                .usernameParameter("accountLogin")
                .passwordParameter("passwordLogin")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/403");
            
    }
    
    
}