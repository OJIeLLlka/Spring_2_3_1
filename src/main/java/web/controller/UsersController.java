package web.controller;

import org.springframework.validation.BindingResult;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute(userService.getAllUsers());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {

        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "users/new";
        }

        userService.saveUser(user);
        return "redirect:/users";
    }

    //РЕДАКТИРОВАНИЕ ЮЗЕРА
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {

        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}