package br.com.springbatchredis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash
public class ReaderCSVModel implements Serializable {

    @Id
    private String id;
    private String name;
    private String dateOfBirthday;
    private String currentJob;

}
