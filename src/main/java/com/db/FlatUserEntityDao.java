package com.db;

import com.models.FlatUserEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class FlatUserEntityDao extends AbstractDAO<FlatUserEntity> {
    public FlatUserEntityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<FlatUserEntity> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public void create(FlatUserEntity flatUserEntity) {
        persist(flatUserEntity);
    }

    @SuppressWarnings("unchecked")
    public List<FlatUserEntity> findAll() {
        return list((Query<FlatUserEntity>) namedQuery("com.models.FlatUserEntity.findAll"));
    }
}
