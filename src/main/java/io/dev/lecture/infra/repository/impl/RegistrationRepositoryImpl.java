package io.dev.lecture.infra.repository.impl;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dev.lecture.domain.model.Registration;
import io.dev.lecture.domain.repository.RegistrationRepository;
import io.dev.lecture.infra.Entity.*;
import io.dev.lecture.infra.repository.LectureJpaRepository;
import io.dev.lecture.infra.repository.RegistrationJpaRepository;
import io.dev.lecture.infra.repository.UserJpaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.dev.lecture.infra.Entity.QRegistrationEntity.registrationEntity;
import static io.dev.lecture.infra.Entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class RegistrationRepositoryImpl implements RegistrationRepository {

    private final RegistrationJpaRepository registrationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final LectureJpaRepository lectureJpaRepository;

    @Autowired
    EntityManager em;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Registration> findByRegistration(long userId) {
        List<RegistrationEntity> result = queryFactory
                .selectFrom(registrationEntity)
                .join(registrationEntity.user, userEntity)
                .fetchJoin()
                .where(userEntity.id.eq(userId))
                .fetch();

        return result.stream().map(RegistrationEntity::toRegistration).toList();
    }

    @Override
    public void save(long userId, long lectureId) {
        UserEntity user = userJpaRepository.findById(userId).get();
        LectureEntity lecture = lectureJpaRepository.findById(lectureId).get();

        registrationJpaRepository.save(new RegistrationEntity(user, lecture));
    }
}
