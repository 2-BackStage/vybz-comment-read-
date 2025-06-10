package back.vybz.comment_read_service.comment.infrastructure;

import back.vybz.comment_read_service.comment.domain.CommentRead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentReadRepository extends MongoRepository<CommentRead, String>, CommentReadRepositoryCustom {
}
