package hello.itemservice.web.validation.form;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class ItemSaveForm {

    @NonNull
    private String itemName;

    @NonNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NonNull
    @Max(value = 9999)
    private Integer quantity;
}
