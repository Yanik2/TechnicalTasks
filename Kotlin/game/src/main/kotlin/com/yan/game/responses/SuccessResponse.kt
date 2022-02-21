package com.yan.game.responses

class SuccessResponse: Response() {
    override val state: String
        get() = "success"
    override val code: Int
        get() = 200
}