package web.badminton.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import web.badminton.modal.elasticSearch.Bool;

import web.badminton.modal.elasticSearch.Must;
import web.badminton.modal.elasticSearch.PriceES;
import web.badminton.modal.elasticSearch.ProductES;
import web.badminton.modal.elasticSearch.ProductHit;
import web.badminton.modal.elasticSearch.Query;
import web.badminton.modal.elasticSearch.QueryES;
import web.badminton.modal.elasticSearch.QueryString;
import web.badminton.modal.elasticSearch.Range;
import web.badminton.repository.ProductBrandsRepository;
import web.badminton.repository.ProductPortfolioRepository;
import web.badminton.vo.Product;
import web.badminton.vo.ProductBrands;
import web.badminton.vo.ProductPortfolio;

@Service
public class ElasticSearchProductService {

   
    @Autowired
    ProductPortfolioRepository productPortfolio;

    public List<Product> findProduct(String keyWord,String min , String max) {

        String url = "http://localhost:9200/producttest/_search";
       String requestJson = createQuery(keyWord,min, max);
        return resultProduct(url,requestJson);
    }

   
    




    // create json type object
    private String createQuery( String keyWord,String min,String max ){
      
        QueryES queryES = new QueryES();
        Query query = new Query();
        Bool bool = new Bool();
        Must mustOfQueyString = new Must();
        Must mustOfRange = new Must();
        QueryString queryString = new QueryString();
        Range range = new Range();
        PriceES priceES = new PriceES();
        List<Must> musts = new ArrayList<>();
    
     
        //multi_Match
        queryString.setFields(new String[]{"nameproduct","productdescription","origin"});
        queryString.setQuery("*"+keyWord+"*");
        if(StringUtils.isEmpty(max) & !StringUtils.isEmpty(min)){
             //price
             priceES.setBoots("2.0");
             priceES.setGte(min); // set price min
             priceES.setLte("1000000000"); // set price max
             //range
             range.setPriceES(priceES);
             // filter
             mustOfRange.setRange(range);
              //set rang into range
             musts.add(mustOfRange);
        }
         if(!StringUtils.isEmpty(max) && StringUtils.isEmpty(min)){
             //price
             priceES.setBoots("2.0");
             priceES.setGte("0"); // set price min
             priceES.setLte(max); // set price max
             //range
             range.setPriceES(priceES);
             // filter
             mustOfRange.setRange(range);
              //set rang into range
            musts.add(mustOfRange);
        }
        if(StringUtils.isEmpty(max) ==false && StringUtils.isEmpty(min)== false){
            //price
            priceES.setBoots("2.0");
            priceES.setGte(min); // set price min
            priceES.setLte(max); // set price max
            //range
            range.setPriceES(priceES);
            // filter
            mustOfRange.setRange(range);
             //set rang into range
            musts.add(mustOfRange);
        }   
        mustOfQueyString.setQueryString(queryString);
            //track scores true
        queryES.setTrackScore(true);

        
        //set query string into must
        musts.add(mustOfQueyString);
       

        bool.setMust(musts);
        query.setBool(bool);
        queryES.setQuery(query);
        return new Gson().toJson(queryES);
    }

   
  
    private List<Product>  resultProduct(String url,String requestJson){
        Gson gson = new Gson();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, entity, String.class);
       
        ProductES productES = gson.fromJson(response, ProductES.class);
        List<ProductHit> productHit = productES.getProductHits().getProductHit();
        List<Product> products = new ArrayList<>();
        for (ProductHit source : productHit) {
            if(Integer.parseInt( source.getProductSource().getStatusProduct()) !=1){ // check product hide,ban and sold out
                continue;
            }
             
            Product product = new Product();
            product.setDateInput( java.sql.Date.valueOf( LocalDate.parse(source.getProductSource().getDateInput().substring(0, 10))));
            
            try {
                product.setDayBanned(java.sql.Date.valueOf(LocalDate.parse(source.getProductSource().getDayBanned().substring(0, 10))));
            } catch (Exception e) {
                product.setDayBanned(null); 
            }
            product.setIdBrand(Integer.parseInt(source.getProductSource().getIdBrand()));
            product.setIdProduct(Integer.parseInt(source.getProductSource().getIdProduct()));
            product.setIdShop(Integer.parseInt(source.getProductSource().getIdShop()));
            product.setNameProduct(source.getProductSource().getNameProduct());
            product.setNumberProduct(Integer.parseInt(source.getProductSource().getNumberProduct()));
            product.setOrigin(source.getProductSource().getOrigin());
            product.setPrice(Float.parseFloat(source.getProductSource().getPrice()));
            product.setPriceDiscount(Integer.parseInt(source.getProductSource().getPriceDiscount()));
            product.setPriceProduct(Float.parseFloat(source.getProductSource().getPriceProduct()));
            product.setProductDescription(source.getProductSource().getProductDescription());
            product.setRanking(Integer.parseInt(source.getProductSource().getRanking()));
            product.setReasonBanned(source.getProductSource().getReasonBanned());
            product.setStatusProduct(Integer.parseInt(source.getProductSource().getStatusProduct()));
            products.add(product);
        }
        return products;
    }

}