package com.yan.game.dto;

public class InfoDto {
    private Long userId;
    private Long levelId;
    private Long result;

    public InfoDto() {}

    public InfoDto(Long userId, Long levelId, Long result) {
        this.levelId = levelId;
        this.result = result;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public Long getResult() {
        return result;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
