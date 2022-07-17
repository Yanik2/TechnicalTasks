package com.yan.game.service;

import com.yan.game.dto.InfoDto;

import java.util.List;

public interface IResultService {
    List<InfoDto> getUserInfo(Long id);
    List<InfoDto> getLevelInfo(Long id);
    void setInfo(InfoDto infoDto);
}
