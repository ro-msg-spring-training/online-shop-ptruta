package ro.msg.learning.shop.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model, Dto>
        implements Converter<Model, Dto> {

    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        List<Dto> result=new ArrayList<>();
        models.stream()
                .map(this::convertModelToDto)
                .forEach(result::add);
        return result;
    }

    public List<Model> convertDtosToModels(Collection<Dto> dtos) {
        List<Model> result=new ArrayList<>();
        dtos.stream()
                .map(this::convertDtoToModel)
                .forEach(result::add);
        return result;
    }

}

