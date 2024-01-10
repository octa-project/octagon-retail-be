package octagon.retail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import octagon.retail.entity.Sales;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface MainRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

    <S extends T> S save(S var1);

    <S extends T> Iterable<S> saveAll(Iterable<S> var1);

    Optional<T> findById(ID var1);

    boolean existsById(ID var1);

    List<T> findAll();

    Iterable<T> findAllById(Iterable<ID> var1);

    long count();

    void deleteById(ID var1);

    void delete(T var1);

    void deleteAll(Iterable<? extends T> var1);

    void deleteAll();
}
