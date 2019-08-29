package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="user")
public class UserController {

    private static boolean isItValidLength(String str)
    {
        boolean isValidLength = true;
        if (str.length() < 5 || str.length() > 15) {
            isValidLength = false;
        }

        return isValidLength;
    }

    private static boolean isValidFormat(String str) {
        boolean isFormatValid = true;
        char[] charArray = str.toCharArray();
        for(char c:charArray)
        {
            if (!Character.isLetter(c)) {
                isFormatValid = false;
            }
        }
        return isFormatValid;
    }

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "All Users");
        return "user/index";
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int userId) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a New User");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("title", "Add a New User");
            return "user/add";
        }

        UserData.add(user);
        return "redirect:";




    }
}
