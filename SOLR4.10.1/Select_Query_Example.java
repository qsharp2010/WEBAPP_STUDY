private SolrDocumentList solrList;
private void solrSelectExecute()
{
  //connect solr
  SolrServer server = new HttpSolrServer(solrHostUrl);
  if (!con.isServerAlive((HttpSolrServer) server)) {
  	throw new SolrServerException("Error Message");
  }
  //create query
  StringBuffer selectQuery = new StringBuffer("");
  selectQuery.append("search criteria");
  selectQuery.append("search criteria");
  selectQuery.append("search criteria");
  selectQuery.append("search criteria");
  selectQuery.append("search criteria");
  //aaa:ClientUtils.escapeQueryChars(value) AND .... (xxx:value OR SSS:value OR CCC:value)
  SolrQuery query = new SolrQuery(selectQuery.toString());
  query.setRows(Integer.MAX_VALUE);
  QueryResponse response = server.query(query);
  //results
  solrList = response.getResults
}

//ping solr server
public boolean isServerAlive(HttpSolrServer server) {
SolrPingResponse response = null;
  try {
    response = server.ping();
    if (response == null || !response.getResponse().get("status").equals("OK")) {
    return false;
    }
  return true;
   } catch (SolrServerException | IOException e) {
      e.printStackTrace();
      return false;
   }
}
