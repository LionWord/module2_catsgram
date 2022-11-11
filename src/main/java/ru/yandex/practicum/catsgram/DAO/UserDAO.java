package ru.yandex.practicum.catsgram.DAO;

import ru.yandex.practicum.catsgram.model.User;

import java.util.Optional;

public interface UserDAO {

    Optional<User> findUserById(String id);
}
