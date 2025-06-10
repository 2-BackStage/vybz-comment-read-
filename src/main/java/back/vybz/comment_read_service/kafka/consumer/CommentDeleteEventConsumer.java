package back.vybz.comment_read_service.kafka.consumer;

import back.vybz.comment_read_service.comment.infrastructure.CommentReadRepository;
import back.vybz.comment_read_service.kafka.event.CommentDeleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentDeleteEventConsumer {

    private final CommentReadRepository commentReadRepository;

    @KafkaListener(
            topics = "comment-delete-event",
            groupId = "comment-read-group",
            containerFactory = "commentDeleteEventKafkaListenerContainerFactory"
    )
    public void consume(CommentDeleteEvent event) {
        log.info("🗑️ Kafka 댓글 삭제 이벤트 수신: {}", event);

        commentReadRepository.deleteById(event.getCommentId());
        log.info("✅ 댓글 Mongo에서 삭제 완료: {}", event.getCommentId());
    }
}
