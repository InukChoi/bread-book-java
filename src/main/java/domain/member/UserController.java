package domain.member;

import global.utils.JsonParser;
import global.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
    private UserService userService;
    public UserController() {this.userService = new UserService();}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if("/signup".equals(action)) {
            User user = JsonParser.parse(request, User.class);
            user.setAgree(true);
            userService.signup(user);
        }else if ("/login".equals(action)) {
            User user = JsonParser.parse(request, User.class);
            int userIdx = userService.login(user);
            if (userIdx > 0 ) {
                // JWT 토큰 생성
                String token = JwtUtil.generateToken(userIdx, user.getEmail());
                //{"result":true, "userIdx":11, "userName":"test01"
                //{"result":true, "token":"ey....")
                ResLoginDto resDto = new ResLoginDto(true, token);

                Cookie cookie = new Cookie("ATOKEN", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(false);
                cookie.setMaxAge(30*60);
                cookie.setPath("/");
                response.addCookie(cookie);

                JsonParser.parse(response, resDto);
            }else  {
                ResLoginDto resDto = new ResLoginDto(false, "");
                JsonParser.parse(response, resDto);
            }
        }
    }
}
