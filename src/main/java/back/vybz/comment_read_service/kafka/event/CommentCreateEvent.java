package back.vybz.comment_read_service.kafka.event;

import back.vybz.comment_read_service.comment.domain.FeedType;
import back.vybz.comment_read_service.comment.domain.WriterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateEvent {
    private String id;
    private String feedId;
    private FeedType feedType;
    private String writerUuid;
    private WriterType writerType;
    private String comment;
    private String parentCommentId;
    private Instant createdAt;
}
