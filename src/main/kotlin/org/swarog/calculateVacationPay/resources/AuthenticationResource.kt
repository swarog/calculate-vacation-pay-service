package org.swarog.calculateVacationPay.resources

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.swarog.calculateVacationPay.config.JwtTokenUtil
import org.swarog.calculateVacationPay.models.JwtRequest
import org.swarog.calculateVacationPay.models.JwtResponse
import org.swarog.calculateVacationPay.service.JwtUserDetailsService

@RestController
class AuthenticationResource(private val authenticationManager: AuthenticationManager,
                             private val jwtTokenUtil: JwtTokenUtil,
                             private val userDetailsService: JwtUserDetailsService)
{

    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<JwtResponse> {
        authenticate(authenticationRequest.username, authenticationRequest.password)
        val userDetails: UserDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.username)
        val token = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok<JwtResponse>(JwtResponse(token))
    }

    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}
