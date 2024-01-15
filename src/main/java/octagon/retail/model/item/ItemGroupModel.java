package octagon.retail.model.item;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import octagon.retail.entity.ItemGroups;

@Data
@Validated
public class ItemGroupModel {

    @JsonProperty(value = "id")
    private Long id;

    @NotNull(message = "name is null")
    @JsonProperty(value = "name")
    private String name;

    @NotNull(message = "code is null")
    @JsonProperty(value = "code")
    private Double code;

    @NotNull(message = "parentId is null")
    @JsonProperty(value = "parentId")
    private Long parentId;

    @NotNull(message = "color is null")
    @JsonProperty(value = "color")
    private Long color;

    @NotNull(message = "isPrimary is null")
    @JsonProperty(value = "isPrimary")
    private Boolean isPrimary;

    public static ItemGroups convert(ItemGroups itemGroup, ItemGroupModel model) {
        if (itemGroup == null)
            itemGroup = new ItemGroups();
        itemGroup.setName(model.getName());
        itemGroup.setCode(model.getCode());
        itemGroup.setColor(model.getColor());
        itemGroup.setIsPrimary(model.getIsPrimary());
        itemGroup.setParentId(model.getParentId());
        return itemGroup;
    }

    public static ItemGroupModel convertToModel(ItemGroups itemGroup) {

        var model = new ItemGroupModel();

        model.setCode(itemGroup.getCode());
        model.setColor(itemGroup.getColor());
        model.setIsPrimary(itemGroup.getIsPrimary());
        model.setParentId(itemGroup.getParentId());
        return model;
    }

}
