package me.right42.springdatajpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface MyRepository<T, ID> extends Repository<T, ID> {

    <E extends T> E save(@NonNull T entity);

    List<T> findAll();

    <E extends T> Optional<E> findById(ID id);
}
