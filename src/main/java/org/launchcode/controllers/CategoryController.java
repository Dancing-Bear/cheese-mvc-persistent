package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Greg on 6/30/2017.
 */

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;


    // Request path: /category
    @RequestMapping(value = "")
    private String index(Model model) {
        Iterable<Category> allCategories = categoryDao.findAll();
        model.addAttribute("title", "Categories");
        model.addAttribute("allCategories", allCategories);

        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());

        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return "category/add";
        }

        categoryDao.save(category);

        return "redirect:";
    }






}
