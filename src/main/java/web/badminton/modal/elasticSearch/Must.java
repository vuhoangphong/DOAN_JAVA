package web.badminton.modal.elasticSearch;

public class Must {
    private QueryString query_string;
    private Range range;

    public QueryString getQueryString() {
        return query_string;
    }

    public void setQueryString( QueryString query_string) {
        this.query_string=query_string;
    }

    public Range getRange() {
        return range;
    }

    public void setRange( Range range) {
        this.range=range;
    }
}
