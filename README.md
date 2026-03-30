package io.swagger.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CustomInstantDeserializer Tests")
class CustomInstantDeserializerTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        ThreeTenModule module = new ThreeTenModule();
        module.addDeserializer(Instant.class, CustomInstantDeserializer.INSTANT);
        module.addDeserializer(OffsetDateTime.class, CustomInstantDeserializer.OFFSET_DATE_TIME);
        module.addDeserializer(ZonedDateTime.class, CustomInstantDeserializer.ZONED_DATE_TIME);
        objectMapper.registerModule(module);
    }

    // -------------------------------------------------------------------------
    // Static constant sanity checks
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("INSTANT static constant should not be null")
    void instantConstantShouldNotBeNull() {
        assertNotNull(CustomInstantDeserializer.INSTANT);
    }

    @Test
    @DisplayName("OFFSET_DATE_TIME static constant should not be null")
    void offsetDateTimeConstantShouldNotBeNull() {
        assertNotNull(CustomInstantDeserializer.OFFSET_DATE_TIME);
    }

    @Test
    @DisplayName("ZONED_DATE_TIME static constant should not be null")
    void zonedDateTimeConstantShouldNotBeNull() {
        assertNotNull(CustomInstantDeserializer.ZONED_DATE_TIME);
    }

    // -------------------------------------------------------------------------
    // Instant deserialization
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Instant Deserialization")
    class InstantDeserializationTests {

        @Test
        @DisplayName("Should deserialize ISO-8601 UTC string to Instant")
        void shouldDeserializeIsoUtcStringToInstant() throws Exception {
            String json = "\"2024-06-15T10:30:00Z\"";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
        }

        @Test
        @DisplayName("Should deserialize epoch millis (integer) to Instant")
        void shouldDeserializeEpochMillisToInstant() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
            long epochMillis = 1718447400000L;
            String json = String.valueOf(epochMillis);
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.ofEpochMilli(epochMillis), result);
        }

        @Test
        @DisplayName("Should deserialize epoch seconds (float) to Instant")
        void shouldDeserializeEpochSecondsFloatToInstant() throws Exception {
            String json = "1718447400.5";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(1718447400L, result.getEpochSecond());
        }

        @Test
        @DisplayName("Should return null for empty string")
        void shouldReturnNullForEmptyString() throws Exception {
            String json = "\"\"";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNull(result);
        }

        @Test
        @DisplayName("Should handle +0000 suffix by converting to Z")
        void shouldHandlePlusZeroTimezone() throws Exception {
            // +0000 is normalized to Z inside the deserializer
            String json = "\"2024-06-15T10:30:00+0000\"";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
        }

        @Test
        @DisplayName("Should deserialize epoch as nanoseconds when feature is enabled")
        void shouldDeserializeEpochAsNanosecondsWhenFeatureEnabled() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
            long epochSeconds = 1718447400L;
            String json = String.valueOf(epochSeconds);
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.ofEpochSecond(epochSeconds, 0), result);
        }
    }

    // -------------------------------------------------------------------------
    // OffsetDateTime deserialization
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("OffsetDateTime Deserialization")
    class OffsetDateTimeDeserializationTests {

        @Test
        @DisplayName("Should deserialize ISO offset date-time string")
        void shouldDeserializeIsoOffsetDateTimeString() throws Exception {
            String json = "\"2024-06-15T10:30:00+05:30\"";
            OffsetDateTime result = objectMapper.readValue(json, OffsetDateTime.class);
            assertNotNull(result);
            assertEquals(OffsetDateTime.parse("2024-06-15T10:30:00+05:30"), result);
        }

        @Test
        @DisplayName("Should deserialize epoch millis to OffsetDateTime")
        void shouldDeserializeEpochMillisToOffsetDateTime() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
            long epochMillis = 1718447400000L;
            OffsetDateTime result = objectMapper.readValue(String.valueOf(epochMillis), OffsetDateTime.class);
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should deserialize epoch seconds (float) to OffsetDateTime")
        void shouldDeserializeEpochSecondsFloatToOffsetDateTime() throws Exception {
            String json = "1718447400.0";
            OffsetDateTime result = objectMapper.readValue(json, OffsetDateTime.class);
            assertNotNull(result);
            assertEquals(1718447400L, result.toEpochSecond());
        }

        @Test
        @DisplayName("Should return null for empty string")
        void shouldReturnNullForEmptyStringOffsetDateTime() throws Exception {
            String json = "\"\"";
            OffsetDateTime result = objectMapper.readValue(json, OffsetDateTime.class);
            assertNull(result);
        }
    }

    // -------------------------------------------------------------------------
    // ZonedDateTime deserialization
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("ZonedDateTime Deserialization")
    class ZonedDateTimeDeserializationTests {

        @Test
        @DisplayName("Should deserialize ISO zoned date-time string")
        void shouldDeserializeIsoZonedDateTimeString() throws Exception {
            String json = "\"2024-06-15T10:30:00+05:30[Asia/Kolkata]\"";
            ZonedDateTime result = objectMapper.readValue(json, ZonedDateTime.class);
            assertNotNull(result);
            assertEquals("Asia/Kolkata", result.getZone().getId());
        }

        @Test
        @DisplayName("Should deserialize epoch millis to ZonedDateTime")
        void shouldDeserializeEpochMillisToZonedDateTime() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
            long epochMillis = 1718447400000L;
            ZonedDateTime result = objectMapper.readValue(String.valueOf(epochMillis), ZonedDateTime.class);
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should deserialize decimal seconds to ZonedDateTime")
        void shouldDeserializeDecimalSecondsToZonedDateTime() throws Exception {
            String json = "1718447400.0";
            ZonedDateTime result = objectMapper.readValue(json, ZonedDateTime.class);
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should return null for empty string")
        void shouldReturnNullForEmptyStringZonedDateTime() throws Exception {
            String json = "\"\"";
            ZonedDateTime result = objectMapper.readValue(json, ZonedDateTime.class);
            assertNull(result);
        }
    }

    // -------------------------------------------------------------------------
    // Error cases
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Error Handling")
    class ErrorHandlingTests {

        @Test
        @DisplayName("Should throw exception for invalid date-time string for Instant")
        void shouldThrowExceptionForInvalidInstantString() {
            String json = "\"not-a-date\"";
            assertThrows(Exception.class, () -> objectMapper.readValue(json, Instant.class));
        }

        @Test
        @DisplayName("Should throw exception for invalid date-time string for OffsetDateTime")
        void shouldThrowExceptionForInvalidOffsetDateTimeString() {
            String json = "\"not-a-date\"";
            assertThrows(Exception.class, () -> objectMapper.readValue(json, OffsetDateTime.class));
        }

        @Test
        @DisplayName("Should throw exception for invalid date-time string for ZonedDateTime")
        void shouldThrowExceptionForInvalidZonedDateTimeString() {
            String json = "\"not-a-date\"";
            assertThrows(Exception.class, () -> objectMapper.readValue(json, ZonedDateTime.class));
        }
    }
}




