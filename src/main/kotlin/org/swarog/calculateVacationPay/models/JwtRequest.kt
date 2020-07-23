package org.swarog.calculateVacationPay.models

import java.io.Serializable


class JwtRequest : Serializable {
    lateinit var username: String
    lateinit var password: String

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }
}

