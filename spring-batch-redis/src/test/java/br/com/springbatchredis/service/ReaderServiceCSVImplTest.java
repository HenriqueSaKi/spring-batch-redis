package br.com.springbatchredis.service;

import br.com.springbatchredis.model.ReaderCSVModel;
import br.com.springbatchredis.service.impl.ReaderServiceCSVImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ReaderServiceCSVImplTest {

    ReaderServiceCSVImpl service;

    @BeforeEach
    public void init() {
        service = new ReaderServiceCSVImpl();
    }

    @Test
    public void testReadFileSucess() throws IOException {
        List<ReaderCSVModel> response = service.readFile();

        Assertions.assertEquals(100, response.size());
        Assertions.assertEquals("1", response.get(0).getId());
        Assertions.assertEquals("Emily Johnson", response.get(0).getName());
        Assertions.assertEquals("1985-07-12", response.get(0).getDateOfBirthday());
        Assertions.assertEquals("Software Engineer", response.get(0).getCurrentJob());

    }

}
