package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/ExecuteFindArticoloServlet")
public class ExecuteFindArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ExecuteFindArticoloServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	String codiceInputParam = request.getParameter("codice");
	String descrizioneInputParam = request.getParameter("descrizione");
	String prezzoInputStringParam = request.getParameter("prezzo");
	String dataArrivoStringParam = request.getParameter("dataArrivo");

	Date dataArrivoParsed = parseDateArrivoFromString(dataArrivoStringParam);

	Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam,
			Integer.parseInt(prezzoInputStringParam), dataArrivoParsed);

	try {
		List<Articolo> listaByExample = MyServiceFactory.getArticoloServiceInstance()
				.findByExample(articoloInstance);
		request.setAttribute("listaArticoliAttribute", listaByExample);
		request.setAttribute("successMessage", "Operazione di ricerca effettuata con esito positivo");
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("errorMessage", "Attenzione errore nella ricerca.");
		request.getRequestDispatcher("/articolo/find.jsp").forward(request, response);
		return;
	}

	request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);

}

private Date parseDateArrivoFromString(String dataArrivoStringParam) {
	if (StringUtils.isBlank(dataArrivoStringParam))
		return null;
	try {
		return new SimpleDateFormat("yyyy-MM-dd").parse(dataArrivoStringParam);
	} catch (ParseException e) {
		return null;
	}
}
}
