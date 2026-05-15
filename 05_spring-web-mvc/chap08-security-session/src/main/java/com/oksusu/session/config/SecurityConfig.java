package com.oksusu.session.config;

import com.oksusu.session.common.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest; // 이거 봐야함
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthFailHandler authFailHandler;

    @Autowired
    public SecurityConfig( AuthFailHandler authFailHandler) {
        this.authFailHandler = authFailHandler;
    }

    @Bean // 비밀번호를 인코딩하기 위한 bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 정적 리소스에 대한 요청은 인증 제외하겠따 static 구역에 대한 요청을 왜 제ㅚ할까? 홈화면에서도 로그인하게 만들면 구려서
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> {
                    // 인가처리를 어떻게 시작하겠다? 해당 인가를 어떻게 처리할지 대상을 정리해둔 곳 .permitAll 모든 사람들이 쓸 수 있따.
                    auth.requestMatchers("/auth/login", "/signup", "auth/fail", "/").permitAll();
                    // .hasAnyAuthority 권한을 가진 사용자들만 가질 수 있따.
                    auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getValue()); //enum으로 가져오기
                    auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getValue());
                    auth.anyRequest().authenticated(); // 모든 것은 인가를 받아야 사용할 수 있따.

                }
        ).formLogin(form -> {
                    form.loginPage("/auth/login");
                    form.usernameParameter("user"); // 프론트딴에서 name=username 이 부분을 바꿀 때 그 이름 정하기
                    form.passwordParameter("pass");
                    form.defaultSuccessUrl("/", true);
                    form.failureHandler(authFailHandler);

                }
        ).logout(logout -> {
                    logout.logoutUrl("/auth/logout");
                    logout.deleteCookies("JSESSIONID"); // 쿠키 삭제해서 session 정보를 날려서 로그아웃하게 하는 방식
                    logout.invalidateHttpSession(true); // 서버에 들어온 사용자의 인증정보 삭제
                    logout.logoutSuccessUrl("/");

                }
        ).sessionManagement(session -> {
                    session.maximumSessions(1); // 로그인 1명만 허용
                    session.invalidSessionUrl("/");

                }
        ).csrf(csrf -> csrf.disable());

        return http.build();
    }
























}
