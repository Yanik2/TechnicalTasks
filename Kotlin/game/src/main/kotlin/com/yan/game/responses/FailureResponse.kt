package com.yan.game.responses

class FailureResponse: Response() {
    override val state: String
        get() = "failure"
    override val code: Int
        get() = 400
    var errorMessage: String? = null
}