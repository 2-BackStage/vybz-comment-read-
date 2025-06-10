package back.vybz.comment_read_service.comment.vo.response;

import back.vybz.comment_read_service.comment.domain.CommentRead;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseScrollCommentVo {
    private String id;
    private String writerUuid;
    private String comment;
    private boolean isReply;
    private int likeCount;
    private Instant createdAt;

    @Builder
    public ResponseScrollCommentVo(String id,
                                   String writerUuid,
                                   String comment,
                                   boolean isReply,
                                   int likeCount,
                                   Instant createdAt) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.comment = comment;
        this.isReply = isReply;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

    public static ResponseScrollCommentVo from(CommentRead commentRead) {
        return ResponseScrollCommentVo.builder()
                .id(commentRead.getId())
                .writerUuid(commentRead.getWriterUuid())
                .comment(commentRead.getComment())
                .isReply(commentRead.getParentCommentId() != null)
                .likeCount(commentRead.getLikeCount())
                .createdAt(commentRead.getCreatedAt())
                .build();
    }

    public static List<ResponseScrollCommentVo> listFrom(List<CommentRead> commentReads) {
        return commentReads.stream()
                .map(ResponseScrollCommentVo::from)
                .collect(Collectors.toList());
    }
}
