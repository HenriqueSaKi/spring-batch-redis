package br.com.springbatchredis.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReaderCSVModelTest {

    @Test
    public void testReaderCSVModel() {
        ReaderCSVModel model = new ReaderCSVModel();
        model.setId("Id");
        model.setName("Name");
        model.setDateOfBirthday("Date of Birthday");
        model.setCurrentJob("Current Job");

        Assertions.assertEquals("Id", model.getId());
        Assertions.assertEquals("Name", model.getName());
        Assertions.assertEquals("Date of Birthday", model.getDateOfBirthday());
        Assertions.assertEquals("Current Job", model.getCurrentJob());

    }

}
