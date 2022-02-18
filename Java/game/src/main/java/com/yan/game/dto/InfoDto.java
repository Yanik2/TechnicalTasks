package com.yan.game.dto;

public class InfoDto {
    private Integer userId;
    private Integer levelId;
    private Integer result;

    public InfoDto() {}

    public InfoDto(Integer userId, Integer levelId, Integer result) {
        this.levelId = levelId;
        this.result = result;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public Integer getResult() {
        return result;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
