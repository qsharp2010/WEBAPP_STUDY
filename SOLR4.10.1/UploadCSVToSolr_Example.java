// Upload object.
private ContentStreamUpdateRequest up;
private SolrServer solr;
private void uploadExcute()
{
  //Connect solr.
  SolrServer server = new HttpSolrServer(solrHostUrl);
  if (!con.isServerAlive((HttpSolrServer) server)) {
  	throw new SolrServerException("Error Message");
  }
  //Set solr upload directory.
  up = new ContentStreamUpdateRequest("/update/csv");
  try {
    // Add solr upload file.
    // CONTENT_TYPE: "text/csv".
    up.addFile(uploadCsvFile, "text/csv");
    up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
    // Delete all solr old documents.
    solr.deleteByQuery("*:*");
    solr.commit();
    // Solr upload processing
    solr.request(up);
    // Delete the CSV file after upload completed.
    uploadCsvFile.delete();
    } catch (Exception e) {
      uploadCsvFile.delete();
      e.printStackTrace();
    }
}

//Ping solr server.
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
