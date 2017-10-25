package servlets;

import beans.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        request.setAttribute("message",message);
        
        Cookie rememberUsername = getCookie(request,"rememberUsername");
        if(rememberUsername!=null) 
            request.setAttribute("rememberUsername", rememberUsername.getValue());
        
        if("Logged Out".equals(message)) 
            request.getSession().invalidate();
        
        if (request.getSession().getAttribute("username")!=null)
            response.sendRedirect("/home");
        else
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        if(!username.isEmpty() && !password.isEmpty()){
            User user = new User(username,password);
            if(UserService.login(user)){
                if (request.getParameter("remember")!=null){
                    Cookie c = new Cookie("rememberUsername", username);
                    c.setMaxAge(-1);
                    response.addCookie(c);
                }
                else{
                    Cookie c = getCookie(request,"rememberUsername");
                    if (c!=null){ 
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
                request.getSession().setAttribute("username", user.getUsername());
                response.sendRedirect("/home");
            }
            else { 
                request.setAttribute("message", "Invalid Login, Either Username or Password Incorrect");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
        else{ 
            request.setAttribute("message", "Both Fields Required");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
    
    private Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if (cookies==null)
            return null;
        for (Cookie cookie : cookies)
            if (cookie.getName().equals(name))
                return cookie;
        return null;
    }
    
}
