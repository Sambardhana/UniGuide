package com.uniguide.controller;

import com.uniguide.dto.ContactResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    private ContactResponse sampleEmergencyContact;
    private ContactResponse sampleDeptContact;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(contactController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleEmergencyContact = ContactResponse.builder()
                .id(1L)
                .name("Campus Health Emergency Helpline")
                .designation("24/7 Medical Response Unit")
                .category("EMERGENCY")
                .phoneNumber("+1-555-911-0000")
                .email("emergency@university.edu")
                .officeLocation("Medical Center Room 101")
                .build();

        sampleDeptContact = ContactResponse.builder()
                .id(2L)
                .name("Dr. Alan Turing")
                .designation("Head of Department")
                .category("FACULTY")
                .phoneNumber("+1-555-100-2001")
                .email("alan.turing@university.edu")
                .officeLocation("Turing Block Room 304")
                .departmentId(10L)
                .departmentName("Computer Science")
                .build();
    }

    @Test
    @DisplayName("GET /api/contacts returns all contacts")
    void getContacts_ReturnsAll() throws Exception {
        when(contactService.getAllContacts()).thenReturn(List.of(sampleEmergencyContact, sampleDeptContact));

        mockMvc.perform(get("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Campus Health Emergency Helpline")))
                .andExpect(jsonPath("$[1].name", is("Dr. Alan Turing")));

        verify(contactService).getAllContacts();
    }

    @Test
    @DisplayName("GET /api/contacts?category=EMERGENCY returns category contacts")
    void getContacts_CategoryFilter() throws Exception {
        when(contactService.getContactsByCategory("EMERGENCY")).thenReturn(List.of(sampleEmergencyContact));

        mockMvc.perform(get("/api/contacts")
                        .param("category", "EMERGENCY")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("EMERGENCY")));

        verify(contactService).getContactsByCategory("EMERGENCY");
    }

    @Test
    @DisplayName("GET /api/contacts?departmentId=10 returns departmental contacts")
    void getContacts_DepartmentFilter() throws Exception {
        when(contactService.getContactsByDepartment(10L)).thenReturn(List.of(sampleDeptContact));

        mockMvc.perform(get("/api/contacts")
                        .param("departmentId", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].departmentId", is(10)));

        verify(contactService).getContactsByDepartment(10L);
    }

    @Test
    @DisplayName("GET /api/contacts?search=Emergency returns searched contacts")
    void getContacts_SearchFilter() throws Exception {
        when(contactService.searchContacts("Emergency")).thenReturn(List.of(sampleEmergencyContact));

        mockMvc.perform(get("/api/contacts")
                        .param("search", "Emergency")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Campus Health Emergency Helpline")));

        verify(contactService).searchContacts("Emergency");
    }

    @Test
    @DisplayName("GET /api/contacts/{id} returns contact when found")
    void getContactById_Success() throws Exception {
        when(contactService.getContactById(1L)).thenReturn(sampleEmergencyContact);

        mockMvc.perform(get("/api/contacts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.phoneNumber", is("+1-555-911-0000")))
                .andExpect(jsonPath("$.category", is("EMERGENCY")));

        verify(contactService).getContactById(1L);
    }

    @Test
    @DisplayName("GET /api/contacts/{id} returns 404 when not found")
    void getContactById_NotFound() throws Exception {
        when(contactService.getContactById(99L))
                .thenThrow(new ResourceNotFoundException("Contact", "id", 99L));

        mockMvc.perform(get("/api/contacts/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Contact not found with id: '99'")));

        verify(contactService).getContactById(99L);
    }

    @Test
    @DisplayName("GET /api/contacts/emergency returns emergency contacts")
    void getEmergencyContacts_Success() throws Exception {
        when(contactService.getContactsByCategory("EMERGENCY")).thenReturn(List.of(sampleEmergencyContact));

        mockMvc.perform(get("/api/contacts/emergency")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("EMERGENCY")));

        verify(contactService).getContactsByCategory("EMERGENCY");
    }

    @Test
    @DisplayName("GET /api/contacts/category/{category} returns contacts by category path")
    void getContactsByCategory_Success() throws Exception {
        when(contactService.getContactsByCategory("FACULTY")).thenReturn(List.of(sampleDeptContact));

        mockMvc.perform(get("/api/contacts/category/FACULTY")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].designation", is("Head of Department")));

        verify(contactService).getContactsByCategory("FACULTY");
    }
}
