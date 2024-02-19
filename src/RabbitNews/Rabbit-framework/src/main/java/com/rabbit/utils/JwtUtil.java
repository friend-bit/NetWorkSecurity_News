package com.rabbit.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.Data;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    //有效期为
    public static final Long JWT_TTL=24*60*60*1000L;

    public static final String JWT_KEY="rabbit";

    public static String getUUID(){
        String token= UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }
    public static String createJWT(String subject){
        JwtBuilder builder=getJwtBuilder(subject,null,getUUID());
        return builder.compact();
    }

    public static String createJWT(String subject,Long ttlmillis){
        JwtBuilder builder=getJwtBuilder(subject,ttlmillis,getUUID());
        return builder.compact();
    }
    public static String createJWT(String id,String subject,Long ttlMillis){
        JwtBuilder builder=getJwtBuilder(subject,ttlMillis,id);
        return builder.compact();
    }
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        SecretKey secretKey=genralKey();
        long nowMillis=System.currentTimeMillis();
        Date now=new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis=nowMillis+ttlMillis;
        Date expDate=new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("rabbit")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm,secretKey)
                .setExpiration(expDate);
    }
    public static void main(String[] args) throws Exception{
//        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiYzIxOWY5Yjk5Y2E0N2EzYTQ4M2Q2ZGUyYjVkMWZlNyIsInN1YiI6IjEiLCJpc3MiOiJyYWJiaXQiLCJpYXQiOjE2OTcwNzk2MTksImV4cCI6MTY5NzA4MzIxOX0.44LPXCXYyQYvf85h_OpAkMrHpYcAOpy-vbpm6cTD19Y";
//        Claims claims=parseJWT(token);
        String passwd="1234";
        String token=createJWT(passwd);
        System.out.println(token);
    }


    public static SecretKey genralKey(){
        byte[] encodedkey= Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key=new SecretKeySpec(encodedkey,0,encodedkey.length,"AES");
        return key;
    }

    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey=genralKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
