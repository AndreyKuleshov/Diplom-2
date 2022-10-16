package orders;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderBody {
    private List<String> ingredients;

    public CreateOrderBody(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
