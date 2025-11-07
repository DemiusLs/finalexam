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
import plants.finalexam.Service.BenefitService;
import plants.finalexam.model.Benefit;

@Controller
@RequestMapping("/benefits")
public class BenefitController {

    @Autowired
    private BenefitService benefitService;
    
    @GetMapping("/index")
    public String index(Model model){
        List<Benefit> benefits = benefitService.findAll();
        model.addAttribute("benefits", benefits);
        return "/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Benefit benefit= benefitService.getById(id);
        model.addAttribute("benefit", benefit);
        return new String();
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("benefit", new Benefit());
        return "/benefits/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("benefit") Benefit benefitForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/benefits/create-or-edit";
        }
        benefitService.create(benefitForm);

        return "redirect:/benefits/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("benefit", benefitService.getById(id));
        model.addAttribute("edit", true);
        return "/benefits/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("benefit") Benefit benefitForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", true);
            return "/benefits/create-or-edit";
        }
        benefitService.update(benefitForm);

        return "redirect:/benefits/index";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        benefitService.deleteById(id);
        return "redirect:/benefits/index";
    }



}
