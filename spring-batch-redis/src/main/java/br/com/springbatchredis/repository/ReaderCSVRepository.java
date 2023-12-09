package br.com.springbatchredis.repository;

import br.com.springbatchredis.model.ReaderCSVModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderCSVRepository extends CrudRepository<ReaderCSVModel, String> {
}
