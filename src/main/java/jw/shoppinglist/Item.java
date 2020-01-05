package jw.shoppinglist;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
public class Item implements Comparable<Item> {
    @NotNull
    private String name;

    @NotNull
    @Min(value = 0, message = "Positive cost only!")
    private float cost;

    @NotNull
    private long id;

    @Override
    public int compareTo(@NotNull Item other){
        if(this.cost-other.cost > 0 ) {
            return 1;
        }
        else if (this.cost - other.cost == 0 ) {
            return 0;
        }
        else {
            return -1;
        }
    }
}


