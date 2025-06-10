package back.vybz.comment_read_service.comment.presention;

import back.vybz.comment_read_service.comment.application.CommentReadService;
import back.vybz.comment_read_service.comment.dto.request.RequestScrollCommentDto;
import back.vybz.comment_read_service.comment.dto.request.RequestScrollReplyDto;
import back.vybz.comment_read_service.comment.dto.response.ResponseScrollCommentDto;
import back.vybz.comment_read_service.comment.domain.FeedType;

import back.vybz.comment_read_service.comment.dto.response.ResponseScrollReplyDto;
import back.vybz.comment_read_service.common.entity.BaseResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/read/comments")
@RequiredArgsConstructor
public class CommentReadController {

    private final CommentReadService commentReadService;

    @Operation(
            summary = "댓글 목록 무한스크롤 조회 API",
            description = "댓글 데이터를 무한스크롤 방식으로 조회합니다. " +
                    "size는 한 페이지에 가져올 댓글 수이며, 다음 페이지 요청 시에는 lastCreatedAt과 lastId에 이전 목록의 마지막 댓글 정보를 전달해주세요. " +
                    "feedId는 피드 고유 ID이며, feedType은 피드의 타입(REELS, FAN_FEED, NOTICE, ABOUT) 중 하나입니다.",
            tags = {"COMMENT-READ-SERVICE"}
    )
    @GetMapping("/scroll")
    public BaseResponseEntity<ResponseScrollCommentDto> getCommentsWithScroll(
            @RequestParam String feedId,
            @RequestParam FeedType feedType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant lastCreatedAt,
            @RequestParam(required = false) String lastId,
            @RequestParam(defaultValue = "10") int size
    ) {
        RequestScrollCommentDto requestScrollCommentDto = RequestScrollCommentDto.builder()
                .feedId(feedId)
                .feedType(feedType)
                .lastCreatedAt(lastCreatedAt)
                .lastId(lastId)
                .size(size)
                .build();

        ResponseScrollCommentDto responseScrollCommentDto = commentReadService.getCommentListWithScroll(requestScrollCommentDto);
        return BaseResponseEntity.ok(responseScrollCommentDto);
    }

    @Operation(
            summary = "대댓글 무한스크롤 조회 API",
            description = "특정 댓글의 자식 댓글(대댓글)을 무한스크롤 방식으로 조회합니다.",
            tags = {"COMMENT-READ-SERVICE"}
    )
    @GetMapping("/replies/scroll")
    public BaseResponseEntity<ResponseScrollReplyDto> getRepliesWithScroll(
            @RequestParam String parentCommentId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant lastCreatedAt,
            @RequestParam(required = false) String lastCommentId,
            @RequestParam(defaultValue = "10") int size
    ) {
        RequestScrollReplyDto request = RequestScrollReplyDto.builder()
                .parentCommentId(parentCommentId)
                .lastCreatedAt(lastCreatedAt)
                .lastCommentId(lastCommentId)
                .size(size)
                .build();

        return BaseResponseEntity.ok(commentReadService.getReplyListWithScroll(request));
    }


}
