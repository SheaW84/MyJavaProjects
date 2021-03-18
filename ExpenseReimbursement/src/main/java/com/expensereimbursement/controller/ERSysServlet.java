package com.expensereimbursement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


public class ERSysServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8094032509794407100L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().write(new ObjectMapper().writeValueAsString(ERSysHelper.processGet(request,response)));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ERSysHelper.processPost(request,response);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ERSysHelper.processPut(request,response);

    }

}
