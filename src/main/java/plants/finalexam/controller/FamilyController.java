package plants.finalexam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import plants.finalexam.Service.FamilyService;

import plants.finalexam.model.Family;

@Controller
@RequestMapping("/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;
    
    @GetMapping("/index")
    public String index(Model model){
        List<Family> families = familyService.findAll();
        model.addAttribute("families", families);
        return "/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Family family= familyService.getById(id);
        model.addAttribute("family", family);
        return new String();
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("family", new Family());
        return "/families/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("family") Family familyForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/families/create-or-edit";
        }
        familyService.create(familyForm);

        return "redirect:/families/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("family", familyService.getById(id));
        model.addAttribute("edit", true);
        return "/families/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("family") Family familyForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", true);
            return "/families/create-or-edit";
        }
        familyService.update(familyForm);

        return "redirect:/families/index";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        familyService.deleteById(id);
        return "redirect:/families/index";
    }



}
