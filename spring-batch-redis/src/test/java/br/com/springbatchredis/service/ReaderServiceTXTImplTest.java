package br.com.springbatchredis.service;

import br.com.springbatchredis.model.ReaderTXTModel;
import br.com.springbatchredis.service.impl.ReaderServiceTXTImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ReaderServiceTXTImplTest {

    ReaderServiceTXTImpl service;

    @BeforeEach
    public void init() {
        service = new ReaderServiceTXTImpl();
    }

    @Test
    public void testReadFileSucess() throws IOException {
        List<ReaderTXTModel> response = service.readFile();

        Assertions.assertEquals(100, response.size());
        Assertions.assertEquals(1, response.get(0).getId());
        Assertions.assertEquals("Emily Johnson", response.get(0).getName());
        Assertions.assertEquals("1989-07-20", response.get(0).getBirthday());
        Assertions.assertEquals("Computer Science", response.get(0).getGraduation());
        Assertions.assertEquals("University of X", response.get(0).getUniversity());

    }

}
