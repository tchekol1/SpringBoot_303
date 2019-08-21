package com.example.demo;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ToDo_2Repository todo_2Repository;
    @RequestMapping("/")
    public String listTODO(Model model){
        model.addAttribute("todos_2", todo_2Repository.findAll());
        return "list";


    }
    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("toDo_2", new ToDo_2());
        return "todo_2form";


    }
    @PostMapping("/process")
    public String processForm(@Valid ToDo_2 toDo_2, BindingResult result){
        if(result.hasErrors()){
            return "todo_2form";

        }todo_2Repository.save(toDo_2);
        return "redirect:/";
    }
@RequestMapping("/detail/{id}")
    public String showToDo_2(@PathVariable("id") long id, Model model){
        model.addAttribute("toDo_2", todo_2Repository.findById(id).get());
        return "show";

}
@RequestMapping("/update/{id}")
public String updateToDo_2(@PathVariable("id") long id, Model model){
    model.addAttribute("toDo_2", todo_2Repository.findById(id).get());
    return "todo_2form";

}
    @RequestMapping("/delete/{id}")
    public String delToDo_2(@PathVariable("id") long id){
        todo_2Repository.deleteById(id);
        return "redirect:/";

    }
}
