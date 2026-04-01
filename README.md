package org.nnnn.ddd.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnnn.ddd.model.User;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Covers the AttributesMapper lambdas (lambda$findUser$0 and
 * lambda$findMembersOfGroup$0) by capturing the mapper Mockito
 * would otherwise short-circuit, then invoking it directly with
 * a stubbed Attributes object.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ADSearchServiceProd – AttributesMapper lambda coverage")
class ADSearchServiceProdAttributesMappingTest {

    @Mock
    private LdapTemplate ldapTemplate;

    @InjectMocks
    private ADSearchServiceProd service;

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Builds a mock Attribute whose .get() returns the given value. */
    private Attribute attr(String value) throws NamingException {
        Attribute a = mock(Attribute.class);
        when(a.get()).thenReturn(value);
        return a;
    }

    /**
     * Builds a fully-populated Attributes mock.
     * Pass null for any optional attribute to simulate it being absent from LDAP.
     */
    private Attributes buildAttributes(
            String sAMAccountName,
            String givenName,
            String sn,
            String telephoneNumber,   // nullable
            String mail,              // nullable
            String nnnnRank,          // nullable
            String title,             // nullable
            String nnnnTaxId,         // nullable
            String nnnnCmdCode        // nullable
    ) throws NamingException {

        Attributes attrs = mock(Attributes.class);

        when(attrs.get("sAMAccountName")).thenReturn(attr(sAMAccountName));
        when(attrs.get("givenName")).thenReturn(attr(givenName));
        when(attrs.get("sn")).thenReturn(attr(sn));

        when(attrs.get("telephoneNumber")).thenReturn(telephoneNumber != null ? attr(telephoneNumber) : null);
        when(attrs.get("mail")).thenReturn(mail != null ? attr(mail) : null);
        when(attrs.get("NNNNrank")).thenReturn(nnnnRank != null ? attr(nnnnRank) : null);
        when(attrs.get("title")).thenReturn(title != null ? attr(title) : null);
        when(attrs.get("NNNNtaxid")).thenReturn(nnnnTaxId != null ? attr(nnnnTaxId) : null);
        when(attrs.get("NNNNcmdcode")).thenReturn(nnnnCmdCode != null ? attr(nnnnCmdCode) : null);

        return attrs;
    }

    /**
     * Captures the AttributesMapper passed to ldapTemplate.search() by findUser(),
     * then invokes it with the supplied Attributes so the lambda body executes.
     */
    @SuppressWarnings("unchecked")
    private User invokeFindUserMapper(Attributes attrs) throws NamingException {
        ArgumentCaptor<AttributesMapper<User>> captor = ArgumentCaptor.forClass(AttributesMapper.class);
        when(ldapTemplate.search(any(LdapQuery.class), captor.capture())).thenReturn(List.of());

        service.findUser("jdoe");

        return captor.getValue().mapFromAttributes(attrs);
    }

    /**
     * Captures the AttributesMapper passed to ldapTemplate.search() by
     * findMembersOfGroup(), then invokes it with the supplied Attributes.
     */
    @SuppressWarnings("unchecked")
    private User invokeFindMembersMapper(Attributes attrs) throws NamingException {
        ArgumentCaptor<AttributesMapper<User>> captor = ArgumentCaptor.forClass(AttributesMapper.class);
        when(ldapTemplate.search(any(LdapQuery.class), captor.capture())).thenReturn(List.of());

        service.findMembersOfGroup(
                "CN=SG-DDD-SUPERVISOR,OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"
        );

        return captor.getValue().mapFromAttributes(attrs);
    }

    // =========================================================================
    // lambda$findUser$0 – AttributesMapper inside findUser()
    // =========================================================================

    @Nested
    @DisplayName("lambda$findUser$0 (AttributesMapper inside findUser)")
    class FindUserMapperTests {

        @Test
        @DisplayName("maps all present attributes to User fields")
        void findUserMapper_allAttributesPresent_mapsCorrectly() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest",
                    "Detective", "Analyst", "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getUsername()).isEqualTo("jdoe");
            assertThat(result.getFirstName()).isEqualTo("Jane");
            assertThat(result.getLastName()).isEqualTo("Doe");
            assertThat(result.getMobile()).isEqualTo("555-9876");
            assertThat(result.getEmail()).isEqualTo("jdoe@nnnn.finest");
            assertThat(result.getRank()).isEqualTo("Detective");
            assertThat(result.getTitle()).isEqualTo("Analyst");
            assertThat(result.getTax()).isEqualTo("TAX001");
            assertThat(result.getCmdCode()).isEqualTo("CMD01");
        }

