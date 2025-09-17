package br.com.feliperbdantas.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class ObjectMapper {
    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();
}
