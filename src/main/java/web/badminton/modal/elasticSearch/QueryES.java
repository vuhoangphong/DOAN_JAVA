package web.badminton.modal.elasticSearch;

import java.util.List;

public class QueryES {
    private Query query;
    private boolean track_scores;
    private List<Sort> sort;

    public void setTrackScore(boolean track_scores) {
        this.track_scores = track_scores;
    }

    public boolean getTrackScore() {
        return track_scores;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query=query;
    }

    public List<Sort> getMust() {
        return sort;
    }

    public void setMust( List<Sort> sort) {
        this.sort=sort;
    }


    
}
