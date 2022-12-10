package com.example.shopbe.util;

import com.example.shopbe.config.exception.VsException;
import com.example.shopbe.constant.AuthorityConstant;
import com.nimbusds.jwt.SignedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

@Component("WebTransferRequestHeader")
public class RequestHeader {
  private final HttpServletRequest httpServletRequest;

  public RequestHeader(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }

  /**
   * @return String
   */
  public Long getId() {
    String bearerToken = httpServletRequest.getHeader(AuthorityConstant.AUTHORIZATION);
    if (StringUtils.isEmpty(bearerToken)) {
      return AuthorityConstant.ANONYMOUS_USER;
    }
    String token = bearerToken.substring(7);
    try {
      SignedJWT decodedJWT = SignedJWT.parse(token);
      Map<String, Object> payload = decodedJWT.getPayload().toJSONObject();
      return (Long) payload.get(AuthorityConstant.CLAIM_ID);
    } catch (ParseException e) {
      throw new VsException("Có lỗi sảy ra", e.getMessage());
    }
  }

}
