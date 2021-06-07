package top.simonchou.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.simonchou.blog.po.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String userName, String password);
}
