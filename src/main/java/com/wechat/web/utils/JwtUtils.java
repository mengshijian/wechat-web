package com.wechat.web.utils;

import com.wechat.web.consts.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  public static Claims parseJWT(String jsonWebToken) {
    Claims claims = null;
    try {
      claims = Jwts.parser()
        .setSigningKey(DatatypeConverter.parseBase64Binary(Constant.BASE64_SECURITY))
        .parseClaimsJws(jsonWebToken).getBody();
    } catch (Exception ex) {
      logger.error("parseJWT ({})", ex);
    }
    return claims;
  }

  public static String createJWT(String keyObject, long TTLMillis) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long nowMillis = System.currentTimeMillis();
    //生成签名密钥 就是一个base64加密后的字符串？
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.BASE64_SECURITY);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    //添加构成JWT的参数
    JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
      .setId(String.valueOf(System.currentTimeMillis()))          // JWT_ID
      .setSubject(keyObject)                                      // 主题
      .setIssuer(Constant.JWT_ISSUER)                             // 签发者
      .setIssuedAt(new Date())                                    // 签发时间
      .signWith(signatureAlgorithm, signingKey);

    //添加Token过期时间
    if (TTLMillis >= 0) {
      //过期时间
      long expMillis = nowMillis + TTLMillis;
      //现在是什么时间
      Date exp = new Date(expMillis);
      //系统时间之前的token都是不可以被承认的
      builder.setExpiration(exp);
    }
    //生成JWT
    return builder.compact();
  }
}
