package web.badminton.modal.elasticSearch;



import java.util.List;

public class QueryString{
    private String query;
    private String[] fields;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

	
}