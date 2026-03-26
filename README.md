@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)  // ← add this line
@DisplayName("AuthenticationService Tests")
class AuthenticationServiceTest {



import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@Test
@DisplayName("getUsername - returns null when principal is not a JWT")
void getUsername_nonJwtPrincipal_returnsNull() {
    Authentication auth = mock(Authentication.class);
    when(auth.getPrincipal()).thenReturn("simpleStringPrincipal");
    // ❌ Remove this line:
    // when(auth.getAuthorities()).thenReturn(Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(auth);

    String result = authenticationService.getUsername();

    assertThat(result).isNull();
}
