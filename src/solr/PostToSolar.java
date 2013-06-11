package solr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.common.SolrInputDocument;

/**
 * Servlet implementation class PostToSolar
 */
public class PostToSolar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dvdName = request.getParameter("dvdName");
		String dvdType = request.getParameter("dvdType");
		Integer type = new Integer(dvdType);
		DvdType dType = SolrUtils.getInstance().getTypeyId(type);
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("name", dvdName);
		doc.addField("type", dType.getTypeName());
		System.out.println(doc.toString() + " from PostToSolar Servlet");
		try
		{
			SolrUtils.getInstance().postToSolr(doc);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
		

	}

}
