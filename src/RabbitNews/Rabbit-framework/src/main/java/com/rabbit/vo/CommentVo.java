package com.rabbit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Long id;

    private Long articleId;

    private Long rootId;

    private Long userId;

    private String content;

    private Long toCommentUserId;

    private String toCommentUserName;

    private String username;

    private String avatar;

    private Long toCommentId;

    private List<CommentVo> children;

}
