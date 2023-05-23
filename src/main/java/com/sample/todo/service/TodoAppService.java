package com.sample.todo.service;

import java.util.List;

import com.sample.todo.dao.TodoAppDao;
import com.sample.todo.entity.TodoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ロジックを記述するクラス<br>
 *
 * {@code @Component}と書いておくと、他からは{@code @Autowired}と記述すれば利用できる。Spring Beanという概念。
 */
@Component
public class TodoAppService {

  /**
   * TodoAppDaoは{@code @Component}を持っているので、{@code @Autowired}で利用できる（裏でSpringがこっそりセットしています）
   */
  @Autowired
  private TodoAppDao dao;

  public List<TodoApp> getTodoAppList() {
    return dao.getTodoAppList();
  }

  public void register(String title, String detail) {
    int nextId = dao.getNextId();
    dao.insert(nextId, title, detail);
  }
}
