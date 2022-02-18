package com.yan.game.service;

import com.yan.game.dto.InfoDto;

import java.util.List;

public interface IResultService {
    List<InfoDto> getUserInfo(Integer id);
    List<InfoDto> getLevelInfo(Integer id);
    void setInfo(InfoDto infoDto);
}
