package com.alice.libraryapi.controller.mappers;

import com.alice.libraryapi.controller.dto.UsuarioDTO;
import com.alice.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

}
