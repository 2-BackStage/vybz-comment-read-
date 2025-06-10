package back.vybz.comment_read_service.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CommentLikeCountEvent {
    private String commentId;
    private int totalLikeCount;
}
