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
public class ReaderTXTModel implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String birthday;
    private String graduation;
    private String university;

}
