package back.vybz.comment_read_service.kafka.consumer;

import back.vybz.comment_read_service.comment.infrastructure.CommentReadRepository;
import back.vybz.comment_read_service.kafka.event.CommentLikeCountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentLikeCountEventConsumer {

    private final CommentReadRepository commentReadRepository;

    @KafkaListener(
            topics = "comment-like-count",
            groupId = "comment-like-group",
            containerFactory = "commentLikeCountKafkaListenerContainerFactory"
    )
    public void consume(CommentLikeCountEvent event) {
        log.info("🔥 댓글 좋아요 수 수신: commentId={}, likeCount={}",
                event.getCommentId(), event.getTotalLikeCount());

        commentReadRepository.findById(event.getCommentId())
                .ifPresent(comment -> {
                    comment.setLikeCount(event.getTotalLikeCount());
                    commentReadRepository.save(comment);
                });
    }
}
