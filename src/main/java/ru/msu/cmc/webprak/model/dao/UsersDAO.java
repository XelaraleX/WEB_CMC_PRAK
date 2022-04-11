package ru.msu.cmc.webprak.model.dao;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.model.entity.Users;

import java.util.Collection;

public interface UsersDAO extends BaseDAO<Users> {
    @Builder
    @Getter
    class Filter {
        private String userStatus;
        private String fullName;
        private String address;
        private String email;
        private String phoneNumber;
        private String userLogin;
        private String userPassword;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }

    Collection<Users> getUsersByFilter(Filter filter);
}
