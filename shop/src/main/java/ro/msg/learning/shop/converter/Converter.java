package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.BaseDto;

public interface Converter<Model, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);
    Dto convertModelToDto(Model model);
}

