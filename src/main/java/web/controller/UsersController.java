package web.controller;

import org.springframework.validation.BindingResult;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UsersController {

//        List<User> userki = new ArrayList<>();

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
//        model.addAttribute(userki);

        model.addAttribute(userService.getAllUsers());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userki.stream().filter(p -> p.getId() == id).findAny().orElse(null));

        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        userki.add(user);

        if (bindingResult.hasErrors()) {
            return "redirect:/users";
        }

        userService.saveUser(user);
        return "users/users";
    }

    //РЕДАКТИРОВАНИЕ ЮЗЕРА
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
//        model.addAttribute(Objects.requireNonNull(userki.stream().filter(p -> p.getId() == id).findAny().orElse(null)));

        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}










//    @GetMapping(value = "/users")
//    public String index(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "users/users";
//    }
//
//    @GetMapping(value = "/{id}")
//    public String show(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "users/show";
//    }
//
//    @GetMapping(value = "/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "users/new";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/users";
//        }
//
//        userService.saveUser(user);
//        return "users/users";
//    }
//
//    //РЕДАКТИРОВАНИЕ ЮЗЕРА
//    @GetMapping(value = "/{id}/edit")
//    public String edit(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "users/edit";
//    }
//
//    @PatchMapping(value = "/{id}")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                         @PathVariable("id") long id) {
//        if (bindingResult.hasErrors()) {
//            return "users/edit";
//        }
//
//        userService.updateUser(user);
//        return "users/users";
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public String delete(@PathVariable("id") long id) {
//        userService.removeUserById(id);
//        return "users/users";
//    }


