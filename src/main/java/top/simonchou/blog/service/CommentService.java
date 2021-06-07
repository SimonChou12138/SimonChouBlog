package top.simonchou.blog.service;

import top.simonchou.blog.po.Comment;

import java.util.List;

public interface CommentService {

    //获取列表
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
