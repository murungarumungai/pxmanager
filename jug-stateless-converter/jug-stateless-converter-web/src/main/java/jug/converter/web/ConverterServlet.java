package jug.converter.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jug.converter.ejb.CurrencyConverterLocal;

@WebServlet("/ConverterServlet")
public class ConverterServlet extends HttpServlet {

	static String PAGE_HEADER = "<html><head><title>CurrencyConverter</title></head><body>"; //

	static String PAGE_FOOTER = "</body></html>";

	/*
	 * The ConverterServlet class uses dependency injection to obtain a reference to
	 * ConverterBean.
	 */
	/**
	 * Because the developer is delegating the work to the platform, resource
	 * dependency injection is also called <b>inversion of control</b>. The
	 * primary reasons to use injection over the traditional manual approach are
	 * simplicity and robustness. Code written using inversion of control is
	 * much easier to develop and maintain.
	 * */
	@EJB
	CurrencyConverterLocal currencyConverter;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		// display input UI Controls
		writer.println("<FORM method=\"get\">");
		writer.println("<TABLE>");
		writer.println("<TR>");
		writer.println("<TD> Enter RON Ammount:</TD>");
		writer.println("<TD><INPUT type=\"text\" name=\"RON\"/></TD>");
		writer.println("</TR>");
		writer.println("<TR>");
		writer.println("<TD><INPUT type=\"submit\" value=\"RON-EUR\"/></TD>");
		writer.println("<TD></TD>");
		writer.println("</TR>");
		writer.println("</TABLE>");
		writer.println("</FORM>");
		if (req.getParameter("RON") != null) {
			// display result
			String ronStr = req.getParameter("RON");
			BigDecimal ron = new BigDecimal(ronStr);
			BigDecimal eur = currencyConverter.converRonToEuro(ron);
			writer.println(eur);
		}
		writer.println(PAGE_FOOTER);
		writer.close();
	}

}
