package back.vybz.comment_read_service.comment.infrastructure;

import back.vybz.comment_read_service.comment.domain.CommentRead;
import back.vybz.comment_read_service.comment.domain.FeedType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentReadRepositoryCustomImpl implements CommentReadRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<CommentRead> findCommentsWithScroll(String feedId, FeedType feedType, Instant lastCreatedAt, String lastId, int size) {
        Query query = new Query();


        query.addCriteria(Criteria.where("feedId").is(feedId)
                .and("feedType").is(feedType)
                .and("parentCommentId").is(null));


        if (lastCreatedAt != null && lastId != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("createdAt").lt(lastCreatedAt),
                    Criteria.where("createdAt").is(lastCreatedAt).and("id").lt(lastId)
            ));
        }


        query.with(Sort.by(
                Sort.Order.desc("createdAt"),
                Sort.Order.desc("id")
        ));

        query.limit(size + 1);

        return mongoTemplate.find(query, CommentRead.class);
    }

    @Override
    public List<CommentRead> findRepliesWithScroll(String parentCommentId, Instant lastCreatedAt, String lastId, int size) {
        Query query = new Query();

        query.addCriteria(Criteria.where("parentCommentId").is(parentCommentId));

        if (lastCreatedAt != null && lastId != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("createdAt").lt(lastCreatedAt),
                    Criteria.where("createdAt").is(lastCreatedAt).and("id").lt(lastId)
            ));
        }

        query.with(Sort.by(
                Sort.Order.desc("createdAt"),
                Sort.Order.desc("id")
        ));

        query.limit(size + 1);

        return mongoTemplate.find(query, CommentRead.class);
    }
}
