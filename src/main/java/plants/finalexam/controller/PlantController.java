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
import plants.finalexam.Service.ControindicationService;
import plants.finalexam.Service.FamilyService;
import plants.finalexam.Service.PlantService;
import plants.finalexam.model.Plant;



@Controller
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;
    @Autowired 
    private BenefitService benefitService;
    @Autowired
    private ControindicationService controindicationService;
    @Autowired
    private FamilyService familyService;
    
    @GetMapping("/index")
    public String index(Model model){
        List<Plant> plants = plantService.findAll();
        model.addAttribute("plants",plants);
        return "/plants/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Plant plant = plantService.getById(id);
        model.addAttribute("plant", plant);
        return new String();
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("plant", new Plant());
        model.addAttribute("benefits", benefitService.findAll());
        model.addAttribute("controindications", controindicationService.findAll());
        model.addAttribute("family", familyService.findAll());
        return "/plants/create-or-edit";
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("plant") Plant formPlant, BindingResult bindingResult ,Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("benefits", benefitService.findAll());
            model.addAttribute("controindications", controindicationService.findAll());
            model.addAttribute("family", familyService.findAll());
            return "/plants/create-or-edit";
        }
        plantService.create(formPlant);
        return "redirect:/plants/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("plant", plantService.getById(id));
        model.addAttribute("benefits", benefitService.findAll());
        model.addAttribute("controindications", controindicationService.findAll());
        model.addAttribute("family", familyService.findAll());
        model.addAttribute("edit", true);
        return "/plants/create-or-edit";
        
    }


    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("plant") Plant formPLant, BindingResult bindingResult, @PathVariable Integer id, Model model){
        if(bindingResult.hasErrors()){

            model.addAttribute("benefits", benefitService.findAll());
            model.addAttribute("controindications", controindicationService.findAll());
            model.addAttribute("family", familyService.findAll());
            model.addAttribute("edit", true);
            return "/plants/create-or-edit";

        }
        plantService.update(formPLant);
        return "redirect:/plants/index";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){

        plantService.deleteById(id);

        return "redirect:/plants/index";
    }



    


}
