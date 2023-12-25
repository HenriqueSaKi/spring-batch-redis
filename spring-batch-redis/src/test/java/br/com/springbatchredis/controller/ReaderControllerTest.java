package br.com.springbatchredis.controller;

import br.com.springbatchredis.service.impl.ReaderServiceCSVImpl;
import br.com.springbatchredis.service.impl.ReaderServiceTXTImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReaderControllerTest {

    @Mock
    ReaderServiceCSVImpl csvService;

    @Mock
    ReaderServiceTXTImpl txtService;

    @InjectMocks
    ReaderController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                    .alwaysDo(print())
                    .build();
    }

    @Test
    public void testReadCSVFile() throws Exception {
        mockMvc.perform(get("/read_csv_file"))
                .andExpect(status().isOk());

    }

    @Test
    public void testReadCSVFileBadRequest() throws Exception {
        Mockito.when(csvService.readFile()).thenThrow(IOException.class);

        Assertions.assertEquals("[]",
                mockMvc.perform(get("/read_csv_file"))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString()
        );

    }

    @Test
    public void testReadTXTFile() throws Exception {
        mockMvc.perform(get("/read_txt_file"))
                .andExpect(status().isOk());

    }

    @Test
    public void testReadTXTFileBadRequest() throws Exception {
        Mockito.when(txtService.readFile()).thenThrow(IOException.class);

        Assertions.assertEquals("[]",
                mockMvc.perform(get("/read_txt_file"))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
        );

    }

}
