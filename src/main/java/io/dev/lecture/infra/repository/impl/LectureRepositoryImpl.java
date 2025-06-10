package io.dev.lecture.infra.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dev.lecture.domain.model.Schedule;
import io.dev.lecture.domain.repository.LectureRepository;
import io.dev.lecture.infra.entity.LectureEntity;
import io.dev.lecture.infra.entity.QLectureEntity;
import io.dev.lecture.infra.entity.QScheduleEntity;
import io.dev.lecture.infra.entity.ScheduleEntity;
import io.dev.lecture.infra.repository.LectureJpaRepository;
import io.dev.lecture.infra.repository.ScheduleJpaRepository;
import io.dev.lecture.support.CustomException;
import io.dev.lecture.support.ErrorCode;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static io.dev.lecture.infra.entity.QLectureEntity.lectureEntity;
import static io.dev.lecture.infra.entity.QScheduleEntity.scheduleEntity;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;
    private final ScheduleJpaRepository scheduleJpaRepository;

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Schedule> findById(long id) {

        ScheduleEntity result = queryFactory
                .selectFrom(scheduleEntity)
                .join(scheduleEntity.lecture, lectureEntity)
                .fetchJoin()
                .where(scheduleEntity.id.eq(id))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();

        return Optional.ofNullable(result).map(ScheduleEntity::toSchedule);
    }

    @Override
    public List<Schedule> findAvailableById(LocalDateTime date) {
        List<ScheduleEntity> result = queryFactory
                .selectFrom(scheduleEntity)
                .join(scheduleEntity.lecture, lectureEntity)
                .fetchJoin()
                .where(
                        scheduleEntity.startAt.gt(date),
                        scheduleEntity.currentCapacity.lt(scheduleEntity.maxCapacity)
                )
                .fetch();

        return result.stream()
                .map(ScheduleEntity::toSchedule)
                .toList();
    }

    @Override
    public List<Schedule> findAllLectures(List<Long> lectureIds) {
        return scheduleJpaRepository.findAllById(lectureIds)
                .stream()
                .map(ScheduleEntity::toSchedule)
                .toList();
    }

    @Override
    public int increaseLecture(long scheduleId) {
        ScheduleEntity schedule = scheduleJpaRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));

        schedule.increaseCapacity();

        return schedule.getCurrentCapacity();
    }
}