@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


package io.swagger.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SwaggerDocumentationConfig Tests")
class SwaggerDocumentationConfigTest {

    private SwaggerDocumentationConfig config;

    @BeforeEach
    void setUp() {
        config = new SwaggerDocumentationConfig();
    }

    @Test
    @DisplayName("openApi() bean should not return null")
    void openApiBeanShouldNotBeNull() {
        OpenAPI openAPI = config.openApi();
        assertNotNull(openAPI);
    }

    @Test
    @DisplayName("OpenAPI info should not be null")
    void openApiInfoShouldNotBeNull() {
        OpenAPI openAPI = config.openApi();
        assertNotNull(openAPI.getInfo());
    }

    @Test
    @DisplayName("OpenAPI title should match expected value")
    void openApiTitleShouldMatch() {
        Info info = config.openApi().getInfo();
        assertEquals("Swagger Petstore - OpenAPI 3.0", info.getTitle());
    }

    @Test
    @DisplayName("OpenAPI version should match expected value")
    void openApiVersionShouldMatch() {
        Info info = config.openApi().getInfo();
        assertEquals("1.0.12", info.getVersion());
    }

    @Test
    @DisplayName("OpenAPI description should not be null or empty")
    void openApiDescriptionShouldNotBeNullOrEmpty() {
        Info info = config.openApi().getInfo();
        assertNotNull(info.getDescription());
        assertFalse(info.getDescription().isEmpty());
    }

