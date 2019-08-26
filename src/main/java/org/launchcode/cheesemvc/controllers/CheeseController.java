package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value="cheese")
public class CheeseController {


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


        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese) {
        CheeseData.add(newCheese);

        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove a cheese");
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for (int eachCheeseIndex : cheeseIds) {
            CheeseData.remove(eachCheeseIndex);
        }
        return "redirect:";
    }

    @RequestMapping(value="remove_by_dropdown", method = RequestMethod.GET)
    public String displayRemoveCheeseForm2(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove a cheese");
        return "cheese/remove_by_dropdown";
    }

    @RequestMapping(value="remove_by_dropdown", method = RequestMethod.POST)
    public String processRemoveCheeseForm2(@RequestParam int cheeseDelete) {
        CheeseData.remove(cheeseDelete);
        return "redirect:";
    }

    @RequestMapping(value="edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", CheeseData.getById(cheeseId));
        return "cheese/edit";
    }

    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String processEditForm(int cheeseId, String name, String description) {
        Cheese cheeseToEdit = CheeseData.getById(cheeseId);
        cheeseToEdit.setName(name);
        cheeseToEdit.setDescription(description);
        return "redirect:";
    }

}
