package com.ddd.controller;

import com.ddd.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@DisplayName("UserController Tests")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User sampleUser;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setUsername("jdoe");
        sampleUser.setFirstName("John");
        sampleUser.setLastName("Doe");
        sampleUser.setEmail("jdoe@example.com");
        sampleUser.setRank("Detective");
        sampleUser.setTitle("Senior ADA");
        sampleUser.setTax("123456");
        sampleUser.setCmdCode("CMD01");
        sampleUser.setMobile("555-1234");
        sampleUser.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));

        User secondUser = new User();
        secondUser.setUsername("asmith");
        secondUser.setFirstName("Alice");
        secondUser.setLastName("Smith");
        secondUser.setEmail("asmith@example.com");
        secondUser.setRoles(Collections.singletonList("ROLE_USER"));

        userList = Arrays.asList(sampleUser, secondUser);
    }

    // =========================================================
    // GET /users
    // =========================================================

    @Test
    @DisplayName("GET /users - should return list of all users")
    void getUserList_Success() throws Exception {
        when(userService.getUserList()).thenReturn(userList);

        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("jdoe")))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].email", is("jdoe@example.com")))
                .andExpect(jsonPath("$[1].username", is("asmith")));

        verify(userService, times(1)).getUserList();
    }

    @Test
    @DisplayName("GET /users - should return empty list when no users found")
    void getUserList_Empty() throws Exception {
        when(userService.getUserList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /users - should return 500 when AD service is unavailable")
    void getUserList_ADServiceUnavailable() throws Exception {
        when(userService.getUserList()).thenThrow(new RuntimeException("AD service unavailable"));

        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("GET /users - should return users with roles populated")
    void getUserList_WithRoles() throws Exception {
        when(userService.getUserList()).thenReturn(userList);

        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roles", hasSize(2)))
                .andExpect(jsonPath("$[0].roles", containsInAnyOrder("ROLE_USER", "ROLE_ADMIN")));
    }

    // =========================================================
    // GET /user/login
    // =========================================================

    @Test
    @DisplayName("GET /user/login - should login user successfully and return token")
    void loginUser_Success() throws Exception {
        when(userService.loginUser("jdoe")).thenReturn("mock-jwt-token");

        mockMvc.perform(get("/user/login")
                .param("username", "jdoe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-Rate-Limit"))
                .andExpect(header().exists("X-Expires-After"));

        verify(userService, times(1)).loginUser("jdoe");
    }

    @Test
    @DisplayName("GET /user/login - should return 400 for invalid username")
    void loginUser_InvalidUsername() throws Exception {
        when(userService.loginUser(anyString()))
                .thenThrow(new InvalidCredentialsException("Invalid username/password"));

        mockMvc.perform(get("/user/login")
                .param("username", "baduser")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /user/login - should succeed without username param (optional param)")
    void loginUser_WithoutUsernameParam() throws Exception {
        when(userService.loginUser(null)).thenReturn("anonymous-token");

        mockMvc.perform(get("/user/login")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // =========================================================
    // GET /user/logout
    // =========================================================

    @Test
    @DisplayName("GET /user/logout - should logout user successfully")
    void logoutUser_Success() throws Exception {
        doNothing().when(userService).logoutUser();

        mockMvc.perform(get("/user/logout")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).logoutUser();
    }

    @Test
    @DisplayName("GET /user/logout - should return 500 on service failure")
    void logoutUser_ServiceFailure() throws Exception {
        doThrow(new RuntimeException("Session service error")).when(userService).logoutUser();

        mockMvc.perform(get("/user/logout")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    // =========================================================
    // GET /user/{username}
    // =========================================================

    @Test
    @DisplayName("GET /user/{username} - should return user when found")
    void getUserByName_Success() throws Exception {
        when(userService.getUserByName("jdoe")).thenReturn(sampleUser);

        mockMvc.perform(get("/user/{username}", "jdoe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("jdoe")))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("jdoe@example.com")))
                .andExpect(jsonPath("$.tax", is("123456")));

        verify(userService, times(1)).getUserByName("jdoe");
    }

    @Test
    @DisplayName("GET /user/{username} - should return 404 when user not found")
    void getUserByName_NotFound() throws Exception {
        when(userService.getUserByName("unknown"))
                .thenThrow(new ResourceNotFoundException("User not found"));

        mockMvc.perform(get("/user/{username}", "unknown")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /user/{username} - should return 400 for invalid username format")
    void getUserByName_InvalidUsername() throws Exception {
        when(userService.getUserByName(anyString()))
                .thenThrow(new InvalidUsernameException("Invalid username supplied"));

        mockMvc.perform(get("/user/{username}", "!invalid!")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /user/{username} - should return user with all fields populated")
    void getUserByName_AllFieldsPopulated() throws Exception {
        when(userService.getUserByName("jdoe")).thenReturn(sampleUser);

        mockMvc.perform(get("/user/{username}", "jdoe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rank", is("Detective")))
                .andExpect(jsonPath("$.title", is("Senior ADA")))
                .andExpect(jsonPath("$.cmdCode", is("CMD01")))
                .andExpect(jsonPath("$.mobile", is("555-1234")))
                .andExpect(jsonPath("$.roles", hasSize(2)));
    }
}
