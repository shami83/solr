package solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

public class SolrUtils {
	public static SolrUtils instance=null;
	private SolrServer server=null;
	private final String SERVER_URL ="http://localhost:8983/solr";
	private SolrUtils()
	{
		
	}
	
	public static  SolrUtils getInstance()
	{
		if(instance == null)
		{
			instance = new SolrUtils();
		}
		return instance;
	}
	
	public SolrServer  getServer() throws Exception
	{
		if(server == null)
		{
			server = new CommonsHttpSolrServer(SERVER_URL);
		}
		return server;
	}
	
	public void postToSolr(SolrInputDocument doc ) throws Exception
	{
		 System.out.println(doc + "ready to Post");
		 Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		 docs.add(doc);
		 UpdateRequest req = new UpdateRequest();
		 req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
		 req.add( docs );
		 UpdateResponse rsp = req.process(getServer());
		 System.out.println(doc + "Posted successfully");
	}
	public Map<String,String> doQuery(String name,String type) throws Exception
	{
		SolrServer server = getServer();
		String query="";
		if(name != null && type !=null)
		{
			query=query+"(name:"+name+") AND (type:"+type+")";
		}
		else if(name != null)
		{
			query=query+"(name:"+name+")" ;
		}
		else if(type != null)
		{
			query=query+"(type:"+type+")" ;
		}
		else
		{
			for(DvdType typeDvd : DvdType.values())
			{
				query=query+"(type:"+typeDvd.getTypeName()+") or" ;
			}
		}
		System.out.print("QUERY==>" + query);
	    ModifiableSolrParams solrParams = new ModifiableSolrParams();
	    solrParams.set("q", query);
	    QueryResponse response = server.query(solrParams, SolrRequest.METHOD.POST);
	    Map<String,String> map = new HashMap<String,String>();
	    Iterator<SolrDocument> iter = response.getResults().iterator();

	    while (iter.hasNext()) {
	      SolrDocument resultDoc = iter.next();

	      String dvdName = (String) resultDoc.getFieldValue("name");
	      String dvdType = (String) resultDoc.getFieldValue("type"); //id is the uniqueKey field
	      map.put(dvdName, dvdType);
	     
	    }
	    System.out.print("RESULT==>" + map);
        return map;
	}
	
	public DvdType getTypeyId(Integer id)
	{
		if(id.equals(DvdType.SAD.typeID))
		{
			return DvdType.SAD;
		}
		else if(id.equals(DvdType.COMEDY.typeID))
		{
			return DvdType.COMEDY;
		}
		else if(id.equals(DvdType.ACTION.typeID))
		{
			return DvdType.ACTION;
		}
		else if(id.equals(DvdType.ROMANTIC.typeID))
		{
			return DvdType.ROMANTIC;
		}
		else if(id.equals(DvdType.SUSPENCE.typeID))
		{
			return DvdType.SUSPENCE;
		}
		else if(id.equals(DvdType.HAUNTED.typeID))
		{
			return DvdType.HAUNTED;
		}
		else
		{
			return DvdType.ALL;
		}
	}
	

}
