package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value="cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    private static boolean isValid(String str)
    {
        char[] charArray = str.toCharArray();
        for(char c:charArray)
            {
                if (!Character.isLetterOrDigit(c) & !Character.isWhitespace(c)) {
                    return false;
                }
            }

        return true;
    }

    // Request path: /cheese
    @RequestMapping(value="")
    public String index(Model model) {


        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model, @RequestParam String cheeseName, @RequestParam String cheeseDescription) {
        if (cheeseName.isEmpty()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("error", "You must enter a cheese name");
            return "cheese/add";
        }
        else if (!isValid(cheeseName)) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("error", "Cheese name must be alphanumeric");
            return "cheese/add";
        }

        cheeses.put(cheeseName, cheeseDescription);

        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Remove a cheese");
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheese) {
        for (String cheeseName : cheese) {
            cheeses.remove(cheeseName);
        }
        return "redirect:";
    }

    @RequestMapping(value="remove_by_dropdown", method = RequestMethod.GET)
    public String displayRemoveCheeseForm2(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Remove a cheese");
        return "cheese/remove_by_dropdown";
    }

    @RequestMapping(value="remove_by_dropdown", method = RequestMethod.POST)
    public String processRemoveCheeseForm2(@RequestParam String cheeseDelete) {
        cheeses.remove(cheeseDelete);
        return "redirect:";
    }

}
