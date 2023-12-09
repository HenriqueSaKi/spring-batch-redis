package br.com.springbatchredis.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ReaderTXTModel implements Serializable {

    public Integer id;
    public String name;
    public String birthday;
    public String graduation;
    public String university;

}
