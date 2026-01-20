package com.fitness.userservice.mapper;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "registerRequest.keyCloakId" , target = "keyCloakId")
    User mapToUser(RegisterRequest registerRequest);

    UserResponse mapToUserRespone(User user);
}
