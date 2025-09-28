package br.com.feliperbdantas.application.mapper;

import br.com.feliperbdantas.application.dto.v1.BookDTO;
import br.com.feliperbdantas.domain.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);

    Book toEntity(BookDTO dto);

    List<BookDTO> toDtoList(List<Book> books);

    List<Book> toEntityList(List<BookDTO> dtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(BookDTO dto, @MappingTarget Book entity);
}

