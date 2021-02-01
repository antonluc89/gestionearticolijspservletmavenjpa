package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteArticoloServlet")
public class ExecuteDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteArticoloServlet() {
		super();
	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idArrivoStringParam = request.getParameter("idArticolo");
		
		Long idArrivoLongParsed = parseIdArrivoFromString(idArrivoStringParam);

		

		try {
			Articolo articoloDeleteInstance = MyServiceFactory.getArticoloServiceInstance().caricaSingoloElemento(idArrivoLongParsed);

			MyServiceFactory.getArticoloServiceInstance().rimuovi(articoloDeleteInstance);
		
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
		

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/delete.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
	}
	

	private Long parseIdArrivoFromString(String idArrivoAtringParam) {
		if (StringUtils.isBlank(idArrivoAtringParam))
			return null;

		try {
			return Long.parseLong(idArrivoAtringParam);
		} catch (Exception e) {
			return null;
		}
	}
}