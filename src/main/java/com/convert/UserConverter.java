package com.convert;

import com.models.FlatUserEntity;
import com.models.User;

public class UserConverter {

    public static FlatUserEntity toEntity(User user) {
        FlatUserEntity entity = new FlatUserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setDateOfBirth(user.getDateOfBirth());
        entity.setCityName(user.getCity().getName());
        entity.setCountryName(user.getCity().getCountry().getName());
        return entity;
    }

}
