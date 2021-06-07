package top.simonchou.blog.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.simonchou.blog.po.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    //查询分类名字
    Tag findByName(String name);

    @Query("select t from t_tag t")
    List<Tag> findTop(Pageable pageable);
}
