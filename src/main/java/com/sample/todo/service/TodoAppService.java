package com.sample.todo.service;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.sample.todo.dao.TodoAppDao;
import com.sample.todo.entity.TodoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ロジックを記述するクラス<br>
 *
 * @Componentと書いておくと、他からはは@Autowiredと記述すれば利用できる。Spring Beanという概念。
 */
@Component 
public class TodoAppService {

    /**
     * TodoAppDaoは@Componentを持っているので、@Autowiredで利用できる（裏でSpringがこっそりセットしています）
     */
    @Autowired
    private TodoAppDao dao;

    public String nowtime(){
        Date today = new Date();
        SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = d1.format(today);
        return nowtime;
    }

    public List<TodoApp> getTodoAppList() {
        return dao.getTodoAppList();
    }

    public void register(String title, String detail, String deadline, String category) {
        int nextId = dao.getNextId();
        dao.insert(nextId, title, detail, deadline, category);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    
    public List<TodoApp> getTodoAppListFix(int id) {
        return dao.getTodoAppListFix(id);
    }

    public void fixDone(int id, String title, String detail, String deadline, String category) {
        dao.update(id, title, detail, deadline, category);
    }

    public List<TodoApp> getTodoAppSort(String title, String select){
        return dao.getTodoAppListSort(title, select);
    }
    
}
