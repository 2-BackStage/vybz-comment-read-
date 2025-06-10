package back.vybz.comment_read_service.comment.application;

import back.vybz.comment_read_service.comment.domain.CommentRead;
import back.vybz.comment_read_service.comment.dto.request.RequestScrollCommentDto;
import back.vybz.comment_read_service.comment.dto.request.RequestScrollReplyDto;
import back.vybz.comment_read_service.comment.dto.response.ResponseScrollCommentDto;
import back.vybz.comment_read_service.comment.dto.response.ResponseScrollReplyDto;
import back.vybz.comment_read_service.comment.infrastructure.CommentReadRepository;
import back.vybz.comment_read_service.common.util.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentReadServiceImpl implements CommentReadService {

    private final CommentReadRepository commentReadRepository;

    /*
     * 스크롤 방식으로 댓글 목록 조회
     */
    @Override
    public ResponseScrollCommentDto getCommentListWithScroll(RequestScrollCommentDto requestScrollCommentDto) {

        List<CommentRead> comments = commentReadRepository.findCommentsWithScroll(
                requestScrollCommentDto.getFeedId(),
                requestScrollCommentDto.getFeedType(),
                requestScrollCommentDto.getLastCreatedAt(),
                requestScrollCommentDto.getLastId(),
                requestScrollCommentDto.getSize()
        );

        CursorPage<CommentRead> cursorPage = CursorPage.of(
                comments,
                requestScrollCommentDto.getSize(),
                CommentRead::getCreatedAt,
                CommentRead::getId
        );

        return ResponseScrollCommentDto.from(cursorPage);
    }

    /*
     * 스크롤 방식으로 부모 댓글(최상위 댓글) 목록 조회
     */
    @Override
    public ResponseScrollReplyDto getReplyListWithScroll(RequestScrollReplyDto requestDto) {
        List<CommentRead> replies = commentReadRepository.findRepliesWithScroll(
                requestDto.getParentCommentId(),
                requestDto.getLastCreatedAt(),
                requestDto.getLastCommentId(),
                requestDto.getSize()
        );

        CursorPage<CommentRead> cursorPage = CursorPage.of(
                replies,
                requestDto.getSize(),
                CommentRead::getCreatedAt,
                CommentRead::getId
        );

        return ResponseScrollReplyDto.from(cursorPage);
    }
}
