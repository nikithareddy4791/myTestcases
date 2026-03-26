14:import org.springframework.ldap.core.LdapTemplate;
31:    private LdapTemplate ldapTemplate;
41:        ReflectionTestUtils.setField(adSearchServiceProd,"ldapTemplate",ldapTemplate);
61:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
73:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
87:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
96:    @DisplayName("findUser - calls ldapTemplate.search exactly once")
98:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
103:        verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
113:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
120:        verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
126:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
137:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
143:        verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
153:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
159:        verify(ldapTemplate, times(8)).search(any(LdapQuery.class), any(AttributesMapper.class));
165:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
180:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
192:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
201:        verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
210:        verifyNoInteractions(ldapTemplate);
216:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
225:    @DisplayName("findMembersOfGroup - calls ldapTemplate.search exactly once per call")
227:        when(ldapTemplate.search(ArgumentMatchers.<LdapQuery>any(),ArgumentMatchers.<AttributesMapper<User>>any()))
232:        verify(ldapTemplate, times(1)).search(any(LdapQuery.class), any(AttributesMapper.class));
236:    // @DisplayName("debug - verify ldapTemplate is injected?")
237:    // void debug_verifyLdapTemplateInjected(){
238:    //     Object injected= ReflectionTestUtils.getField(adSearchServiceProd, "ldapTemplate");
239:    //     assertThat(injected).isSameAs(ldapTemplate);