    @Test
    @DisplayName("OpenAPI terms of service should be empty string")
    void openApiTermsOfServiceShouldBeEmptyString() {
        Info info = config.openApi().getInfo();
        assertEquals("", info.getTermsOfService());
    }

    @Test
    @DisplayName("OpenAPI license should not be null")
    void openApiLicenseShouldNotBeNull() {
        Info info = config.openApi().getInfo();
        assertNotNull(info.getLicense());
    }

    @Test
    @DisplayName("OpenAPI license name should be Apache 2.0")
    void openApiLicenseNameShouldBeApache() {
        License license = config.openApi().getInfo().getLicense();
        assertEquals("Apache 2.0", license.getName());
    }

    @Test
    @DisplayName("OpenAPI license URL should point to Apache license")
    void openApiLicenseUrlShouldMatch() {
        License license = config.openApi().getInfo().getLicense();
        assertEquals("https://www.apache.org/licenses/LICENSE-2.0.html", license.getUrl());
    }

    @Test
    @DisplayName("OpenAPI contact should not be null")
    void openApiContactShouldNotBeNull() {
        Info info = config.openApi().getInfo();
        assertNotNull(info.getContact());
    }

    @Test
    @DisplayName("OpenAPI contact email should match expected value")
    void openApiContactEmailShouldMatch() {
        Info info = config.openApi().getInfo();
        assertEquals("apiteam@swagger.io", info.getContact().getEmail());
    }

    @Test
    @DisplayName("openApi() should return a new instance on each call")
    void openApiShouldReturnNewInstanceOnEachCall() {
        OpenAPI first = config.openApi();
        OpenAPI second = config.openApi();
        assertNotSame(first, second);
    }
}



@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package io.swagger.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SwaggerUiConfiguration Tests")
class SwaggerUiConfigurationTest {

    private SwaggerUiConfiguration swaggerUiConfiguration;

    @Mock
    private ResourceHandlerRegistry resourceHandlerRegistry;

    @Mock
    private ResourceHandlerRegistration resourceHandlerRegistration;

    @Mock
    private ResourceChainRegistration resourceChainRegistration;

    @Mock
    private ViewControllerRegistry viewControllerRegistry;

    @Mock
    private ViewControllerRegistration viewControllerRegistration;

    @BeforeEach
    void setUp() {
        swaggerUiConfiguration = new SwaggerUiConfiguration();
    }

