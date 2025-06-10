package back.vybz.comment_read_service.comment.dto.request;

import back.vybz.comment_read_service.comment.domain.FeedType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class RequestScrollCommentDto {
    private String feedId;
    private FeedType feedType;
    private Instant lastCreatedAt;
    private String lastId;
    private int size = 10;

    @Builder
    private RequestScrollCommentDto(String feedId,
                                    FeedType feedType,
                                    Instant lastCreatedAt,
                                    String lastId,
                                    int size) {
        this.feedId = feedId;
        this.feedType = feedType;
        this.lastCreatedAt = lastCreatedAt;
        this.lastId = lastId;
        this.size = size;
    }
}