        @Test
        @DisplayName("maps null telephoneNumber to empty string")
        void findUserMapper_nullTelephoneNumber_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    null, "jdoe@nnnn.finest", "Detective", "Analyst", "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getMobile()).isEmpty();
        }

        @Test
        @DisplayName("maps null mail to empty string")
        void findUserMapper_nullMail_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", null, "Detective", "Analyst", "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getEmail()).isEmpty();
        }

        @Test
        @DisplayName("maps null NNNNrank to empty string")
        void findUserMapper_nullRank_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", null, "Analyst", "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getRank()).isEmpty();
        }

        @Test
        @DisplayName("maps null title to empty string")
        void findUserMapper_nullTitle_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", "Detective", null, "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getTitle()).isEmpty();
        }

        @Test
        @DisplayName("maps null NNNNtaxid to empty string")
        void findUserMapper_nullTaxId_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", "Detective", "Analyst", null, "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getTax()).isEmpty();
        }

        @Test
        @DisplayName("maps null NNNNcmdcode to empty string")
        void findUserMapper_nullCmdCode_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", "Detective", "Analyst", "TAX001", null
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getCmdCode()).isEmpty();
        }

        @Test
        @DisplayName("maps all optional attributes null to empty strings")
        void findUserMapper_allOptionalAttributesNull_allEmptyStrings() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    null, null, null, null, null, null
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getUsername()).isEqualTo("jdoe");
            assertThat(result.getMobile()).isEmpty();
            assertThat(result.getEmail()).isEmpty();
            assertThat(result.getRank()).isEmpty();
            assertThat(result.getTitle()).isEmpty();
            assertThat(result.getTax()).isEmpty();
            assertThat(result.getCmdCode()).isEmpty();
        }
    }

    // =========================================================================
    // lambda$findMembersOfGroup$0 – AttributesMapper inside findMembersOfGroup()
    // =========================================================================

    @Nested
    @DisplayName("lambda$findMembersOfGroup$0 (AttributesMapper inside findMembersOfGroup)")
    class FindMembersOfGroupMapperTests {

        @Test
        @DisplayName("maps all present attributes to User fields")
        void findMembersMapper_allAttributesPresent_mapsCorrectly() throws NamingException {
            Attributes attrs = buildAttributes(
                    "mbr1", "Member", "One",
                    "555-1111", "mbr1@nnnn.finest", "Officer", "Supervisor", "TAX002", "CMD02"
            );

            User result = invokeFindMembersMapper(attrs);

            assertThat(result.getUsername()).isEqualTo("mbr1");
            assertThat(result.getFirstName()).isEqualTo("Member");
            assertThat(result.getLastName()).isEqualTo("One");
            assertThat(result.getMobile()).isEqualTo("555-1111");
            assertThat(result.getEmail()).isEqualTo("mbr1@nnnn.finest");
            assertThat(result.getRank()).isEqualTo("Officer");
            assertThat(result.getTitle()).isEqualTo("Supervisor");
            assertThat(result.getTax()).isEqualTo("TAX002");
            assertThat(result.getCmdCode()).isEqualTo("CMD02");
        }

        @Test
        @DisplayName("maps null givenName to empty string")
        void findMembersMapper_nullGivenName_mapsToEmptyString() throws NamingException {
            Attributes attrs = mock(Attributes.class);
            when(attrs.get("sAMAccountName")).thenReturn(attr("mbr1"));
            when(attrs.get("givenName")).thenReturn(null);
            when(attrs.get("sn")).thenReturn(attr("One"));
            when(attrs.get("telephoneNumber")).thenReturn(null);
            when(attrs.get("mail")).thenReturn(null);
            when(attrs.get("NNNNrank")).thenReturn(null);
            when(attrs.get("title")).thenReturn(null);
            when(attrs.get("NNNNtaxid")).thenReturn(null);
            when(attrs.get("NNNNcmdcode")).thenReturn(null);

            User result = invokeFindMembersMapper(attrs);

            assertThat(result.getFirstName()).isEmpty();
        }

        @Test
        @DisplayName("maps null sn to empty string")
        void findMembersMapper_nullSn_mapsToEmptyString() throws NamingException {
            Attributes attrs = mock(Attributes.class);
            when(attrs.get("sAMAccountName")).thenReturn(attr("mbr1"));
            when(attrs.get("givenName")).thenReturn(attr("Member"));
            when(attrs.get("sn")).thenReturn(null);
            when(attrs.get("telephoneNumber")).thenReturn(null);
            when(attrs.get("mail")).thenReturn(null);
            when(attrs.get("NNNNrank")).thenReturn(null);
            when(attrs.get("title")).thenReturn(null);
            when(attrs.get("NNNNtaxid")).thenReturn(null);
            when(attrs.get("NNNNcmdcode")).thenReturn(null);

            User result = invokeFindMembersMapper(attrs);

            assertThat(result.getLastName()).isEmpty();
        }

        @Test
        @DisplayName("maps all optional attributes null to empty strings")
        void findMembersMapper_allOptionalAttributesNull_allEmptyStrings() throws NamingException {
            Attributes attrs = mock(Attributes.class);
            when(attrs.get("sAMAccountName")).thenReturn(attr("mbr1"));
            when(attrs.get("givenName")).thenReturn(null);
            when(attrs.get("sn")).thenReturn(null);
            when(attrs.get("telephoneNumber")).thenReturn(null);
            when(attrs.get("mail")).thenReturn(null);
            when(attrs.get("NNNNrank")).thenReturn(null);
            when(attrs.get("title")).thenReturn(null);
            when(attrs.get("NNNNtaxid")).thenReturn(null);
            when(attrs.get("NNNNcmdcode")).thenReturn(null);

            User result = invokeFindMembersMapper(attrs);

            assertThat(result.getUsername()).isEqualTo("mbr1");
            assertThat(result.getFirstName()).isEmpty();
            assertThat(result.getLastName()).isEmpty();
            assertThat(result.getMobile()).isEmpty();
            assertThat(result.getEmail()).isEmpty();
            assertThat(result.getRank()).isEmpty();
            assertThat(result.getTitle()).isEmpty();
            assertThat(result.getTax()).isEmpty();
            assertThat(result.getCmdCode()).isEmpty();
        }
    }
}