    @Test
    @DisplayName("addResourceHandlers should register /swagger-ui/** handler")
    void shouldRegisterSwaggerUiResourceHandler() {
        when(resourceHandlerRegistry.addResourceHandler("/swagger-ui/**"))
                .thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.addResourceLocations(anyString()))
                .thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.resourceChain(false))
                .thenReturn(resourceChainRegistration);

        swaggerUiConfiguration.addResourceHandlers(resourceHandlerRegistry);

        verify(resourceHandlerRegistry).addResourceHandler("/swagger-ui/**");
    }

    @Test
    @DisplayName("addResourceHandlers should set correct classpath resource location")
    void shouldSetCorrectResourceLocation() {
        when(resourceHandlerRegistry.addResourceHandler("/swagger-ui/**"))
                .thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.addResourceLocations(anyString()))
                .thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.resourceChain(false))
                .thenReturn(resourceChainRegistration);

        swaggerUiConfiguration.addResourceHandlers(resourceHandlerRegistry);

        verify(resourceHandlerRegistration)
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }

    @Test
    @DisplayName("addResourceHandlers should disable resource chain caching")
    void shouldDisableResourceChain() {
        when(resourceHandlerRegistry.addResourceHandler("/swagger-ui/**"))
                .thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.addResourceLocations(anyString()))
                .thenReturn(resourceHandlerRegistration);
        when(resourceHandlerRegistration.resourceChain(false))
                .thenReturn(resourceChainRegistration);

        swaggerUiConfiguration.addResourceHandlers(resourceHandlerRegistry);

        verify(resourceHandlerRegistration).resourceChain(false);
    }

    @Test
    @DisplayName("addViewControllers should register /swagger-ui/ view controller")
    void shouldRegisterSwaggerUiViewController() {
        when(viewControllerRegistry.addViewController("/swagger-ui/"))
                .thenReturn(viewControllerRegistration);

        swaggerUiConfiguration.addViewControllers(viewControllerRegistry);

        verify(viewControllerRegistry).addViewController("/swagger-ui/");
    }

    @Test
    @DisplayName("addViewControllers should forward /swagger-ui/ to index.html")
    void shouldForwardSwaggerUiToIndexHtml() {
        when(viewControllerRegistry.addViewController("/swagger-ui/"))
                .thenReturn(viewControllerRegistration);

        swaggerUiConfiguration.addViewControllers(viewControllerRegistry);

        verify(viewControllerRegistration).setViewName("forward:/swagger-ui/index.html");
    }

    @Test
    @DisplayName("SwaggerUiConfiguration should implement WebMvcConfigurer")
    void shouldImplementWebMvcConfigurer() {
        assertInstanceOf(
                org.springframework.web.servlet.config.annotation.WebMvcConfigurer.class,
                swaggerUiConfiguration
        );
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package io.swagger.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JacksonConfiguration Tests")
class JacksonConfigurationTest {

    private JacksonConfiguration jacksonConfiguration;

    @BeforeEach
    void setUp() {
        jacksonConfiguration = new JacksonConfiguration();
    }

    @Test
    @DisplayName("threeTenModule() bean should not return null")
    void threeTenModuleShouldNotBeNull() {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        assertNotNull(module);
    }

    @Test
    @DisplayName("threeTenModule() should return a ThreeTenModule instance")
    void shouldReturnThreeTenModuleInstance() {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        assertInstanceOf(ThreeTenModule.class, module);
    }

    @Test
    @DisplayName("Module should register Instant deserializer — Instant string is parseable")
    void moduleShouldRegisterInstantDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        String json = "\"2024-06-15T10:30:00Z\"";
        Instant result = mapper.readValue(json, Instant.class);

        assertNotNull(result);
        assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
    }

    @Test
    @DisplayName("Module should register OffsetDateTime deserializer — OffsetDateTime string is parseable")
    void moduleShouldRegisterOffsetDateTimeDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        String json = "\"2024-06-15T10:30:00+05:30\"";
        OffsetDateTime result = mapper.readValue(json, OffsetDateTime.class);

        assertNotNull(result);
        assertEquals(OffsetDateTime.parse("2024-06-15T10:30:00+05:30"), result);
    }

    @Test
    @DisplayName("Module should register ZonedDateTime deserializer — ZonedDateTime string is parseable")
    void moduleShouldRegisterZonedDateTimeDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        String json = "\"2024-06-15T10:30:00+05:30[Asia/Kolkata]\"";
        ZonedDateTime result = mapper.readValue(json, ZonedDateTime.class);

        assertNotNull(result);
        assertEquals("Asia/Kolkata", result.getZone().getId());
    }

    @Test
    @DisplayName("threeTenModule() should return a new instance on each invocation")
    void shouldReturnNewInstanceOnEachCall() {
        ThreeTenModule first = jacksonConfiguration.threeTenModule();
        ThreeTenModule second = jacksonConfiguration.threeTenModule();
        assertNotSame(first, second);
    }

    @Test
    @DisplayName("Registered Instant deserializer should be CustomInstantDeserializer.INSTANT")
    void instantDeserializerShouldBeCustomInstantDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        // Verify the custom +0000 → Z normalization behaviour, which is unique
        // to CustomInstantDeserializer (proving it — not the default — was registered).
        String json = "\"2024-06-15T10:30:00+0000\"";
        Instant result = mapper.readValue(json, Instant.class);
        assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


package io.swagger.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.threeten.bp.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LocalDateTimeConverter Tests")
class LocalDateTimeConverterTest {

    // -------------------------------------------------------------------------
    // Happy-path conversions
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Valid Conversions")
    class ValidConversionTests {

