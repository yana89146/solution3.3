package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    UserService userServiceImp;

    ;

    @GetMapping(value = "/result")
    public String showList(ModelMap model) {
        model.addAttribute("users", userServiceImp.getAllUsers());
        return "result";
    }

    @GetMapping(value = "/")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(value = "/result")
    public String create(@ModelAttribute("user") User user) {
        userServiceImp.add(user);
        return "redirect:/result";
    }

    @GetMapping(value = "/delete")
    public String delete(Model model, @RequestParam(required = false, defaultValue = "0") Long id) {
        userServiceImp.deleteById(id);
        model.addAttribute("users", userServiceImp.getAllUsers());
        return "redirect:/result";
    }

    @GetMapping(value = "/update")
    public String chooseUserForUpdate(Model model, @RequestParam Long id) {
        User user = userServiceImp.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        return "update";
    }


    @PostMapping(value = "/updateAndSave")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam Long id) {
        User user1 = userServiceImp.findById(id);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setSurname(user.getSurname());
        userServiceImp.update(user);
        return "redirect:/result";
    }

    @GetMapping(value = "/updateAndSave")
    public String showUpdatedUser(Model model) {
        model.addAttribute("users", userServiceImp.getAllUsers());
        return "redirect:/result";
    }


}
