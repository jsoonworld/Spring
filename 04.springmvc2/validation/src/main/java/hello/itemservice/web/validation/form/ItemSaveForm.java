package hello.itemservice.web.validation.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NonNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NonNull
    @Max(value = 9999)
    private Integer quantity;
}
