package org.andarworld.authenticationservice.security.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.andarworld.authenticationservice.api.exceptions.JwtValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey;
    private final long expirationTime;
    private final String issuer;

    public JwtUtil(@Value("${security.secretKey}") String secretKey,
                   @Value("${security.expirationTime}") long expirationTime,
                   @Value("${security.issuer}") String issuer) {
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
        this.issuer = issuer;
    }

    public String generateToken(UserDetails user)  {
        try {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issuer(issuer)
                .issueTime(new Date())
                .subject(user.getUsername())
                .expirationTime(new Date(System.currentTimeMillis() + expirationTime))
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);

        JWSSigner signer = new MACSigner(secretKey);
        signedJWT.sign(signer);

        return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new JwtValidationException("Failed to generate token!");
        }
    }

    public void validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            JWSVerifier verifier = new MACVerifier(secretKey);
            if(!signedJWT.verify(verifier)) {
                throw new JwtValidationException("Invalid token's signature!");
            }

            Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
            if(expirationDate.before(new Date())) {
                throw new JwtValidationException("Expired token!");
            }

        } catch (ParseException e) {
            throw new JwtValidationException("Malformed jwt token!");
        } catch (JOSEException e) {
            throw new JwtValidationException("Token is not valid!");
        }
    }
}