        @Test
        @DisplayName("Should convert a standard ISO-like pattern yyyy-MM-dd HH:mm:ss")
        void shouldConvertStandardPattern() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            LocalDateTime result = converter.convert("2024-06-15 10:30:45");
            assertNotNull(result);
            assertEquals(2024, result.getYear());
            assertEquals(6, result.getMonthValue());
            assertEquals(15, result.getDayOfMonth());
            assertEquals(10, result.getHour());
            assertEquals(30, result.getMinute());
            assertEquals(45, result.getSecond());
        }

        @Test
        @DisplayName("Should convert date-only pattern yyyy-MM-dd with zero time")
        void shouldConvertDateOnlyPattern() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            LocalDateTime result = converter.convert("2024-01-01 00:00:00");
            assertNotNull(result);
            assertEquals(LocalDateTime.of(2024, 1, 1, 0, 0, 0), result);
        }

        @Test
        @DisplayName("Should convert with slashed date pattern dd/MM/yyyy HH:mm")
        void shouldConvertSlashedDatePattern() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("dd/MM/yyyy HH:mm");
            LocalDateTime result = converter.convert("15/06/2024 10:30");
            assertNotNull(result);
            assertEquals(2024, result.getYear());
            assertEquals(6, result.getMonthValue());
            assertEquals(15, result.getDayOfMonth());
        }

        @Test
        @DisplayName("Should convert leap-day date correctly")
        void shouldConvertLeapDayDate() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            LocalDateTime result = converter.convert("2024-02-29 12:00:00");
            assertNotNull(result);
            assertEquals(29, result.getDayOfMonth());
            assertEquals(2, result.getMonthValue());
        }

        @Test
        @DisplayName("Should convert end-of-day time boundary")
        void shouldConvertEndOfDayBoundary() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            LocalDateTime result = converter.convert("2024-12-31 23:59:59");
            assertNotNull(result);
            assertEquals(23, result.getHour());
            assertEquals(59, result.getMinute());
            assertEquals(59, result.getSecond());
        }
    }

    // -------------------------------------------------------------------------
    // Null / empty input
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Null and Empty Input Handling")
    class NullAndEmptyInputTests {

        @Test
        @DisplayName("Should return null when source is null")
        void shouldReturnNullForNullInput() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            assertNull(converter.convert(null));
        }

        @Test
        @DisplayName("Should return null when source is empty string")
        void shouldReturnNullForEmptyString() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            assertNull(converter.convert(""));
        }
    }

    // -------------------------------------------------------------------------
    // Invalid input
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Invalid Input Handling")
    class InvalidInputTests {

        @Test
        @DisplayName("Should throw exception for wrong format")
        void shouldThrowForWrongFormat() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            assertThrows(Exception.class, () -> converter.convert("not-a-date"));
        }

        @Test
        @DisplayName("Should throw exception for date-only string when time is required by pattern")
        void shouldThrowForDateOnlyWhenTimeRequired() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            assertThrows(Exception.class, () -> converter.convert("2024-06-15"));
        }

        @Test
        @DisplayName("Should throw exception for invalid month value")
        void shouldThrowForInvalidMonth() {
            LocalDateTimeConverter converter = new LocalDateTimeConverter("yyyy-MM-dd HH:mm:ss");
            assertThrows(Exception.class, () -> converter.convert("2024-13-01 10:00:00"));
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package io.swagger.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LocalDateConverter Tests")
class LocalDateConverterTest {

    // -------------------------------------------------------------------------
    // Happy-path conversions
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Valid Conversions")
    class ValidConversionTests {

        @Test
        @DisplayName("Should convert standard ISO date string yyyy-MM-dd")
        void shouldConvertIsoDateString() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-06-15");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 6, 15), result);
        }

        @Test
        @DisplayName("Should convert slashed date format dd/MM/yyyy")
        void shouldConvertSlashedDateFormat() {
            LocalDateConverter converter = new LocalDateConverter("dd/MM/yyyy");
            LocalDate result = converter.convert("15/06/2024");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 6, 15), result);
        }

        @Test
        @DisplayName("Should convert MM-dd-yyyy US format")
        void shouldConvertUsSeparatedFormat() {
            LocalDateConverter converter = new LocalDateConverter("MM-dd-yyyy");
            LocalDate result = converter.convert("06-15-2024");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 6, 15), result);
        }

        @Test
        @DisplayName("Should convert leap day February 29")
        void shouldConvertLeapDay() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-02-29");
            assertNotNull(result);
            assertEquals(29, result.getDayOfMonth());
            assertEquals(2, result.getMonthValue());
        }

        @Test
        @DisplayName("Should convert year boundary date December 31")
        void shouldConvertYearBoundaryDate() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-12-31");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 12, 31), result);
        }

        @Test
        @DisplayName("Should convert first day of year January 01")
        void shouldConvertFirstDayOfYear() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-01-01");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 1, 1), result);
        }

        @Test
        @DisplayName("Should convert date with short year pattern yy-MM-dd")
        void shouldConvertShortYearPattern() {
            LocalDateConverter converter = new LocalDateConverter("yy-MM-dd");
            LocalDate result = converter.convert("24-06-15");
            assertNotNull(result);
            assertEquals(6, result.getMonthValue());
            assertEquals(15, result.getDayOfMonth());
        }
    }

    // -------------------------------------------------------------------------
    // Null / empty input
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Null and Empty Input Handling")
    class NullAndEmptyInputTests {

        @Test
        @DisplayName("Should return null when source is null")
        void shouldReturnNullForNullInput() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertNull(converter.convert(null));
        }

        @Test
        @DisplayName("Should return null when source is empty string")
        void shouldReturnNullForEmptyString() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertNull(converter.convert(""));
        }
    }

    // -------------------------------------------------------------------------
    // Invalid input
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Invalid Input Handling")
    class InvalidInputTests {

        @Test
        @DisplayName("Should throw exception for completely invalid string")
        void shouldThrowForInvalidString() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("not-a-date"));
        }

        @Test
        @DisplayName("Should throw exception for wrong format pattern mismatch")
        void shouldThrowForFormatMismatch() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("15/06/2024"));
        }

        @Test
        @DisplayName("Should throw exception for invalid day value")
        void shouldThrowForInvalidDay() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("2024-06-32"));
        }

        @Test
        @DisplayName("Should throw exception for invalid month value")
        void shouldThrowForInvalidMonth() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("2024-13-01"));
        }

        @Test
        @DisplayName("Should throw exception for non-leap year February 29")
        void shouldThrowForNonLeapYearFeb29() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("2023-02-29"));
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package io.swagger.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("HomeController Tests")
class HomeControllerTest {

