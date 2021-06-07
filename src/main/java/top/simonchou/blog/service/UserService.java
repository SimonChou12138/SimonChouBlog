package top.simonchou.blog.service;

import top.simonchou.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
