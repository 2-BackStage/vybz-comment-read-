package back.vybz.comment_read_service.comment.dto.request;

import back.vybz.comment_read_service.comment.domain.FeedType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class RequestScrollReplyDto {

    private String parentCommentId;
    private Instant lastCreatedAt;
    private String lastCommentId;
    private int size = 10;

    @Builder
    public RequestScrollReplyDto(String parentCommentId, Instant lastCreatedAt, String lastCommentId, int size) {
        this.parentCommentId = parentCommentId;
        this.lastCreatedAt = lastCreatedAt;
        this.lastCommentId = lastCommentId;
        this.size = size;
    }
}




