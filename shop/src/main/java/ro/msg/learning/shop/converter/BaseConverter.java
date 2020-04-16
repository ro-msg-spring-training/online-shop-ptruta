package ro.msg.learning.shop.converter;

import org.springframework.ui.Model;
import ro.msg.learning.shop.dto.BaseDto;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model,Dto extends BaseDto>
        implements Converter<Model,Dto> {


//    public Set<Long> convertModelsToIDs(Set<Model> models) {
//        return models.stream()
//                .map(model -> model.getId())
//                .collect(Collectors.toSet());
//    }
//
//    public  Set<Long> convertDTOsToIDs(Set<Dto> dtos) {
//        return dtos.stream()
//                .map(dto -> dto.getId())
//                .collect(Collectors.toSet());
//    }
//
//    public List<Dto> convertModelsToDtos(Collection<Model> models) {
//        List<Dto> result=new ArrayList<>();
//        models.stream()
//                .map(model -> convertModelToDto(model))
//                .forEach(model -> result.add(model));
//        return result;
//    }

}

