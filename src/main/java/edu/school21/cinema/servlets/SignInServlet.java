package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserDAO;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private ApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/signIn.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO userDAO = springContext.getBean("userDAO", UserDAO.class);

        resp.setContentType("text/html");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userDAO.findByEmail(email);
        if (user == null || !password.equals(user.getPassword()))
            req.getRequestDispatcher("/WEB-INF/html/signIn.html").forward(req, resp);
        else
            req.getRequestDispatcher(user.toString()).forward(req, resp);
    }

    @Override
    public void destroy() { }
}