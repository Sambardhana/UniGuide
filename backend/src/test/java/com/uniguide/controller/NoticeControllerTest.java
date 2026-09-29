package com.uniguide.controller;

import com.uniguide.dto.NoticeResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.NoticeService;
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

import java.time.LocalDateTime;
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
class NoticeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NoticeService noticeService;

    @InjectMocks
    private NoticeController noticeController;

    private NoticeResponse sampleNotice;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(noticeController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleNotice = NoticeResponse.builder()
                .id(1L)
                .title("Midterm Examination Guidelines")
                .content("Detailed instructions and schedule for upcoming midterms.")
                .category("EXAMINATION")
                .attachmentUrl("https://uniguide.edu/docs/midterm-rules.pdf")
                .isPinned(true)
                .publishedAt(LocalDateTime.now())
                .departmentId(2L)
                .departmentName("Computer Science")
                .build();
    }

    @Test
    @DisplayName("GET /api/notices returns all notices")
    void getNotices_ReturnsAll() throws Exception {
        when(noticeService.getAllNotices()).thenReturn(List.of(sampleNotice));

        mockMvc.perform(get("/api/notices")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Midterm Examination Guidelines")))
                .andExpect(jsonPath("$[0].category", is("EXAMINATION")));

        verify(noticeService).getAllNotices();
    }

    @Test
    @DisplayName("GET /api/notices?category=EXAMINATION returns category notices")
    void getNotices_CategoryFilter() throws Exception {
        when(noticeService.getNoticesByCategory("EXAMINATION")).thenReturn(List.of(sampleNotice));

        mockMvc.perform(get("/api/notices")
                        .param("category", "EXAMINATION")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("EXAMINATION")));

        verify(noticeService).getNoticesByCategory("EXAMINATION");
    }

    @Test
    @DisplayName("GET /api/notices?departmentId=2 returns departmental notices")
    void getNotices_DepartmentFilter() throws Exception {
        when(noticeService.getNoticesByDepartment(2L)).thenReturn(List.of(sampleNotice));

        mockMvc.perform(get("/api/notices")
                        .param("departmentId", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].departmentId", is(2)));

        verify(noticeService).getNoticesByDepartment(2L);
    }

    @Test
    @DisplayName("GET /api/notices?pinned=true returns pinned notices")
    void getNotices_PinnedFilter() throws Exception {
        when(noticeService.getPinnedNotices()).thenReturn(List.of(sampleNotice));

        mockMvc.perform(get("/api/notices")
                        .param("pinned", "true")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].isPinned", is(true)));

        verify(noticeService).getPinnedNotices();
    }

    @Test
    @DisplayName("GET /api/notices?general=true returns general notices")
    void getNotices_GeneralFilter() throws Exception {
        when(noticeService.getGeneralNotices()).thenReturn(List.of(sampleNotice));

        mockMvc.perform(get("/api/notices")
                        .param("general", "true")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(noticeService).getGeneralNotices();
    }

    @Test
    @DisplayName("GET /api/notices/{id} returns notice when found")
    void getNoticeById_Success() throws Exception {
        when(noticeService.getNoticeById(1L)).thenReturn(sampleNotice);

        mockMvc.perform(get("/api/notices/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Midterm Examination Guidelines")))
                .andExpect(jsonPath("$.attachmentUrl", is("https://uniguide.edu/docs/midterm-rules.pdf")));

        verify(noticeService).getNoticeById(1L);
    }

    @Test
    @DisplayName("GET /api/notices/{id} returns 404 when not found")
    void getNoticeById_NotFound() throws Exception {
        when(noticeService.getNoticeById(99L))
                .thenThrow(new ResourceNotFoundException("Notice", "id", 99L));

        mockMvc.perform(get("/api/notices/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Notice not found with id: '99'")));

        verify(noticeService).getNoticeById(99L);
    }

    @Test
    @DisplayName("GET /api/notices/pinned returns pinned notices")
    void getPinnedNotices_Success() throws Exception {
        when(noticeService.getPinnedNotices()).thenReturn(List.of(sampleNotice));

        mockMvc.perform(get("/api/notices/pinned")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].isPinned", is(true)));

        verify(noticeService).getPinnedNotices();
    }

    @Test
    @DisplayName("GET /api/notices/category/{category} returns notices for path category")
    void getNoticesByCategoryPath_Success() throws Exception {
        when(noticeService.getNoticesByCategory("EXAMINATION")).thenReturn(List.of(sampleNotice));

        mockMvc.perform(get("/api/notices/category/EXAMINATION")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("EXAMINATION")));

        verify(noticeService).getNoticesByCategory("EXAMINATION");
    }
}
