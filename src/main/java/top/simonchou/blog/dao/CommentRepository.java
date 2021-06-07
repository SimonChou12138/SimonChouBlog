package top.simonchou.blog.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.simonchou.blog.po.Comment;

import java.util.List;

/**
 * @基本功能:
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-22 16:37:37
 **/
public interface CommentRepository extends JpaRepository<Comment,Long > {
    //父类为空即为最顶级的根节点
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}