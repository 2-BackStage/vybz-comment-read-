package back.vybz.comment_read_service.comment.infrastructure;

import back.vybz.comment_read_service.comment.domain.CommentRead;
import back.vybz.comment_read_service.comment.domain.FeedType;

import java.time.Instant;
import java.util.List;

public interface CommentReadRepositoryCustom  {

    List<CommentRead> findCommentsWithScroll(String feedId, FeedType feedType, Instant lastCreatedAt, String lastId, int size);
    List<CommentRead> findRepliesWithScroll(String parentCommentId, Instant lastCreatedAt, String lastId, int size);



}
