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
import plants.finalexam.Service.ControindicationService;
import plants.finalexam.model.Controindication;

@Controller
@RequestMapping("/controindications")
public class ControindicationController {

    @Autowired
    private ControindicationService controindicationService;
    
    @GetMapping("/index")
    public String index(Model model){
        List<Controindication> controindications = controindicationService.findAll();
        model.addAttribute("controindication", controindications);
        return "/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Controindication controindication= controindicationService.getById(id);
        model.addAttribute("controindication", controindication);
        return new String();
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("controindication", new Controindication());
        return "/controindications/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("controindication") Controindication controindicationForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/controindication/create-or-edit";
        }
        controindicationService.create(controindicationForm);

        return "redirect:/controindications/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("controindication", controindicationService.getById(id));
        model.addAttribute("edit", true);
        return "/controindications/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("controindication") Controindication controindicationForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", true);
            return "/controindications/create-or-edit";
        }
        controindicationService.update(controindicationForm);

        return "redirect:/controindications/index";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        controindicationService.deleteById(id);
        return "redirect:/controindications/index";
    }



}
