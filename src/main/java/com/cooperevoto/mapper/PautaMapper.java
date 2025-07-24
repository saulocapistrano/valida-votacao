package com.cooperevoto.mapper;

import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.domain.model.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    Pauta toEntity(PautaRequest request);

    PautaResponse toResponse(Pauta pauta);
}