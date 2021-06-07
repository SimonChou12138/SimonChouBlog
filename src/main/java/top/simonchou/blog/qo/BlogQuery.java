package top.simonchou.blog.qo;

import lombok.*;

/**
 * @基本功能:封装查询数据
 * @program:blog
 * @author:SimonChou
 * @create:2021-04-17 14:58:22
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}