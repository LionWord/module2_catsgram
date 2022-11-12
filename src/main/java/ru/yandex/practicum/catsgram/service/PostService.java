package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.function.Supplier;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserService userService;

    @Autowired
    public PostService(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    public Collection<Post> findPostsByUser(String userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(new Supplier<UserNotFoundException>() {
                    @Override
                    public UserNotFoundException get() {
                        return new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден.");
                    }
                });

        return postDao.findPostsByUser(user);
    }
}