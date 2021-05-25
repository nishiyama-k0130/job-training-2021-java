package com.sample.todo.controller;

import java.util.List;

import com.sample.todo.entity.TodoApp;
import com.sample.todo.service.TodoAppService;

import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ブラウザからのリクエストはここにくる
 */
@Controller //ブラウザからの入力をここで受け付けるという意味
public class TodoAppController {

    @Autowired //new を裏で自動的にやってくれる。ex.private TodoAppService service; -> private TodoAppService service = new ToDoAppServise;
    private TodoAppService service;

    /**
     * valueの部分がURL<br>
     * POSTを許可しているのは、{@code #register(TodoApp, Model)} からredirectしてくるため
     */
    @RequestMapping(value = { "/", "index" }, method = { RequestMethod.GET, RequestMethod.POST }) //"/index"のURLできた、かつ、GETメソッドか、POSTメソッドできた時
    String index(Model model) { //Model model は盲目的に書く
        List<TodoApp> todoList = service.getTodoAppList(); //データベースのデータがListに格納される
        String nowtime = service.nowtime();
        model.addAttribute("todoList", todoList);// ここの"todoList"というキーがindex.htmlで参照されている
        model.addAttribute("nowtime", nowtime);
        return "index";// resources/index.htmlを指している
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET) //"/new"のURLできた、かつ、GETメソッドできた時
    String add(Model model) {
        String nowtime = service.nowtime();
        model.addAttribute("nowtime", nowtime);
        return "detail";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST) //"/register"のURLできた、かつ、POSTメソッドできた時
    String register(@ModelAttribute TodoApp todoApp, Model model) {
        if((todoApp.getTitle()).length() >= 30 || (todoApp.getDetail()).length() >= 100){
            String nowtime = service.nowtime();
            model.addAttribute("nowtime", nowtime);
            return "detailError";
        }
        else{
            service.register(todoApp.getTitle(), todoApp.getDetail(), todoApp.getDeadline());
            return "redirect:index";// 登録したらindexに移る
        }
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.POST)
    String delete(@ModelAttribute TodoApp todoApp, Model model, @PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/index";
    }

    
    @RequestMapping(value = "/fix/{id}", method = RequestMethod.POST) //"/fix/id"のURLできた、かつ、POSTメソッドできた時
    String fix(@ModelAttribute TodoApp todoApp, Model model, @PathVariable("id") int id) {
        List<TodoApp> todoList = service.getTodoAppListFix(id); //データベースのデータがListに格納される
        model.addAttribute("todoList", todoList);// ここの"todoList"というキーがindex.htmlで参照されている
        String nowtime = service.nowtime();
        model.addAttribute("nowtime", nowtime);
        return "fix";
    }

    @RequestMapping(value = "/fixDone/{id}", method = RequestMethod.POST) //"/fixDone/id"のURLできた、かつ、POSTメソッドできた時
    String fixDone(@ModelAttribute TodoApp todoApp, Model model, @PathVariable("id") int id) {
        if((todoApp.getTitle()).length() >= 30 || (todoApp.getDetail()).length() >= 100){
            List<TodoApp> todoList = service.getTodoAppListFix(id); //データベースのデータがListに格納される
            model.addAttribute("todoList", todoList);
            String nowtime = service.nowtime();
            model.addAttribute("nowtime", nowtime);
            return "fixError";
        }
        else{
            service.fixDone(id, todoApp.getTitle(), todoApp.getDetail(), todoApp.getDeadline());
            return "redirect:/index";// 登録したらindexに移る
        }
        
    }

    @RequestMapping("/sort") //"/sort"のURLできた、かつ、POSTメソッドできた時
    String sort(Model model, @RequestParam("title") String title, @RequestParam("select") String select){
        System.out.println(title+select);
        List<TodoApp> todoList = service.getTodoAppSort(title, select);
        String nowtime = service.nowtime();
        model.addAttribute("todoList", todoList);// ここの"todoList"というキーがindex.htmlで参照されている
        model.addAttribute("nowtime", nowtime);
        return "index";
    }
}
//, method = { RequestMethod.GET, RequestMethod.POST }