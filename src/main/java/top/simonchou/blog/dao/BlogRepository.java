package top.simonchou.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import top.simonchou.blog.po.Blog;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor {
    @Query("select t from t_blog t where t.recommend = true")
    List<Blog> findTop(Pageable pageable);

    //传第一个值就？1,第二个值就是？2
    @Query("select t from t_blog t where t.title like ?1 or t.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Query("select function('date_format',t.updateTime,'%Y') from t_blog t group by function('date_format',t.updateTime,'%Y') order by function('date_format',t.updateTime,'%Y') desc ")
    List<String> findGroupYear();


    @Query("select t from t_blog t where function('date_format',t.updateTime,'%Y') = ?1")
    List<Blog> findBlogYear(String year);
}
