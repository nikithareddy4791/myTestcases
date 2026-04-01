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
 * Covers lambda$findUser$0 and lambda$findMembersOfGroup$0 by capturing
 * the AttributesMapper via ArgumentCaptor and invoking it directly.
 *
 * Root cause of UnfinishedStubbingException: calling attr() — which itself
 * opens a when(...).thenReturn(...) — *inside* another when(...).thenReturn(...)
 * confuses Mockito's stubbing state machine. Fix: fully build all Attribute
 * mocks first, then stub Attributes separately with already-resolved values.
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

    /**
     * Build and fully stub an Attribute mock before returning it.
     * Never call this inline inside another when(...).thenReturn(...).
     */
    private Attribute attr(String value) throws NamingException {
        Attribute a = mock(Attribute.class);
        when(a.get()).thenReturn(value);
        return a;
    }

    /**
     * Build an Attributes mock safely:
     *   1. Create all Attribute mocks and finish stubbing them.
     *   2. Only then open when(attrs.get(...)) stubs — all values are
     *      already resolved, so no stubbing is nested inside another.
     *
     * Pass null for any optional field to simulate absence from LDAP.
     */
    private Attributes buildAttributes(
            String sAMAccountName,
            String givenName,
            String sn,
            String telephoneNumber,
            String mail,
            String nnnnRank,
            String title,
            String nnnnTaxId,
            String nnnnCmdCode
    ) throws NamingException {

        // Step 1 — build every Attribute mock independently (no Attributes mock open yet)
        Attribute attrSam   = attr(sAMAccountName);
        Attribute attrGiven = givenName       != null ? attr(givenName)       : null;
        Attribute attrSn    = sn              != null ? attr(sn)              : null;
        Attribute attrPhone = telephoneNumber != null ? attr(telephoneNumber) : null;
        Attribute attrMail  = mail            != null ? attr(mail)            : null;
        Attribute attrRank  = nnnnRank        != null ? attr(nnnnRank)        : null;
        Attribute attrTitle = title           != null ? attr(title)           : null;
        Attribute attrTax   = nnnnTaxId       != null ? attr(nnnnTaxId)       : null;
        Attribute attrCmd   = nnnnCmdCode     != null ? attr(nnnnCmdCode)     : null;

        // Step 2 — now stub Attributes; all values are plain references, not nested stubs
        Attributes attrs = mock(Attributes.class);
        when(attrs.get("sAMAccountName")).thenReturn(attrSam);
        when(attrs.get("givenName")).thenReturn(attrGiven);
        when(attrs.get("sn")).thenReturn(attrSn);
        when(attrs.get("telephoneNumber")).thenReturn(attrPhone);
        when(attrs.get("mail")).thenReturn(attrMail);
        when(attrs.get("NNNNrank")).thenReturn(attrRank);
        when(attrs.get("title")).thenReturn(attrTitle);
        when(attrs.get("NNNNtaxid")).thenReturn(attrTax);
        when(attrs.get("NNNNcmdcode")).thenReturn(attrCmd);

        return attrs;
    }

    /**
     * Triggers findUser() so Mockito captures the AttributesMapper lambda,
     * then invokes the lambda directly with the supplied Attributes.
     */
    @SuppressWarnings("unchecked")
    private User invokeFindUserMapper(Attributes attrs) throws NamingException {
        ArgumentCaptor<AttributesMapper<User>> captor = ArgumentCaptor.forClass(AttributesMapper.class);
        when(ldapTemplate.search(any(LdapQuery.class), captor.capture())).thenReturn(List.of());

        service.findUser("jdoe");

        return captor.getValue().mapFromAttributes(attrs);
    }

    /**
     * Triggers findMembersOfGroup() so Mockito captures the AttributesMapper lambda,
     * then invokes the lambda directly with the supplied Attributes.
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
    // lambda$findUser$0
    // =========================================================================

    @Nested
    @DisplayName("lambda$findUser$0 (AttributesMapper inside findUser)")
    class FindUserMapperTests {

        @Test
        @DisplayName("maps all present attributes to User fields correctly")
        void findUserMapper_allAttributesPresent_mapsCorrectly() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", "Detective", "Analyst", "TAX001", "CMD01"
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
        @DisplayName("null telephoneNumber maps to empty string")
        void findUserMapper_nullTelephoneNumber_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    null, "jdoe@nnnn.finest", "Detective", "Analyst", "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getMobile()).isEmpty();
        }

        @Test
        @DisplayName("null mail maps to empty string")
        void findUserMapper_nullMail_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", null, "Detective", "Analyst", "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getEmail()).isEmpty();
        }

        @Test
        @DisplayName("null NNNNrank maps to empty string")
        void findUserMapper_nullRank_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", null, "Analyst", "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getRank()).isEmpty();
        }

        @Test
        @DisplayName("null title maps to empty string")
        void findUserMapper_nullTitle_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", "Detective", null, "TAX001", "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getTitle()).isEmpty();
        }

        @Test
        @DisplayName("null NNNNtaxid maps to empty string")
        void findUserMapper_nullTaxId_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", "Detective", "Analyst", null, "CMD01"
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getTax()).isEmpty();
        }

        @Test
        @DisplayName("null NNNNcmdcode maps to empty string")
        void findUserMapper_nullCmdCode_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "jdoe", "Jane", "Doe",
                    "555-9876", "jdoe@nnnn.finest", "Detective", "Analyst", "TAX001", null
            );

            User result = invokeFindUserMapper(attrs);

            assertThat(result.getCmdCode()).isEmpty();
        }

        @Test
        @DisplayName("all optional attributes null map to empty strings")
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
    // lambda$findMembersOfGroup$0
    // =========================================================================

    @Nested
    @DisplayName("lambda$findMembersOfGroup$0 (AttributesMapper inside findMembersOfGroup)")
    class FindMembersOfGroupMapperTests {

        @Test
        @DisplayName("maps all present attributes to User fields correctly")
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
        @DisplayName("null givenName maps to empty string")
        void findMembersMapper_nullGivenName_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "mbr1", null, "One",
                    null, null, null, null, null, null
            );

            User result = invokeFindMembersMapper(attrs);

            assertThat(result.getFirstName()).isEmpty();
        }

        @Test
        @DisplayName("null sn maps to empty string")
        void findMembersMapper_nullSn_mapsToEmptyString() throws NamingException {
            Attributes attrs = buildAttributes(
                    "mbr1", "Member", null,
                    null, null, null, null, null, null
            );

            User result = invokeFindMembersMapper(attrs);

            assertThat(result.getLastName()).isEmpty();
        }

        @Test
        @DisplayName("all optional attributes null map to empty strings")
        void findMembersMapper_allOptionalAttributesNull_allEmptyStrings() throws NamingException {
            Attributes attrs = buildAttributes(
                    "mbr1", null, null,
                    null, null, null, null, null, null
            );

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
