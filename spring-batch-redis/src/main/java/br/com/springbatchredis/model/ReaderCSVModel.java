package br.com.springbatchredis.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash
public class ReaderCSVModel implements Serializable {

    @Id
    public Integer id;
    public String name;
    public String dateOfBirthday;
    public String currentJob;

}
