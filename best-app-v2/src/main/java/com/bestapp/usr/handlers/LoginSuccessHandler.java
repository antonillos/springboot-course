package com.bestapp.usr.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth) throws IOException, ServletException {

        SessionFlashMapManager flashManager = new SessionFlashMapManager();
        FlashMap map = new FlashMap();
        map.put("success", "You have sign in! welcome ".concat(auth.getName()));

        flashManager.saveOutputFlashMap(map, req, res);

        super.onAuthenticationSuccess(req, res, auth);
    }

}
