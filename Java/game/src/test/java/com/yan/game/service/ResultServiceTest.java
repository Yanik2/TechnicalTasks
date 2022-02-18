package com.yan.game.service;

import com.yan.game.dao.LevelRepository;
import com.yan.game.dao.ResultRepository;
import com.yan.game.dao.UserRepository;
import com.yan.game.dto.InfoDto;
import com.yan.game.entity.Level;
import com.yan.game.entity.Result;
import com.yan.game.entity.User;
import com.yan.game.service.impl.ResultServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private LevelRepository levelRepositoryMock;

    @InjectMocks
    private IResultService testObject = new ResultServiceImpl();

    private User user;
    private Level level;

    @Before
    public void setup() {
        User user = new User(1, "user");
        user.setResults(List.of(new Result(1, user, new Level(1, "level"))));
        Level level = new Level(1, "level");
        level.setResults(List.of(new Result(1, user, new Level(1, "level"))));
        Mockito.when(userRepositoryMock.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(user));
        Mockito.when(levelRepositoryMock.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(level));
    }

    @Test
    public void getUserInfoTest() {
        List<InfoDto> list = testObject.getUserInfo(1);
        Assertions.assertEquals(list.get(0).getResult(), 1);
        Assertions.assertEquals(list.get(0).getUserId(),1);
        Assertions.assertEquals(list.get(0).getLevelId(), 1);
    }

    @Test
    public void getLevelInfoTest() {
        List<InfoDto> list = testObject.getLevelInfo(1);
        Assertions.assertEquals(list.get(0).getResult(), 1);
        Assertions.assertEquals(list.get(0).getUserId(),1);
        Assertions.assertEquals(list.get(0).getLevelId(), 1);
    }
}
