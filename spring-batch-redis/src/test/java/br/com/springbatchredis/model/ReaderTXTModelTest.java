package br.com.springbatchredis.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReaderTXTModelTest {

    @Test
    public void testReaderTXTModel() {
        ReaderTXTModel model = new ReaderTXTModel();
        model.setId(1);
        model.setName("Name");
        model.setBirthday("Birthday");
        model.setGraduation("Graduation");
        model.setUniversity("University");

        Assertions.assertEquals(1, model.getId());
        Assertions.assertEquals("Name", model.getName());
        Assertions.assertEquals("Birthday", model.getBirthday());
        Assertions.assertEquals("Graduation", model.getGraduation());
        Assertions.assertEquals("University", model.getUniversity());

    }

}
