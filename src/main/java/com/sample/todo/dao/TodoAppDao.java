package com.sample.todo.dao;

import java.util.List;

import com.sample.todo.entity.TodoApp;
import com.sample.todo.entity.TodoAppRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * データアクセスオブジェクト（DataAccessObject=Dao）<br>
 * データアクセス関連を記述するクラス
 */
@Component
public class TodoAppDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate; //データベースに接続する時に使用されるクラス
    

    public List<TodoApp> getTodoAppList() {
        List<TodoApp> resultList = jdbcTemplate.query("SELECT * FROM TODO_APP", new MapSqlParameterSource(null), new TodoAppRowMapper());
        return resultList;
    }

    public int getNextId() {
        int maxTodoId = jdbcTemplate.queryForObject("SELECT MAX(TODO_ID) FROM TODO_APP;",
                new MapSqlParameterSource(null), Integer.class);
        return ++maxTodoId;
    }

    public <T> void insert(int todoId, String title, String detail, String deadline, String category) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("todoId", todoId);
        paramMap.addValue("title", title);
        paramMap.addValue("detail", detail);
        paramMap.addValue("deadline", deadline);
        paramMap.addValue("category", category);
        jdbcTemplate.update("INSERT INTO TODO_APP VALUES(:todoId, :title, :detail, :deadline, :category)", paramMap);
    }

    public <T> void delete(int id)
    {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("todoId", id);
        jdbcTemplate.update("delete from TODO_APP where TODO_ID = :todoId", paramMap);
    }

    
    public List<TodoApp> getTodoAppListFix(int id) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        TodoAppRowMapper todoapprowmapper = new TodoAppRowMapper();
        paramMap.addValue("todoId", id);
        List<TodoApp> resultList = jdbcTemplate.query("SELECT * FROM TODO_APP where TODO_ID = :todoId", paramMap, todoapprowmapper);
        return resultList;
    }

    public <T> void update(int todoId, String title, String detail, String deadline, String category) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("todoId", todoId);
        paramMap.addValue("title", title);
        paramMap.addValue("detail", detail);
        paramMap.addValue("deadline", deadline);
        paramMap.addValue("category", category);
        jdbcTemplate.update("update TODO_APP SET TITLE = :title, DETAIL = :detail, DEAD_LINE = :deadline, CATEGORY = :category where TODO_ID = :todoId", paramMap);
    }

    public List<TodoApp> getTodoAppListSort(String title, String select) {
        if(select.equals("ascend")){
            String sql = "SELECT * FROM TODO_APP ORDER BY "+ title;
            List<TodoApp> resultList = jdbcTemplate.query(sql, new MapSqlParameterSource(null), new TodoAppRowMapper());
            return resultList;
        }
        else{
            String sql = "SELECT * FROM TODO_APP ORDER BY "+ title +" DESC";
            List<TodoApp> resultList = jdbcTemplate.query(sql, new MapSqlParameterSource(null), new TodoAppRowMapper());
            return resultList;
            //
        }
    }
    
}
