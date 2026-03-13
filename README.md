DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Stored SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] to HttpSession [org.springframework.mock.web.MockHttpSession@7ff470b1]
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Trying to match request against DefaultSecurityFilterChain defined as 'jwtSecurityFilterChain' in [class path resource [org/springframework/boot/autoconfigure/security/oauth2/resource/servlet/OAuth2ResourceServerJwtConfiguration$OAuth2SecurityFilterChainConfiguration.class]] matching [any request] and having filters [DisableEncodeUrl, WebAsyncManagerIntegration, SecurityContextHolder, HeaderWriter, Csrf, Logout, BearerTokenAuthentication, Authentication, RequestCacheAware, SecurityContextHolderAwareRequest, AnonymousAuthentication, ExceptionTranslation, Authorization] (1/1)
2026-03-13T17:33:58.548-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Securing GET /users
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking DisableEncodeUrlFilter (1/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking WebAsyncManagerIntegrationFilter (2/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderFilter (3/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking HeaderWriterFilter (4/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking CsrfFilter (5/13)   
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] s.s.w.c.CsrfTokenRequestAttributeHandler : Wrote a CSRF token to the following request attributes: [_csrf, org.springframework.security.web.csrf.CsrfToken]
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.csrf.CsrfFilter         : Did not protect against CSRF 
since request did not match And [CsrfNotRequired [TRACE, HEAD, GET, OPTIONS], Not [Or [org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer$BearerTokenRequestMatcher@57f59036]]]
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking LogoutFilter (6/13) 
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.s.w.a.logout.LogoutFilter            : Did not match request to Ant 
[pattern='/logout', POST]
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking BearerTokenAuthenticationFilter (7/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] .s.r.w.a.BearerTokenAuthenticationFilter : Did not process request since did not find bearer token
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthenticationFilter (8/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AuthenticationFilter           : Did not match request to org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.DPoPAuthenticationConfigurer$DPoPRequestMatcher@29600405
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking RequestCacheAwareFilter (9/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.s.w.s.HttpSessionRequestCache        : matchingRequestParameterName 
is required for getMatchingRequest to lookup a value, but not provided
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderAwareRequestFilter (10/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AnonymousAuthenticationFilter (11/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking ExceptionTranslationFilter (12/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthorizationFilter 
(13/13)
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Authorizing GET /users       
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Checking authorization on GET /users using org.springframework.security.authorization.AuthenticatedAuthorizationManager@37aa7ede
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Retrieved SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] from SPRING_SECURITY_CONTEXT
2026-03-13T17:33:58.548-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AnonymousAuthenticationFilter  : Did not set SecurityContextHolder since already authenticated UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]
2026-03-13T17:33:58.548-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Secured GET /users
2026-03-13T17:33:58.552-04:00 TRACE 10900 --- [           main] o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match request to [Is Secure]
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Trying to match request against DefaultSecurityFilterChain defined as 'jwtSecurityFilterChain' in [class path resource [org/springframework/boot/autoconfigure/security/oauth2/resource/servlet/OAuth2ResourceServerJwtConfiguration$OAuth2SecurityFilterChainConfiguration.class]] matching [any request] and having filters [DisableEncodeUrl, WebAsyncManagerIntegration, SecurityContextHolder, HeaderWriter, Csrf, Logout, BearerTokenAuthentication, Authentication, RequestCacheAware, SecurityContextHolderAwareRequest, AnonymousAuthentication, ExceptionTranslation, Authorization] (1/1)
2026-03-13T17:33:58.561-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Securing GET /users
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking DisableEncodeUrlFilter (1/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking WebAsyncManagerIntegrationFilter (2/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderFilter (3/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking HeaderWriterFilter (4/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking CsrfFilter (5/13)   
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] s.s.w.c.CsrfTokenRequestAttributeHandler : Wrote a CSRF token to the following request attributes: [_csrf, org.springframework.security.web.csrf.CsrfToken]
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.csrf.CsrfFilter         : Did not protect against CSRF 
since request did not match And [CsrfNotRequired [TRACE, HEAD, GET, OPTIONS], Not [Or [org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer$BearerTokenRequestMatcher@57f59036]]]
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking LogoutFilter (6/13) 
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.s.w.a.logout.LogoutFilter            : Did not match request to Ant 
[pattern='/logout', POST]
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking BearerTokenAuthenticationFilter (7/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] .s.r.w.a.BearerTokenAuthenticationFilter : Did not process request since did not find bearer token
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthenticationFilter (8/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AuthenticationFilter           : Did not match request to org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.DPoPAuthenticationConfigurer$DPoPRequestMatcher@29600405
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking RequestCacheAwareFilter (9/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.s.w.s.HttpSessionRequestCache        : matchingRequestParameterName 
is required for getMatchingRequest to lookup a value, but not provided
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderAwareRequestFilter (10/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AnonymousAuthenticationFilter (11/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking ExceptionTranslationFilter (12/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthorizationFilter 
(13/13)
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Authorizing GET /users       
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Checking authorization on GET /users using org.springframework.security.authorization.AuthenticatedAuthorizationManager@37aa7ede
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : No HttpSession currently exists
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.561-04:00 TRACE 10900 --- [           main] .s.s.w.c.SupplierDeferredSecurityContext : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.562-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AnonymousAuthenticationFilter  : Set SecurityContextHolder to 
AnonymousAuthenticationToken [Principal=anonymousUser, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=127.0.0.1, SessionId=null], Granted Authorities=[ROLE_ANONYMOUS]]
2026-03-13T17:33:58.562-04:00 TRACE 10900 --- [           main] o.s.s.w.a.ExceptionTranslationFilter     : Sending AnonymousAuthenticationToken [Principal=anonymousUser, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=127.0.0.1, SessionId=null], Granted Authorities=[ROLE_ANONYMOUS]] to authentication entry point since access is denied

org.springframework.security.authorization.AuthorizationDeniedException: Access Denied
        at org.springframework.security.web.access.intercept.AuthorizationFilter.doFilter(AuthorizationFilter.java:99) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:125) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:100) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:179) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.AuthenticationFilter.doFilterInternal(AuthenticationFilter.java:176) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter.doFilterInternal(BearerTokenAuthenticationFilter.java:135) ~[spring-security-oauth2-resource-server-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:107) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:93) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.csrf.CsrfFilter.doFilterInternal(CsrfFilter.java:117) ~[spring-security-web-6.5.0.jar:6.5.0]        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:82) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:69) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:62) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.session.DisableEncodeUrlFilter.doFilterInternal(DisableEncodeUrlFilter.java:42) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:233) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:191) ~[spring-security-web-6.5.0.jar:6.5.0] 
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.ServletRequestPathFilter.doFilter(ServletRequestPathFilter.java:52) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebSecurityConfiguration.java:319) ~[spring-security-config-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.handler.HandlerMappingIntrospector.lambda$createCacheFilter$3(HandlerMappingIntrospector.java:243) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.security.config.annotation.web.configuration.WebMvcSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebMvcSecurityConfiguration.java:240) ~[spring-security-config-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:362) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:278) ~[spring-web-6.2.7.jar:6.2.7]  
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.2.7.jar:6.2.7]   
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:201) ~[spring-test-6.2.7.jar:6.2.7]
        at org.nnnn.ddd.service.UsersControllerTest.getUserList_noAuth_returns401(UsersControllerTest.java:99) ~[test-classes/:na]      
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:565) ~[na:na]
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:775) ~[junit-platform-commons-1.12.2.jar:1.12.2]
        at org.junit.platform.commons.support.ReflectionSupport.invokeMethod(ReflectionSupport.java:479) ~[junit-platform-commons-1.12.2.jar:1.12.2]
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:161) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:152) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:91) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:112) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:94) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:93) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:87) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:216) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:212) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:137) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:69) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:156) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:201) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:170) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:94) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:59) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:142) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:58) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56) ~[surefire-junit-platform-3.5.3.jar:3.5.3]        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:194) ~[surefire-junit-platform-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:150) ~[surefire-junit-platform-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:124) ~[surefire-junit-platform-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385) ~[surefire-booter-3.5.3.jar:3.5.3]   
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162) ~[surefire-booter-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507) ~[surefire-booter-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495) ~[surefire-booter-3.5.3.jar:3.5.3]

