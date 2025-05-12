package io.dev.lecture.infra.init;

import io.dev.lecture.infra.Entity.LectureEntity;
import io.dev.lecture.infra.Entity.LectureScheduleEntity;
import io.dev.lecture.infra.Entity.RegistrationEntity;
import io.dev.lecture.infra.Entity.UserEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Profile({"local", "test"})
@Component
@RequiredArgsConstructor
public class InitDataLoad {

    private final InitUserService initUserService;

    @PostConstruct
    public void init() {
        initUserService.init();
    }

    @Component
    static class InitUserService {

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse("2024-10-12 17:00:00", formatter);


            LectureEntity spring = new LectureEntity("스프링",LocalDateTime.now(),null);
            LectureEntity jpa = new LectureEntity("JPA",LocalDateTime.now(),null);
            LectureEntity db = new LectureEntity("DB",LocalDateTime.now(),null);
            LectureEntity redis = new LectureEntity("Redis",LocalDateTime.now(),null);
            em.persist(spring);
            em.persist(jpa);
            em.persist(db);
            em.persist(redis);

            em.persist(new LectureScheduleEntity(5, LocalDateTime.parse("2025-05-10 13:00:00", formatter), spring));
            em.persist(new LectureScheduleEntity(0, LocalDateTime.parse("2025-05-12 13:00:00", formatter), spring));
            em.persist(new LectureScheduleEntity(5, LocalDateTime.parse("2025-05-10 13:00:00", formatter), jpa));
            em.persist(new LectureScheduleEntity(10, LocalDateTime.parse("2025-05-10 13:00:00", formatter), db));
            em.persist(new LectureScheduleEntity(10, LocalDateTime.parse("2025-05-10 13:00:00", formatter), redis));

            for (int i = 1; i <= 30; i++) {
                UserEntity user = new UserEntity("user"+i, LocalDateTime.now(), null);
                em.persist(user);

                // 강의마다 5명씩 등록한다고 가정
                LectureEntity lecture = (i <= 5) ? spring :
                        (i <= 10) ? jpa :
                                (i <= 20) ? db : redis;

                RegistrationEntity registration = new RegistrationEntity(user, lecture);
                em.persist(registration);
            }
        }
    }

}
