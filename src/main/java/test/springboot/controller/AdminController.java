package test.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import test.springboot.model.User;
import test.springboot.service.UserService;

@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasAnyAuthority('admin')")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addPageAttr(ModelMap model) {
        model.addAttribute("adminPage", true);
    }

    private void addAllUsersToModel(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
    }


    @GetMapping
//    @PreAuthorize("hasAnyAuthority('admin')")
    public String adminPage(ModelMap model) {
        addAllUsersToModel(model);
        return "admin";
    }

    @PostMapping(value = "/add")
//    @PreAuthorize("hasAnyAuthority('admin')")
    public String addUser(@RequestParam String[] rolesParam, @ModelAttribute() User user, ModelMap model) {
        user.setRoles(rolesParam);
//        if (user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getName().isEmpty() || user.getRoles().isEmpty())
//            model.addAttribute("isEmptyForm", true);
//        else
        model.addAttribute("isValidate", userService.addUser(user));

        addAllUsersToModel(model);
        return "admin";
    }

    @PostMapping(value = "/delete")
//    @PreAuthorize("hasAnyAuthority('admin')")
    public String deleteUser(@RequestParam Long id, ModelMap model) {
        userService.deleteUserById(id);

        addAllUsersToModel(model);
        return "admin";
    }

    @GetMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('admin')")
    public String updatePage(@PathVariable String id, ModelMap model) {
        model.addAttribute("id", Long.parseLong(id));

        addAllUsersToModel(model);
        return "admin";
    }

    @PostMapping(value = "/update")
//    @PreAuthorize("hasAnyAuthority('admin')")
    public String updateUser(@ModelAttribute User user, ModelMap model) {
        boolean isAlreadyExist = !userService.updateUser(user);
        model.addAttribute("isAlreadyExist", isAlreadyExist);

        addAllUsersToModel(model);
        if (isAlreadyExist) {
            model.addAttribute("login", user.getLogin());
            model.addAttribute("id", user.getId());
        }

        return "admin";
    }
}
