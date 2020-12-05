# Transactional Outbox Sample

## 개요
- Serverless auroraDB의 경우 binary log(binlog)를 제공하지 않아 다른 시스템으로 database 변경사항을 마이그레이션 하기가 어려움
- Transactional outbox pattern을 적용하면 binlog를 제공하지 않는 Database의 변경 사항을 일관성 있게 마이그레이션 할 수 있음  

## Transactional Outbox
- 동일 Database내에 변경사항을 기록하는 Outbox Table을 생성
- 단일 트랜잭션 내에서 테이블 변경사항이 일어나면 OutBox table에도 변경사항을 기록
- 동일 데이터베이스내에 테이블이 존재하고 단일 트랙잭션으로 데이터를 처리하므로 Outbox 테이블에 일관성 있게 변경 데이터를 저장할 수 있다
- Outbox 테이블에 기록된 DB 변경 사항을 지속적으로 감시하고 처리하는 Message Relay를 구현하여 변경사항을 마이그레이션 한다. 처리 완료된 내역은 Outbox 테이블에서 삭제한다.

## Outbox Table 구조
field | Type | Comment | Sample
-----|-----|----------|----------
id | bigint | pk | 1
aggregate_id | varchar(50) | 순서처리가 필요한 데이터를 그룹핑하는 ID(ex. user_id) | abel
aggregate_type | varchar(30) | 대상 테이블 명 | LICENSE
event_type | varchar(30) | 이벤트 타입(INSERT, UPDATE, DELETE ...) | INSERT 
payload | text | 테이블 변경사항(JSON) | {"id":42,"userid":"abel","episode_id":14562,"expire_at_datetime":"2021-01-01T15:23:38Z"}
created_at_datetime | datetime | 생성일자 | 2020-12-01 23:38:39.616869
## Spring ApplicationEvent를 통한 Outbox 이벤트 구현
- Application Event를 발행하는 event publisher 생성
~~~java
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish(OutBoxEvent outboxEvent) {
        this.publisher.publishEvent(outboxEvent);
    }
}
~~~
- 비즈니스 로직 처리시 데이터 베이스 변경사항이 발생하면 단일 트랜잭션 내에서 Outbox Event를 발행
~~~java
@RequiredArgsConstructor
@Service
public class LicenseService implements RegisterLicenseUseCase {

    private final RegisterLicensePort registerLicensePort;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public void registerLicense(License license) {
        LicenseEntity licenseEntity = LicenseEntity.builder().userid(license.getUserid()).episodeId(license.getEpisodeId()).expireAtDatetime(license.getExpireAtDatetime()).build();
        registerLicensePort.register(licenseEntity);
        eventPublisher.publish(EventUtils.createLicenseEvent(licenseEntity, EventUtils.EventType.INSERT));
    }
}
~~~
- 이벤트 리스너가 데이터베이스 변경 이벤트를 Outbox 테이블에 기록
~~~java
@RequiredArgsConstructor
@Service
public class EventService {

    private final SaveOutBoxPort saveOutBoxPort;

    @EventListener
    public void handleOutBoxEvent(OutBoxEvent event) {
        OutBoxEntity outBox = OutBoxEntity.builder()
                .aggregateId(event.getAggregateId())
                .aggregateType(event.getAggregateType().name())
                .eventType(event.getEventType().name())
                .payload(event.getPayload().toString())
                .createdAtDatetime(LocalDateTime.now())
                .build();
        saveOutBoxPort.save(outBox);
    }
}
~~~




