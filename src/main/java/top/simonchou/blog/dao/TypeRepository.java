package top.simonchou.blog.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.simonchou.blog.po.Type;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {
    //查询分类名字
    Type findByName(String name);

    //查询每个分类的博客数目大小的排序后的前6个
    @Query("select t from t_type t")
    List<Type> findTop(Pageable pageable);
}
