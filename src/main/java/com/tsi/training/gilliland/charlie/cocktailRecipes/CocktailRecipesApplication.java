package com.tsi.training.gilliland.charlie.cocktailRecipes;

import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.Cocktail;
import com.tsi.training.gilliland.charlie.cocktailRecipes.cocktail.CocktailRepository;
import com.tsi.training.gilliland.charlie.cocktailRecipes.equipment.*;
import com.tsi.training.gilliland.charlie.cocktailRecipes.garnish.*;
import com.tsi.training.gilliland.charlie.cocktailRecipes.glass.*;
import com.tsi.training.gilliland.charlie.cocktailRecipes.ingredient.*;
import com.tsi.training.gilliland.charlie.cocktailRecipes.instruction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/cocktails")
public class CocktailRecipesApplication {

	@Autowired
	private GlassRepository glassRepository;
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private GarnishRepository garnishRepository;
	@Autowired
	private InstructionRepository instructionRepository;
	@Autowired
	private CocktailRepository cocktailRepository;


	public static void main(String[] args) {
		try{
			SpringApplication.run(CocktailRecipesApplication.class, args);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}



	//////////////////////////////// Cocktail //////////////////////////////////////////
	@GetMapping("/cocktail/getAll")
	public @ResponseBody Iterable<Cocktail> getAll(){
		return cocktailRepository.findAll();
	}

	@GetMapping("/cocktail/getCocktail")
	public @ResponseBody Object getCocktail(@RequestParam int id){
		Optional<Cocktail> cocktailOptional = cocktailRepository.findById(id);
		if(!cocktailOptional.isPresent()){
			return "Cocktail not found";
		}
		Cocktail cocktail = cocktailOptional.get();
		return cocktail;
	}

	@GetMapping("/cocktail/searchCocktailsByName")
	public @ResponseBody Iterable<Cocktail> searchCocktailsByName(String query){
		Iterable<Cocktail> cocktails = cocktailRepository.findAll();
		ArrayList<Cocktail> results = new ArrayList<>();
		for(Cocktail c : cocktails){
			if(c.getName().contains(query)){
				results.add(c);
			}
		}
		return results;
	}

	@GetMapping("/cocktail/searchCocktailsByIngredients")
	public @ResponseBody Iterable<Cocktail> searchCocktailsByIngredients(String query){
		Iterable<Cocktail> cocktails = cocktailRepository.findAll();
		ArrayList<Cocktail> results = new ArrayList<>();
		outerloop:
		for(Cocktail c : cocktails){
			for(Instruction inst : c.getInstructions()){
				for(Ingredient ing : inst.getIngredients()){
					if(ing.getName().contains(query)){
						results.add(c);
						continue outerloop;
					}
					if(ing.getType().contains(query)){
						results.add(c);
						continue outerloop;
					}
				}
			}
		}
		return results;
	}

	@PostMapping("/cocktail/addCocktail")
	public @ResponseBody String addCocktail(@RequestParam String name, @RequestParam List<Integer> instructionIds, @RequestParam String description){

		// Create cocktail
		Cocktail cocktail = new Cocktail();

		// Setting the name
		cocktail.setName(name);

		// Setting the instructions
		if(instructionIds != null){
			for(Integer i : instructionIds){
				Optional<Instruction> instructionOptional = instructionRepository.findById(i);
				if(instructionOptional.isPresent()){
					Instruction singleInstruction = instructionOptional.get();
					cocktail.addInstruction(singleInstruction);
				}
			}
		}
		cocktail.setNoOfSteps(instructionIds.size());

		// Setting the description
		cocktail.setDescription(description);

		// Saving the cocktail to the database
		cocktailRepository.save(cocktail);

		return cocktail.getName() + " created";
	}

	@PutMapping("/cocktail/updateCocktail")
	public @ResponseBody String updateCocktail(@RequestBody Cocktail cocktail){
		Optional<Cocktail> cocktailInDb = cocktailRepository.findById(cocktail.getId());
		if(cocktailInDb.isEmpty()){
			return "Cocktail not found";
		}
		cocktailRepository.save(cocktail);
		return cocktail.getName() + " updated";
	}

	@DeleteMapping("/cocktail/deleteCocktail")
	public @ResponseBody String deleteCocktail(@RequestParam int id){
		Optional<Cocktail> cocktailOptional = cocktailRepository.findById(id);
		if(!cocktailOptional.isPresent()){
			return "Cocktail not found";
		}
		cocktailRepository.deleteById(id);
		return cocktailOptional.get().getName() + " deleted";
	}




	//////////////////////////////// Instruction //////////////////////////////////////////
	@GetMapping("/instruction/getAll")
	public @ResponseBody Iterable<Instruction> getInstructions(){
		return instructionRepository.findAll();
	}

	@GetMapping("/instruction/getInstruction")
	public @ResponseBody Object getInstruction(@RequestParam int id){
		Optional<Instruction> instructionOptional = instructionRepository.findById(id);
		if(!instructionOptional.isPresent()){
			return "Instruction not found";
		}
		Instruction instruction = instructionOptional.get();
		return instruction;
	}

	@PostMapping("/instruction/addInstruction")
	public @ResponseBody String addInstruction(@RequestParam(required = false) List<Integer> ingredientIds, @RequestParam(required = false) List<Integer> equipmentIds, @RequestParam(required = false) Integer glassId, @RequestParam(required = false) Integer garnishId, @RequestParam(required = false) String description){
		// Create instruction
		Instruction instruction = new Instruction();

		// Finding Equipment
		if(equipmentIds != null){
			for(Integer i : equipmentIds){
				Optional<Equipment> singleEquipmentOptional = equipmentRepository.findById(i);
				if(singleEquipmentOptional.isPresent()){
					Equipment singleEquipment = singleEquipmentOptional.get();
					instruction.addEquipment(singleEquipment);
				}
			}
		}

		// Getting Ingredients
		if(ingredientIds != null){
			for(Integer i : ingredientIds){
				Optional<Ingredient> singleIngredientOptional = ingredientRepository.findById(i);
				if(singleIngredientOptional.isPresent()){
					Ingredient singleIngredient = singleIngredientOptional.get();
					instruction.addIngredients(singleIngredient);
				}
			}
		}

		//Finding glass
		Glass glass = null;
		if(glassId != null){
			Optional<Glass> glassOptional = glassRepository.findById(glassId);
			if(glassOptional.isEmpty()){
				return "Glass not found";
			}
			glass = glassOptional.get();
		}

		// Finding garnish
		Garnish garnish = null;
		if(garnishId != null){
			Optional<Garnish> garnishOptional = garnishRepository.findById(garnishId);
			if(garnishOptional.isEmpty()){
				return "Garnish not found";
			}
			garnish = garnishOptional.get();
		}
		instruction.setDescription(description);
		instruction.setGlass(glassId);
		instruction.setGarnish(garnishId);
		instructionRepository.save(instruction);
		return "Instruction Added";
	}

	@PutMapping("/instruction/updateInstruction")
	public @ResponseBody String updateInstruction(@RequestBody Instruction instruction){
		Optional<Instruction> instructionInDb = instructionRepository.findById(instruction.getId());
		if(instructionInDb.isEmpty()){
			return "Instruction not found";
		}
		instructionRepository.save(instruction);
		return "Instruction updated";
	}

	@DeleteMapping("/instruction/deleteInstruction")
	public @ResponseBody String deleteInstruction(@RequestParam int id){
		Optional<Instruction> instruction = instructionRepository.findById(id);
		if(instruction.isEmpty()){
			return "Instruction not found";
		}
		instructionRepository.deleteById(id);
		return "Instruction deleted";
	}




	//////////////////////////////// Glasses //////////////////////////////////////////////
	@GetMapping("/glass/getAll")
	public @ResponseBody Iterable<Glass> getGlasses(){
		return glassRepository.findAll();
	}

	@GetMapping("/glass/getGlass")
	public @ResponseBody String getGlass(@RequestParam int id){
		return glassRepository.findById(id).get().toString();
	}

	@PostMapping("/glass/addGlass")
	public @ResponseBody String addGlass(@RequestParam String type, @RequestParam int volume){
		Glass glass = new Glass(type, volume);
		glassRepository.save(glass);
		return "Saved";
	}

	@PutMapping("/glass/updateGlass")
	public @ResponseBody String updateGlass(@RequestBody Glass glass){
		Optional<Glass> glassInDb = glassRepository.findById(glass.getId());
		if(glassInDb.isEmpty()){
			return "Glass is not in database";
		}
		glassRepository.save(glass);
		return "Glass Updated";
	}

	@DeleteMapping("/glass/deleteGlass")
	public @ResponseBody String deleteGlass(@RequestParam int id){
		Optional<Glass> glassInDb = glassRepository.findById(id);
		if(glassInDb.isEmpty()){
			return "Glass not found";
		}
		glassRepository.deleteById(id);
		return "Glass Deleted";
	}



	//////////////////////////////// Ingredient //////////////////////////////////////////////
	@GetMapping("/ingredient/getAll")
	public @ResponseBody Iterable<Ingredient> getIngredients(){
		return ingredientRepository.findAll();
	}

	@GetMapping("/ingredient/getIngredient")
	public @ResponseBody Object getIngredient(@RequestParam int id){
		Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
		if(ingredientOptional.isEmpty()){
			return "Ingredient not found";
		}
		Ingredient ingredient = ingredientOptional.get();
		return ingredient;
	}

	@PostMapping("/ingredient/addIngredient")
	public @ResponseBody String addIngredient(@RequestParam String name, @RequestParam String type, @RequestParam float abv, String storage, String description){
		Ingredient ingredient = new Ingredient(name, type, abv, storage, description);
		ingredientRepository.save(ingredient);
		return "Saved";
	}

	@PutMapping("/ingredient/updateIngredient")
	public @ResponseBody String updateIngredient(@RequestBody Ingredient ingredient){
		Optional<Ingredient> ingredientInDb = ingredientRepository.findById(ingredient.getId());
		if(ingredientInDb.isEmpty()){
			return "Ingredient is not in database";
		}
		ingredientRepository.save(ingredient);
		return "Ingredient Updated";
	}

	@DeleteMapping("/ingredient/deleteIngredient")
	public @ResponseBody String deleteIngredient(@RequestParam int id){
		Optional<Ingredient> ingredient = ingredientRepository.findById(id);
		if(ingredient.isEmpty()){
			return "No ingredient found";
		}
		ingredientRepository.deleteById(id);
		return "Ingredient deleted";
	}





	//////////////////////////////// Equipment //////////////////////////////////////////////
	@GetMapping("/equipment/getAll")
	public @ResponseBody Iterable<Equipment> getEquipment(){
		return equipmentRepository.findAll();
	}

	@GetMapping("/equipment/getEquipment")
	public @ResponseBody Object getEquipment(@RequestParam int id){
		Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
		if(equipmentOptional.isEmpty()){
			return "Equipment not found";
		}
		Equipment equipment = equipmentOptional.get();
		return equipment;
	}

	@PostMapping("/equipment/addEquipment")
	public @ResponseBody String addEquipment(@RequestParam String name, @RequestParam boolean isPowered){
		Equipment equipment = new Equipment(name, isPowered);
		equipmentRepository.save(equipment);
		return "Saved";
	}

	@PutMapping("/equipment/updateEquipment")
	public @ResponseBody String updateEquipment(@RequestBody Equipment equipment){
		Optional<Equipment> equipmentInDb = equipmentRepository.findById(equipment.getId());
		if(equipmentInDb.isEmpty()){
			return "Equipment not in database";
		}
		equipmentRepository.save(equipment);
		return "Equipment updated";
	}

	@DeleteMapping("/equipment/deleteEquipment")
	public @ResponseBody String deleteEquipment(@RequestParam int id){
		Optional<Equipment> equipmentInDb = equipmentRepository.findById(id);
		if(equipmentInDb.isEmpty()){
			return "Equipment is not in database";
		}
		equipmentRepository.deleteById(id);
		return "Equipment deleted";
	}


	////////////////////////////// Garnish //////////////////////////////////////////////
	@GetMapping("/garnish/getAll")
	public @ResponseBody Iterable<Garnish> getGarnish(){
		return garnishRepository.findAll();
	}

	@GetMapping("/garnish/getGarnish")
	public @ResponseBody Optional<Garnish> getGarnish(@RequestParam int id){
		return garnishRepository.findById(id);
	}

	@PostMapping("/garnish/addGarnish")
	public @ResponseBody String addGarnish(@RequestParam String type, @RequestParam String storage){
		Garnish garnish = new Garnish(type, storage);
		garnishRepository.save(garnish);
		return "Saved";
	}

	@PutMapping("/garnish/updateGarnish")
	public @ResponseBody String updateGarnish(@RequestBody Garnish garnish){
		Optional<Garnish> garnishInDb = garnishRepository.findById(garnish.getId());
		if(garnishInDb.isEmpty()){
			return "Garnish is not in database";
		}
		garnishRepository.save(garnish);
		return "Garnish updated";
	}

	@DeleteMapping("/garnish/deleteGarnish")
	public @ResponseBody String deleteGarnish(@RequestParam int id){
		Optional<Garnish> garnishInDb = garnishRepository.findById(id);
		if(garnishInDb.isEmpty()){
			return "Garnish is not in database";
		}
		garnishRepository.deleteById(id);
		return "Garnish deleted";
	}



}
