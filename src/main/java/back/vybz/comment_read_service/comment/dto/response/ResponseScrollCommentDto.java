package back.vybz.comment_read_service.comment.dto.response;

import back.vybz.comment_read_service.comment.domain.CommentRead;
import back.vybz.comment_read_service.comment.vo.response.ResponseScrollCommentVo;
import back.vybz.comment_read_service.common.util.CursorPage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollCommentDto {

    private List<ResponseScrollCommentVo> content;
    private boolean hasNext;
    private Instant nextCreatedAt;
    private String nextId;

    @Builder
    public ResponseScrollCommentDto(List<ResponseScrollCommentVo> content,
                                    boolean hasNext,
                                    Instant nextCreatedAt,
                                    String nextId) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCreatedAt = nextCreatedAt;
        this.nextId = nextId;
    }

    public static ResponseScrollCommentDto from(CursorPage<CommentRead> cursorPage) {
        return ResponseScrollCommentDto.builder()
                .content(ResponseScrollCommentVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCreatedAt(cursorPage.getNextCreatedAt())
                .nextId(cursorPage.getNextId())
                .build();
    }
}
