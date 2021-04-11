package web.badminton.modal.elasticSearch;

import java.util.List;

public class ProductHits {
    private     List<ProductHit> hits;

    public List<ProductHit> getProductHit() {
        return hits;
    }

    public void setProductHit(List<ProductHit> hits) {
        this.hits = hits;
    }
}
