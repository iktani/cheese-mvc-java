package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseType;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

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


        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());

            return "cheese/add";
        }
        cheeseDao.save(newCheese);

        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove a cheese");
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for (int cheeseId : cheeseIds) {
            cheeseDao.deleteById(cheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value="remove_by_dropdown", method = RequestMethod.GET)
    public String displayRemoveCheeseForm2(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove a cheese");
        return "cheese/remove_by_dropdown";
    }

    @RequestMapping(value="remove_by_dropdown", method = RequestMethod.POST)
    public String processRemoveCheeseForm2(@RequestParam int cheeseDelete) {
        cheeseDao.deleteById(cheeseDelete);
        return "redirect:";
    }

    @RequestMapping(value="edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", cheeseDao.findById(cheeseId).get());
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";
    }

    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String processEditForm(int cheeseId, @ModelAttribute @Valid Cheese editCheese, Errors errors,  Model model) {

        if (errors.hasErrors()){
            model.addAttribute("cheeseTypes", CheeseType.values());

            return "cheese/edit";
        }
        Cheese cheeseToEdit = cheeseDao.findById(cheeseId).get();
        cheeseToEdit.setName(editCheese.getName());
        cheeseToEdit.setDescription(editCheese.getDescription());
        cheeseToEdit.setType(editCheese.getType());
        cheeseToEdit.setRating(editCheese.getRating());
        cheeseDao.save(cheeseToEdit);
        return "redirect:";
    }

}
