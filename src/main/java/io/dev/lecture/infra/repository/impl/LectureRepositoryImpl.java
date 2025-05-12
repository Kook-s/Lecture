package io.dev.lecture.infra.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dev.lecture.domain.model.Lecture;
import io.dev.lecture.domain.repository.LectureRepository;
import io.dev.lecture.infra.Entity.LectureEntity;
import io.dev.lecture.infra.Entity.LectureScheduleEntity;
import io.dev.lecture.infra.repository.LectureJpaRepository;
import io.dev.lecture.infra.repository.LectureScheduleJpaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static io.dev.lecture.infra.Entity.QLectureEntity.lectureEntity;
import static io.dev.lecture.infra.Entity.QLectureScheduleEntity.lectureScheduleEntity;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;
    private final LectureScheduleJpaRepository lectureScheduleJpaRepository;

    @PersistenceContext
    private final EntityManager em;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Lecture> findById(long lectureId) {
        return lectureJpaRepository.findById(lectureId).map(LectureEntity::toLectureOnly);
    }

    @Override
    public List<Lecture> findAllByIds(List<Long> lectureIds) {
        return lectureJpaRepository.findAllById(lectureIds).stream()
                .map(LectureEntity::toLecture)
                .toList();
    }

    @Override
    public Optional<Lecture> findAvailableLectureById(Long lectureId) {
        LectureEntity result = queryFactory
                .selectFrom(lectureEntity)
                .leftJoin(lectureEntity.lectureSchedules, lectureScheduleEntity)
                .fetchJoin()
                .where(lectureEntity.id.eq(lectureId),
                        lectureScheduleEntity.currentCapacity.lt(lectureScheduleEntity.maxCapacity)
                )
                .fetchOne();

        return Optional.ofNullable(result).map(LectureEntity::toLecture);
    }

    @Override
    public List<Lecture> findAvailableTimeLectureById(LocalDateTime date) {
        List<LectureEntity> result = queryFactory
        .selectFrom(lectureEntity)
        .join(lectureEntity.lectureSchedules, lectureScheduleEntity)
        .fetchJoin()
        .where(
                lectureScheduleEntity.startTime.gt(date),
                lectureScheduleEntity.currentCapacity.lt(lectureScheduleEntity.maxCapacity)
        )
        .fetch();

        return result.stream()
                .map(LectureEntity::toLecture)
                .toList();
    }

    @Override
    public void increaseLecture(long scheduleId) {
        LectureScheduleEntity schedule = lectureScheduleJpaRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다."));;

        schedule.increaseCapacity();
    }
}
