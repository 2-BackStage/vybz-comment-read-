package back.vybz.comment_read_service.comment.application;

import back.vybz.comment_read_service.comment.dto.request.RequestScrollCommentDto;
import back.vybz.comment_read_service.comment.dto.request.RequestScrollReplyDto;
import back.vybz.comment_read_service.comment.dto.response.ResponseScrollCommentDto;
import back.vybz.comment_read_service.comment.dto.response.ResponseScrollReplyDto;

public interface CommentReadService {

    ResponseScrollCommentDto getCommentListWithScroll(RequestScrollCommentDto requestScrollCommentDto);
    ResponseScrollReplyDto getReplyListWithScroll(RequestScrollReplyDto requestDto);


}
