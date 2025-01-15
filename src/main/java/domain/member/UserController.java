package domain.member;

import global.utils.JsonParser;
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
        }
    }
}
