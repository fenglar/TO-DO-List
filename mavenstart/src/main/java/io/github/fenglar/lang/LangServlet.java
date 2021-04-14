package io.github.fenglar.lang;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang" , urlPatterns = "/api/langs")
public class LangServlet extends HttpServlet {
    private final Logger logger=  LoggerFactory.getLogger(LangServlet.class);

    private LangService service;
    private ObjectMapper mapper;

    /**
     * Servlet containter need it.
     */
    @SuppressWarnings("unused")
    public LangServlet(){
        this(new LangService(), new ObjectMapper());
    }

    LangServlet(LangService service, ObjectMapper mapper){
        this.mapper=mapper;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got" + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
    mapper.writeValue(resp.getOutputStream(), service.findAll() );

    }
}