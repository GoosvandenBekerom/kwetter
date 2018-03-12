package com.goosvandenbekerom.interceptor;

import com.goosvandenbekerom.annotation.Profanity;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.util.RegexHelpers;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Profanity
public class KweetInterceptor  {
    @AroundInvoke
    public Object profanityFilter(InvocationContext ctx) throws Exception {
        for (Object param : ctx.getParameters()) {
            if (param instanceof Kweet) {
                Kweet kweet = (Kweet) param;
                String msg = kweet.getMessage();
                kweet.setMessage(msg.replaceAll(RegexHelpers.BAD_WORDS, "KWOOPS"));
            }
        }
        return ctx.proceed();
    }
}
