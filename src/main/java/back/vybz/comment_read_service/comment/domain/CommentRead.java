package back.vybz.comment_read_service.comment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Document("comment_read")
public class CommentRead {
    @Id
    private String id;
    //피드 id
    @Indexed
    private String feedId;
    //피드 타입
    private FeedType feedType;
    //댓글작성자 uuid
    private String writerUuid;
    //댓글 작성자 타입
    private WriterType writerType;
    //댓글 내용
    private String comment;
    //부모 댓글 ID (대댓글일 경우에만 세팅됨)
    @Indexed
    private String parentCommentId;
    //댓글 좋아요 수
    private int likeCount;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Builder
    public CommentRead(String id,
                       String feedId,
                       FeedType feedType,
                       String writerUuid,
                       WriterType writerType,
                       String comment,
                       String parentCommentId,
                       int likeCount) {
        this.id = id;
        this.feedId = feedId;
        this.feedType = feedType;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
        this.likeCount = likeCount;

    }
}
