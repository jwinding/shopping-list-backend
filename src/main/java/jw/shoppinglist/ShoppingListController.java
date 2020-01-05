package jw.shoppinglist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="/", produces = "application/json")
@CrossOrigin(origins="*",allowedHeaders="*")
public class ShoppingListController {

    private ArrayList<Item> items;
    private long id;

    public ShoppingListController(){
        id = 1;
        items = new ArrayList<Item>();
        Item test = new Item("Goose", 12.3f, id);
        id++;
        items.add(test);
    }

    @GetMapping("")
    public ArrayList<Item> getList(){
        Collections.sort(items);
        return items;
    }

    @GetMapping("summary")
    public Map<String, Object> getSummary(){

        if(items.isEmpty()) {
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("isEmpty", true);
            json.put("cheapest", "");
            json.put("mostExpensive", "");
            json.put("totalCost", "0" );
            return json;
        } else {
            Collections.sort(items);
            Item cheapest = items.get(0);
            Item mostExpensive = items.get(items.size()-1);
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("isEmpty", false);
            json.put("cheapest", cheapest);
            json.put("mostExpensive", mostExpensive);
            json.put("totalCost", computeTotalCost() );

            return json;
        }
    }

    private float computeTotalCost() {
        float sum = 0;
        for( Item i : items ){
            sum += i.getCost();
        }
        return sum;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Item addItem(@org.jetbrains.annotations.NotNull @RequestBody Map<String, String> json){
        String name = json.get("name");
        Float cost = Float.parseFloat(json.get("cost"));
        Item item = new Item(name,cost,id);
        id++;
        items.add(item);
        return item;
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Item deleteItem(@PathVariable("id") long id){
        if(items.isEmpty()){ return null; }
        Item item = null;
        for(Item i : items){
            if(i.getId() == id){
                item = i;
                break;
            }
        }
        if(item != null) {
            items.remove(item);
            return item;
        } else {
            return null;
        }
    }

}
