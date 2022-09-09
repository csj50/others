package com.iotek.load;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetXMLServlet
 */
@WebServlet("/GetXMLServlet")
public class GetXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetXMLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String username = URLDecoder.decode(URLDecoder.decode(request.getParameter("username"), "utf-8"), "utf-8");
		String password = URLDecoder.decode(URLDecoder.decode(request.getParameter("password"), "utf-8"), "utf-8");
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		xml.append("<student>");
		xml.append("<name>"+username+"</name>");
		xml.append("<password>"+password+"</password>");
		xml.append("</student>");
		out.println(xml.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
