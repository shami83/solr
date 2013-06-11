package solr;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchResultFromSolar
 */
public class SearchResultFromSolar extends HttpServlet {
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
		String typeStr = request.getParameter("dvdType");
		System.out.println("Entering SearchResultFromSolar dopost()");
		System.out.println(dvdName + typeStr);
		if(dvdName == null && typeStr==null)
		{
			getServletContext().getRequestDispatcher("/searchResult.jsp").forward(request, response);
			return;
		}
		if(dvdName != null && dvdName.equalsIgnoreCase(""))
		{
			dvdName =null;
		}
		Integer type = new Integer(typeStr);
		DvdType dType = SolrUtils.getInstance().getTypeyId(type);
		String dvdType=dType.getTypeName();
		System.out.println(dvdType);
		if(dType.getTypeID().equals(DvdType.ALL.typeID))
		{
			dvdType = null;
		}
		try
		{
			Map<String,String> resultMap = SolrUtils.getInstance().doQuery(dvdName, dvdType);
			request.setAttribute("RESULT_MAP", resultMap);
			getServletContext().getRequestDispatcher("/searchResult.jsp").forward(request, response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

}