2026-03-13T17:33:58.568-04:00 TRACE 10900 --- [           main] o.s.s.w.s.HttpSessionRequestCache        : Did not save request since it did not match [And [Ant [pattern='/**', GET], Not [Ant [pattern='/**/favicon.*']], Not [MediaTypeRequestMatcher [contentNegotiationStrategy=org.springframework.web.accept.ContentNegotiationManager@6f4d9baf, matchingMediaTypes=[application/json], useEquals=false, ignoredMediaTypes=[*/*]]], Not [RequestHeaderRequestMatcher [expectedHeaderName=X-Requested-With, expectedHeaderValue=XMLHttpRequest]], Not [MediaTypeRequestMatcher [contentNegotiationStrategy=org.springframework.web.accept.ContentNegotiationManager@6f4d9baf, matchingMediaTypes=[multipart/form-data], useEquals=false, ignoredMediaTypes=[*/*]]], Not [MediaTypeRequestMatcher [contentNegotiationStrategy=org.springframework.web.accept.ContentNegotiationManager@6f4d9baf, matchingMediaTypes=[text/event-stream], useEquals=false, ignoredMediaTypes=[*/*]]]]]
2026-03-13T17:33:58.568-04:00 TRACE 10900 --- [           main] o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match request to [Is Secure]
2026-03-13T17:33:58.573-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : No HttpSession currently exists
2026-03-13T17:33:58.573-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.573-04:00 TRACE 10900 --- [           main] .s.s.w.c.SupplierDeferredSecurityContext : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.573-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created HttpSession as SecurityContext is non-default
2026-03-13T17:33:58.573-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Stored SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] to HttpSession [org.springframework.mock.web.MockHttpSession@50b90535]
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Trying to match request against DefaultSecurityFilterChain defined as 'jwtSecurityFilterChain' in [class path resource [org/springframework/boot/autoconfigure/security/oauth2/resource/servlet/OAuth2ResourceServerJwtConfiguration$OAuth2SecurityFilterChainConfiguration.class]] matching [any request] and having filters [DisableEncodeUrl, WebAsyncManagerIntegration, SecurityContextHolder, HeaderWriter, Csrf, Logout, BearerTokenAuthentication, Authentication, RequestCacheAware, SecurityContextHolderAwareRequest, AnonymousAuthentication, ExceptionTranslation, Authorization] (1/1)
2026-03-13T17:33:58.574-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Securing GET /users
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking DisableEncodeUrlFilter (1/13)
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking WebAsyncManagerIntegrationFilter (2/13)
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderFilter (3/13)
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking HeaderWriterFilter (4/13)
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking CsrfFilter (5/13)   
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] s.s.w.c.CsrfTokenRequestAttributeHandler : Wrote a CSRF token to the following request attributes: [_csrf, org.springframework.security.web.csrf.CsrfToken]
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.csrf.CsrfFilter         : Did not protect against CSRF 
since request did not match And [CsrfNotRequired [TRACE, HEAD, GET, OPTIONS], Not [Or [org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer$BearerTokenRequestMatcher@57f59036]]]
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking LogoutFilter (6/13) 
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.s.w.a.logout.LogoutFilter            : Did not match request to Ant 
[pattern='/logout', POST]
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking BearerTokenAuthenticationFilter (7/13)
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] .s.r.w.a.BearerTokenAuthenticationFilter : Did not process request since did not find bearer token
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthenticationFilter (8/13)
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AuthenticationFilter           : Did not match request to org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.DPoPAuthenticationConfigurer$DPoPRequestMatcher@29600405
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking RequestCacheAwareFilter (9/13)
2026-03-13T17:33:58.574-04:00 TRACE 10900 --- [           main] o.s.s.w.s.HttpSessionRequestCache        : matchingRequestParameterName 
is required for getMatchingRequest to lookup a value, but not provided
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderAwareRequestFilter (10/13)
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AnonymousAuthenticationFilter (11/13)
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking ExceptionTranslationFilter (12/13)
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthorizationFilter 
(13/13)
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Authorizing GET /users       
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Checking authorization on GET /users using org.springframework.security.authorization.AuthenticatedAuthorizationManager@37aa7ede
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Retrieved SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] from SPRING_SECURITY_CONTEXT
2026-03-13T17:33:58.575-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AnonymousAuthenticationFilter  : Did not set SecurityContextHolder since already authenticated UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]
2026-03-13T17:33:58.575-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Secured GET /users
2026-03-13T17:33:58.580-04:00 TRACE 10900 --- [           main] o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match request to [Is Secure]
2026-03-13T17:33:58.619-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : No HttpSession currently exists
2026-03-13T17:33:58.619-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.619-04:00 TRACE 10900 --- [           main] .s.s.w.c.SupplierDeferredSecurityContext : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.619-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created HttpSession as SecurityContext is non-default
2026-03-13T17:33:58.619-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Stored SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] to HttpSession [org.springframework.mock.web.MockHttpSession@5b6e2a7e]
2026-03-13T17:33:58.619-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Trying to match request against DefaultSecurityFilterChain defined as 'jwtSecurityFilterChain' in [class path resource [org/springframework/boot/autoconfigure/security/oauth2/resource/servlet/OAuth2ResourceServerJwtConfiguration$OAuth2SecurityFilterChainConfiguration.class]] matching [any request] and having filters [DisableEncodeUrl, WebAsyncManagerIntegration, SecurityContextHolder, HeaderWriter, Csrf, Logout, BearerTokenAuthentication, Authentication, RequestCacheAware, SecurityContextHolderAwareRequest, AnonymousAuthentication, ExceptionTranslation, Authorization] (1/1)
2026-03-13T17:33:58.620-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Securing GET /users
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking DisableEncodeUrlFilter (1/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking WebAsyncManagerIntegrationFilter (2/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderFilter (3/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking HeaderWriterFilter (4/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking CsrfFilter (5/13)   
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] s.s.w.c.CsrfTokenRequestAttributeHandler : Wrote a CSRF token to the following request attributes: [_csrf, org.springframework.security.web.csrf.CsrfToken]
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.csrf.CsrfFilter         : Did not protect against CSRF 
since request did not match And [CsrfNotRequired [TRACE, HEAD, GET, OPTIONS], Not [Or [org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer$BearerTokenRequestMatcher@57f59036]]]
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking LogoutFilter (6/13) 
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.s.w.a.logout.LogoutFilter            : Did not match request to Ant 
[pattern='/logout', POST]
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking BearerTokenAuthenticationFilter (7/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] .s.r.w.a.BearerTokenAuthenticationFilter : Did not process request since did not find bearer token
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthenticationFilter (8/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AuthenticationFilter           : Did not match request to org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.DPoPAuthenticationConfigurer$DPoPRequestMatcher@29600405
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking RequestCacheAwareFilter (9/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.s.w.s.HttpSessionRequestCache        : matchingRequestParameterName 
is required for getMatchingRequest to lookup a value, but not provided
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderAwareRequestFilter (10/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AnonymousAuthenticationFilter (11/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking ExceptionTranslationFilter (12/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthorizationFilter 
(13/13)
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Authorizing GET /users       
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Checking authorization on GET /users using org.springframework.security.authorization.AuthenticatedAuthorizationManager@37aa7ede
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Retrieved SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] from SPRING_SECURITY_CONTEXT
2026-03-13T17:33:58.620-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AnonymousAuthenticationFilter  : Did not set SecurityContextHolder since already authenticated UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]
2026-03-13T17:33:58.620-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Secured GET /users
2026-03-13T17:33:58.621-04:00 ERROR 10900 --- [           main] org.nnnn.ddd.api.UsersApiController      : Couldn't serialize response for content type application/json

java.lang.RuntimeException: LDAP connection failed
        at org.nnnn.ddd.api.UsersApiController.getUserList(UsersApiController.java:62) ~[classes/:na]
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:565) ~[na:na]
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:359) ~[spring-aop-6.2.7.jar:6.2.7]     
        at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196) ~[spring-aop-6.2.7.jar:6.2.7]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-6.2.7.jar:6.2.7]
        at org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:174) ~[spring-context-6.2.7.jar:6.2.7]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.2.7.jar:6.2.7]
        at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:728) ~[spring-aop-6.2.7.jar:6.2.7]
        at org.nnnn.ddd.api.UsersApiController$$SpringCGLIB$$0.getUserList(<generated>) ~[classes/:na]
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:565) ~[na:na]
        at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:258) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:191) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:986) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:891) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089) ~[spring-webmvc-6.2.7.jar:6.2.7]   
        at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979) ~[spring-webmvc-6.2.7.jar:6.2.7]     
        at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014) ~[spring-webmvc-6.2.7.jar:6.2.7] 
        at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564) ~[tomcat-embed-core-10.1.41.jar:6.0]
        at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72) ~[spring-test-6.2.7.jar:6.2.7]
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658) ~[tomcat-embed-core-10.1.41.jar:6.0]
        at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:165) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.resource.ResourceUrlEncodingFilter.doFilter(ResourceUrlEncodingFilter.java:66) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:108) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:108) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.security.web.FilterChainProxy.lambda$doFilterInternal$3(FilterChainProxy.java:231) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:365) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.access.intercept.AuthorizationFilter.doFilter(AuthorizationFilter.java:101) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:125) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:100) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:179) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.AuthenticationFilter.doFilterInternal(AuthenticationFilter.java:176) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter.doFilterInternal(BearerTokenAuthenticationFilter.java:135) ~[spring-security-oauth2-resource-server-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:107) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:93) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.csrf.CsrfFilter.doFilterInternal(CsrfFilter.java:117) ~[spring-security-web-6.5.0.jar:6.5.0]        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:82) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:69) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:62) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.session.DisableEncodeUrlFilter.doFilterInternal(DisableEncodeUrlFilter.java:42) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:233) ~[spring-security-web-6.5.0.jar:6.5.0]
        at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:191) ~[spring-security-web-6.5.0.jar:6.5.0] 
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.ServletRequestPathFilter.doFilter(ServletRequestPathFilter.java:52) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebSecurityConfiguration.java:319) ~[spring-security-config-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.servlet.handler.HandlerMappingIntrospector.lambda$createCacheFilter$3(HandlerMappingIntrospector.java:243) ~[spring-webmvc-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.security.config.annotation.web.configuration.WebMvcSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebMvcSecurityConfiguration.java:240) ~[spring-security-config-6.5.0.jar:6.5.0]
        at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:362) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:278) ~[spring-web-6.2.7.jar:6.2.7]  
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.2.7.jar:6.2.7]   
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.2.7.jar:6.2.7]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.7.jar:6.2.7]    
        at org.springframework.test.web.servlet.setup.MockMvcFilterDecorator.doFilter(MockMvcFilterDecorator.java:162) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:132) ~[spring-test-6.2.7.jar:6.2.7]
        at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:201) ~[spring-test-6.2.7.jar:6.2.7]
        at org.nnnn.ddd.service.UsersControllerTest.getUserList_ldapThrowsException_returns500(UsersControllerTest.java:93) ~[test-classes/:na]
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:565) ~[na:na]
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:775) ~[junit-platform-commons-1.12.2.jar:1.12.2]
        at org.junit.platform.commons.support.ReflectionSupport.invokeMethod(ReflectionSupport.java:479) ~[junit-platform-commons-1.12.2.jar:1.12.2]
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:161) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:152) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:91) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:112) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:94) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:93) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:87) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:216) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:212) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:137) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:69) ~[junit-jupiter-engine-5.12.2.jar:5.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:156) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:160) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:146) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:144) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:143) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:100) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54) ~[junit-platform-engine-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:201) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:170) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:94) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:59) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:142) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:58) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47) ~[junit-platform-launcher-1.12.2.jar:1.12.2]
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56) ~[surefire-junit-platform-3.5.3.jar:3.5.3]        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:194) ~[surefire-junit-platform-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:150) ~[surefire-junit-platform-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:124) ~[surefire-junit-platform-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385) ~[surefire-booter-3.5.3.jar:3.5.3]   
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162) ~[surefire-booter-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507) ~[surefire-booter-3.5.3.jar:3.5.3]
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495) ~[surefire-booter-3.5.3.jar:3.5.3]

2026-03-13T17:33:58.622-04:00 TRACE 10900 --- [           main] o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match request to [Is Secure]
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : No HttpSession currently exists
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] .s.s.w.c.SupplierDeferredSecurityContext : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.628-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created HttpSession as SecurityContext is non-default
2026-03-13T17:33:58.628-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Stored SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] to HttpSession [org.springframework.mock.web.MockHttpSession@48bbf53e]
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Trying to match request against DefaultSecurityFilterChain defined as 'jwtSecurityFilterChain' in [class path resource [org/springframework/boot/autoconfigure/security/oauth2/resource/servlet/OAuth2ResourceServerJwtConfiguration$OAuth2SecurityFilterChainConfiguration.class]] matching [any request] and having filters [DisableEncodeUrl, WebAsyncManagerIntegration, SecurityContextHolder, HeaderWriter, Csrf, Logout, BearerTokenAuthentication, Authentication, RequestCacheAware, SecurityContextHolderAwareRequest, AnonymousAuthentication, ExceptionTranslation, Authorization] (1/1)
2026-03-13T17:33:58.628-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Securing GET /users
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking DisableEncodeUrlFilter (1/13)
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking WebAsyncManagerIntegrationFilter (2/13)
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderFilter (3/13)
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking HeaderWriterFilter (4/13)
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking CsrfFilter (5/13)   
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] s.s.w.c.CsrfTokenRequestAttributeHandler : Wrote a CSRF token to the following request attributes: [_csrf, org.springframework.security.web.csrf.CsrfToken]
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.csrf.CsrfFilter         : Did not protect against CSRF 
since request did not match And [CsrfNotRequired [TRACE, HEAD, GET, OPTIONS], Not [Or [org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer$BearerTokenRequestMatcher@57f59036]]]
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking LogoutFilter (6/13) 
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.s.w.a.logout.LogoutFilter            : Did not match request to Ant 
[pattern='/logout', POST]
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking BearerTokenAuthenticationFilter (7/13)
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] .s.r.w.a.BearerTokenAuthenticationFilter : Did not process request since did not find bearer token
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthenticationFilter (8/13)
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AuthenticationFilter           : Did not match request to org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.DPoPAuthenticationConfigurer$DPoPRequestMatcher@29600405
2026-03-13T17:33:58.628-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking RequestCacheAwareFilter (9/13)
2026-03-13T17:33:58.629-04:00 TRACE 10900 --- [           main] o.s.s.w.s.HttpSessionRequestCache        : matchingRequestParameterName 
is required for getMatchingRequest to lookup a value, but not provided
2026-03-13T17:33:58.643-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderAwareRequestFilter (10/13)
2026-03-13T17:33:58.643-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AnonymousAuthenticationFilter (11/13)
2026-03-13T17:33:58.643-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking ExceptionTranslationFilter (12/13)
2026-03-13T17:33:58.643-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthorizationFilter 
(13/13)
2026-03-13T17:33:58.644-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Authorizing GET /users       
2026-03-13T17:33:58.644-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Checking authorization on GET /users using org.springframework.security.authorization.AuthenticatedAuthorizationManager@37aa7ede
2026-03-13T17:33:58.644-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Retrieved SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] from SPRING_SECURITY_CONTEXT
2026-03-13T17:33:58.645-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AnonymousAuthenticationFilter  : Did not set SecurityContextHolder since already authenticated UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]
2026-03-13T17:33:58.645-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Secured GET /users
2026-03-13T17:33:58.649-04:00 TRACE 10900 --- [           main] o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match request to [Is Secure]
2026-03-13T17:33:58.655-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : No HttpSession currently exists
2026-03-13T17:33:58.655-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.655-04:00 TRACE 10900 --- [           main] .s.s.w.c.SupplierDeferredSecurityContext : Created SecurityContextImpl [Null authentication]
2026-03-13T17:33:58.656-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Created HttpSession as SecurityContext is non-default
2026-03-13T17:33:58.656-04:00 DEBUG 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Stored SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] to HttpSession [org.springframework.mock.web.MockHttpSession@4458ecb8]
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Trying to match request against DefaultSecurityFilterChain defined as 'jwtSecurityFilterChain' in [class path resource [org/springframework/boot/autoconfigure/security/oauth2/resource/servlet/OAuth2ResourceServerJwtConfiguration$OAuth2SecurityFilterChainConfiguration.class]] matching [any request] and having filters [DisableEncodeUrl, WebAsyncManagerIntegration, SecurityContextHolder, HeaderWriter, Csrf, Logout, BearerTokenAuthentication, Authentication, RequestCacheAware, SecurityContextHolderAwareRequest, AnonymousAuthentication, ExceptionTranslation, Authorization] (1/1)
2026-03-13T17:33:58.656-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Securing GET /users
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking DisableEncodeUrlFilter (1/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking WebAsyncManagerIntegrationFilter (2/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderFilter (3/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking HeaderWriterFilter (4/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking CsrfFilter (5/13)   
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] s.s.w.c.CsrfTokenRequestAttributeHandler : Wrote a CSRF token to the following request attributes: [_csrf, org.springframework.security.web.csrf.CsrfToken]
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.csrf.CsrfFilter         : Did not protect against CSRF 
since request did not match And [CsrfNotRequired [TRACE, HEAD, GET, OPTIONS], Not [Or [org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer$BearerTokenRequestMatcher@57f59036]]]
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking LogoutFilter (6/13) 
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.s.w.a.logout.LogoutFilter            : Did not match request to Ant 
[pattern='/logout', POST]
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking BearerTokenAuthenticationFilter (7/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] .s.r.w.a.BearerTokenAuthenticationFilter : Did not process request since did not find bearer token
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthenticationFilter (8/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AuthenticationFilter           : Did not match request to org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.DPoPAuthenticationConfigurer$DPoPRequestMatcher@29600405
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking RequestCacheAwareFilter (9/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.s.w.s.HttpSessionRequestCache        : matchingRequestParameterName 
is required for getMatchingRequest to lookup a value, but not provided
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking SecurityContextHolderAwareRequestFilter (10/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AnonymousAuthenticationFilter (11/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking ExceptionTranslationFilter (12/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.security.web.FilterChainProxy        : Invoking AuthorizationFilter 
(13/13)
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Authorizing GET /users       
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] estMatcherDelegatingAuthorizationManager : Checking authorization on GET /users using org.springframework.security.authorization.AuthenticatedAuthorizationManager@37aa7ede
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] w.c.HttpSessionSecurityContextRepository : Retrieved SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]] from SPRING_SECURITY_CONTEXT
2026-03-13T17:33:58.656-04:00 TRACE 10900 --- [           main] o.s.s.w.a.AnonymousAuthenticationFilter  : Did not set SecurityContextHolder since already authenticated UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_USER]]
2026-03-13T17:33:58.656-04:00 DEBUG 10900 --- [           main] o.s.security.web.FilterChainProxy        : Secured GET /users
2026-03-13T17:33:58.659-04:00 TRACE 10900 --- [           main] o.s.s.w.header.writers.HstsHeaderWriter  : Not injecting HSTS header since it did not match request to [Is Secure]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.986 s -- in org.nnnn.ddd.service.UsersControllerTest
[INFO] 
[INFO] Results:
[INFO]
[ERROR] Failures: 
[ERROR]   ReferenceDataServiceTest.getdddOffices_nonSupervisorWithSGAuthority_returnsFilteredOffices:299 expected: <1> but was: <0>     
[INFO]
[ERROR] Tests run: 97, Failures: 1, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  29.087 s
[INFO] Finished at: 2026-03-13T17:34:00-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.3:test (default-test) on project ddd-services: There are test failures.
[ERROR]
[ERROR] See F:\project\ddd-services\target\surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
