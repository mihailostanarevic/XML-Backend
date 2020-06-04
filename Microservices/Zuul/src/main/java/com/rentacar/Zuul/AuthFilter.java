package com.rentacar.Zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({"IfStatementWithIdenticalBranches", "SpellCheckingInspection"})
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private AuthClient authClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if(request.getHeader("Auth-Token") == null) {
            return null;
        }

        String token = request.getHeader("Auth-Token");
        System.out.println("token: " + token);
        try {
            if(authClient.verify(token)) {
                System.out.println("tokenIF: " + token);
                ctx.addZuulRequestHeader("Auth-Token", token);
            }
        } catch (FeignException.NotFound e) {
            setFailedRequest("Consumer does not exist!", 403);
        }

        return null;
    }


}
