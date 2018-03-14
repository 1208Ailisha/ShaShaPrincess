package com.ailisha.cmsweb.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hspcadmin on 2017/12/5.
 */
@Controller
@RequestMapping("/list")
public class ListController {

    @RequestMapping("/list")
    public String list(Model model){
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        model.addAttribute("list",list);
        return "list/list";
    }
}