    private HomeController homeController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        homeController = new HomeController();
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    // -------------------------------------------------------------------------
    // Unit-level: return value
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("index() should return redirect to /swagger-ui/")
    void indexShouldReturnRedirectView() {
        String result = homeController.index();
        assertEquals("redirect:/swagger-ui/", result);
    }

    @Test
    @DisplayName("index() should not return null")
    void indexShouldNotReturnNull() {
        assertNotNull(homeController.index());
    }

    @Test
    @DisplayName("index() return value should start with 'redirect:'")
    void indexReturnValueShouldStartWithRedirect() {
        String result = homeController.index();
        assertTrue(result.startsWith("redirect:"));
    }

    // -------------------------------------------------------------------------
    // MockMvc-level: HTTP behaviour
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("GET / should return 3xx redirect response")
    void getHomeShouldReturnRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("GET / should redirect to /swagger-ui/")
    void getHomeShouldRedirectToSwaggerUi() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("/swagger-ui/"));
    }

    // -------------------------------------------------------------------------
    // Type check
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("HomeController should be annotated as a Spring Controller")
    void homeControllerShouldBeAnnotatedAsController() {
        assertNotNull(HomeController.class.getAnnotation(
                org.springframework.stereotype.Controller.class));
    }

    @Test
    @DisplayName("index() method should have @RequestMapping for '/'")
    void indexMethodShouldHaveRequestMapping() throws Exception {
        var annotation = HomeController.class
                .getMethod("index")
                .getAnnotation(org.springframework.web.bind.annotation.RequestMapping.class);
        assertNotNull(annotation);
        assertArrayEquals(new String[]{"/"}, annotation.value());
    }
}



