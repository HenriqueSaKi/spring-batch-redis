package br.com.springbatchredis.repository;

import br.com.springbatchredis.model.ReaderTXTModel;
import org.springframework.data.repository.CrudRepository;

public interface ReaderTXTRepository extends CrudRepository<ReaderTXTModel, String> {
}
