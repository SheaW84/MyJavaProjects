package com.expensereimbursement.controller;

import com.expensereimbursement.models.Employee;
import com.expensereimbursement.models.ReimbursementRequest;
import com.expensereimbursement.service.EmployeeService;
import com.expensereimbursement.service.ReimbursementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ERSysHelper {

    public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String URI = request.getRequestURI();
        final String RESOURCE = URI.replace("/ERSys/api","");
        ReimbursementService reimbursementService = new ReimbursementService();

        switch (RESOURCE){
            case "/view/users":
                RequestDispatcher viewdispatcher = request.getRequestDispatcher("/employees.html");
                viewdispatcher.forward(request,response);


            case "/user/all":
                response.setStatus(200);
                return new EmployeeService().findAll();

            case "/logout":
                HttpSession hs = request.getSession();
                if(hs!=null){
                    hs.invalidate();

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
                    dispatcher.forward(request,response);
                }
                return "No user is logged in";

            case "/request/all":
                response.setStatus(200);
                return reimbursementService.findAll();

            case"/request/menu":
                RequestDispatcher dispatcherMenu = request.getRequestDispatcher("/requestmenu.html");
                dispatcherMenu.forward(request,response);

            case "/request/submit":
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/submitrequest.html");
                dispatcher.forward(request,response);
           


            case "/request/pending/view":

                response.sendRedirect("/ERSys/requestpend.html");


            case"/request/pending/employee":
                hs = request.getSession();
                String email = hs.getAttribute("email").toString();
                Employee employee = new EmployeeService().findByEmail(email);
                return new ReimbursementService().findByUser(employee);


            case "/request/pending/man":
                response.setStatus(200);
                hs = request.getSession();
                String manEmail = hs.getAttribute("email").toString();
                return new EmployeeService().findManager(manEmail);


            case "/request/resolved":
                response.setStatus(200);
                return new ReimbursementService().findResolved();

            case "/employee/details":
                hs = request.getSession();
                String e = hs.getAttribute("email").toString();
                return new EmployeeService().findByEmail(e);

            default:
                response.setStatus(404);
                return "Sorry. The resource you have requested does not exist";
        }
    }

    public static void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        final String URI = request.getRequestURI();
        final String RESOURCE = URI.replace("/ERSys/api","");
        ReimbursementService reimbursementService = new ReimbursementService();

        switch (RESOURCE){

            case "/login":
                String email = request.getParameter("email");
                final String password = request.getParameter("password");

                if(new EmployeeService().isValidEmployee(email,password)){
                    HttpSession hs = request.getSession();
                    hs.setAttribute("email",email);

                   RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.html");
                    dispatcher.forward(request,response);


                }
                break;

            case "/login/man":

                final String manEmail = request.getParameter("email");
                final String manPassword = request.getParameter("password");
                

                if(new EmployeeService().isValidManager(manEmail,manPassword)){
                    HttpSession hs = request.getSession();
                    hs.setAttribute("email",manEmail);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/manager.html");
                    dispatcher.forward(request,response);

                }

                break;

            case "/request/update":

                final int requestId = Integer.parseInt(request.getParameter("requestId"));
                final boolean status =Boolean.parseBoolean(request.getParameter("status"));

                ReimbursementRequest r = reimbursementService.findById(requestId);
                r.setResolved(status);
                reimbursementService.update(r);
                response.getWriter().write("This request has been updated");
                break;

            case"/request/new":

                final String requestReason = request.getParameter("reason");
                final double amount = Double.parseDouble(request.getParameter("amount"));
                final String employeeEmail = request.getParameter("email");

                EmployeeService employeeService = new EmployeeService();
                Employee e = employeeService.findByEmail(employeeEmail);

                ReimbursementRequest rr = new ReimbursementRequest(requestReason,amount,false,e);

                reimbursementService.insert(rr);

                response.sendRedirect("/ERSys/api/request/submit");
                break;


            default:
                response.setStatus(404);
                response.getWriter().write("Please try again");

        }

    }

    public static void processPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        final String URI = request.getRequestURI();
        final String RESOURCE = URI.replace("ERSys/api","");
        ReimbursementService reimbursementService = new ReimbursementService();

        switch(RESOURCE) {


            case"/request/new":

                final String requestReason = request.getParameter("reason");
                final double amount = Double.parseDouble(request.getParameter("amount"));
                final String employeeEmail = request.getParameter("email");

                EmployeeService employeeService = new EmployeeService();
                Employee e = employeeService.findByEmail(employeeEmail);

                ReimbursementRequest r = new ReimbursementRequest(requestReason,amount,false,e);

                reimbursementService.insert(r);
                break;

            default:
                response.setStatus(404);
                response.getWriter().write("We're sorry, Please try again");
        }


    }

}
