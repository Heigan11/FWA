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

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private ApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/signUp.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO userDAO = springContext.getBean("userDAO", UserDAO.class);

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !phone.isEmpty()) {
//            req.getRequestDispatcher("/WEB-INF/html/registered.html").forward(req, resp);
            if (userDAO.findByEmail(email) == null)
                userDAO.saveUser(new User(name, surname, phone, email, password));
            req.getRequestDispatcher(userDAO.showTable().toString()).forward(req, resp);
        } else
            req.getRequestDispatcher("/WEB-INF/html/signUp.html").forward(req, resp);
    }

    @Override
    public void destroy() { }
}