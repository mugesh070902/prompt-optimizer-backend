package com.promptoptimizer.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class JwtFilter implements Filter{

public static String currentUserEmail;

@Override
public void doFilter(
ServletRequest request,
ServletResponse response,
FilterChain chain)

throws IOException,ServletException{

HttpServletRequest req=
(HttpServletRequest)request;

String header=
req.getHeader("Authorization");

if(header!=null &&
header.startsWith("Bearer ")){

String token=
header.substring(7);

try{
currentUserEmail=
JwtUtil.getEmail(token);
}
catch(Exception e){
currentUserEmail=null;
}
}

chain.doFilter(request,response);

}
}
