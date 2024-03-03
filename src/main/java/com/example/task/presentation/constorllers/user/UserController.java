package com.example.task.presentation.constorllers.user;

import com.example.task.application.login.LoginUser;
import com.example.task.application.user.UserApplicationService;
import com.example.task.application.user.get.UserGetCommand;
import com.example.task.application.user.register.UserRegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;

    @ModelAttribute("userCreateForm")
    public UserCreateForm userCreateForm() {
        return new UserCreateForm();
    }

    @GetMapping("/{id}")
    public String get(@AuthenticationPrincipal LoginUser loginUser,
                      @PathVariable("id") Integer id,
                      Model model) {
        int userId = loginUser.getUserId();
        if (id != userId) {
            throw new RuntimeException("ユーザー詳細を表示できません");
        }
        var command = new UserGetCommand(id);
        var user = userApplicationService.get(command);
        model.addAttribute("user", user.getUserData());
        return "user/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("userCreateForm") UserCreateForm userCreateForm) {
        return "user/new";
    }

    @PostMapping("/new")
    public String create(@Validated @ModelAttribute("userCreateForm") UserCreateForm userCreateForm,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "user/new";
        }
        var command = new UserRegisterCommand(
                userCreateForm.getUsername(),
                userCreateForm.getPassword()
        );
        userApplicationService.register(command);

        return "redirect:/users";
    }
}
