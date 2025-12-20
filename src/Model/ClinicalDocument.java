package Model;

public class ClinicalDocument {
	private String documentID;
	private String title;
	private String content;
	private String dateCreated;
	
	
	//constructor
	public ClinicalDocument(String docID, String docTitle, String docContent, String docDate) {
        documentID = docID;
        title = docTitle;
        content = docContent;
        dateCreated = docDate;
    }
	
	
	//getters
	public String getDocumentID() {
        return documentID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDateCreated() {
        return dateCreated;
    }
    
    
    //methods 
    public String getSummary() {
        return "Document [" + documentID + "]: " + title + " (" + dateCreated + ")";
    }
    
    public void viewDocument() {
        System.out.println("Viewing document " + documentID + ": " + content);
    }
    
    @Override public String toString() {
    	return getDocumentID() + " - " + getTitle() + " (" + getDateCreated() + ")";
    	}
}
