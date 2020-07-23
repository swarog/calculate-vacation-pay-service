package org.swarog.calculateVacationPay.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class JwtUserDetailsService: UserDetailsService {
    @Value("\${app.password}")
    private lateinit var password: String

    @Value("\${app.username}")
    private lateinit var username: String

    override fun loadUserByUsername(givenUsername: String): UserDetails {
        return if (username == givenUsername) {
            User(username, password,
                    ArrayList())
        } else {
            throw UsernameNotFoundException("User not found with username: $givenUsername")
        }
    }
}
