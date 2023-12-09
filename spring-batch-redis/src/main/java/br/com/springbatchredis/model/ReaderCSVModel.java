package br.com.springbatchredis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ReaderCSVModel implements Serializable {

    public Integer id;
    public String name;
    public String dateOfBirthday;
    public String currentJob;

}
