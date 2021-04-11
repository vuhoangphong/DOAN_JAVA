package web.badminton.modal.elasticSearch;

public class PriceES {
    private String gte;
    private String lte;
    private String boost;

    public String getGte() {
        return gte;
    }

    public void setGte(String gte) {
        this.gte = gte;
    }

    public String getLte() {
        return lte;
    }
    
    public void setLte(String lte) {
        this.lte = lte;
    }

    public String getBoots() {
        return boost;
    }

    public void setBoots(String boost) {
        this.boost = boost;
    }
}
