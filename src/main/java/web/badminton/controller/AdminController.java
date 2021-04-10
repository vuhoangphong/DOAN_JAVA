package web.badminton.controller;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.badminton.enums.OrderDetailsEnum;
import web.badminton.service.AdminService;
import web.badminton.service.PortfolioService;
import web.badminton.vo.Product;
import web.badminton.vo.ProductBrands;
import web.badminton.vo.ProductPortfolio;
import web.badminton.vo.ProductReview;
import web.badminton.vo.User;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    PortfolioService portfolioService;

    @RequestMapping("admin")
    public ModelAndView admin() {
        ModelAndView view = new ModelAndView("admin/admin");
        // get data order cancel
        view.addObject("dataCancel", adminService.statisticalAllOrder(OrderDetailsEnum.ORDER_CANCELLATION.get(),
                Integer.parseInt(Year.now().toString()), 1));
        // get sum order cancel
        view.addObject("sumCancel", adminService.statisticalAllOrder(OrderDetailsEnum.ORDER_CANCELLATION.get(),
                Integer.parseInt(Year.now().toString()), 0));
        // get data order success
        view.addObject("dataSuccess", adminService.statisticalAllOrder(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(),
                Integer.parseInt(Year.now().toString()), 1));
        // get sum order success
        view.addObject("sumSuccess", adminService.statisticalAllOrder(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(),
                Integer.parseInt(Year.now().toString()), 0));
        // get data order
        view.addObject("dataOrder", adminService.statisticalAllOrderYear(Integer.parseInt(Year.now().toString()), 1));
        // get sum order
        view.addObject("sumOrder", adminService.statisticalAllOrderYear(Integer.parseInt(Year.now().toString()), 0));
        return view;
    }

    @RequestMapping("/adminyear")
    public ModelAndView admin(@RequestParam String Year) {
        ModelAndView view = new ModelAndView("admin/admin");
        // get data order cancel
        view.addObject("dataCancel", adminService.statisticalAllOrder(OrderDetailsEnum.ORDER_CANCELLATION.get(),
                Integer.parseInt(Year), 1));
        // get sum order cancel
        view.addObject("sumCancel", adminService.statisticalAllOrder(OrderDetailsEnum.ORDER_CANCELLATION.get(),
                Integer.parseInt(Year), 0));
        // get data order success
        view.addObject("dataSuccess", adminService.statisticalAllOrder(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(),
                Integer.parseInt(Year), 1));
        // get sum order success
        view.addObject("sumSuccess", adminService.statisticalAllOrder(OrderDetailsEnum.SUCCESSFUL_DELIVERY.get(),
                Integer.parseInt(Year), 0));
        // get data order
        view.addObject("dataOrder", adminService.statisticalAllOrderYear(Integer.parseInt(Year), 1));
        // get sum order
        view.addObject("sumOrder", adminService.statisticalAllOrderYear(Integer.parseInt(Year), 0));
        return view;
    }

    //
    @RequestMapping("addCategories")
    public ModelAndView addCategories() {
        ModelAndView view = new ModelAndView("admin/addCategories");
        view.addObject("categories", adminService.getNamePortfolio());
        view.addObject("brand", adminService.getBrands());
        return view;
    }

    @RequestMapping(value = "/addNewCategories", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductPortfolio> addNewCategories(@RequestParam String name) {
        adminService.insertPortfolio(name);
        return adminService.getNamePortfolio();
    }

    @RequestMapping(value = "/deleteCategories", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductPortfolio> deleteCategories(@RequestParam String id) {
        adminService.deletePortfolio(id);
        return adminService.getNamePortfolio();
    }

    @RequestMapping(value = "/addBrand", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductBrands> addBrand(@RequestParam String name, @RequestParam String id) {
        adminService.insertBrand(name, id, "1");
        return adminService.getTypeStatus1(Integer.parseInt(id));
    }

    // comment management
    @RequestMapping(value = "/commentManagement")
    public ModelAndView commentManagement() {
        ModelAndView view = new ModelAndView("admin/commentManagement");
        view.addObject("listRank", adminService.findRank("0")); // find rank = 0 => all rank
        return view;
    }

    @RequestMapping(value = "/getRank", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductReview> getRank(@RequestParam String rank) {
        return adminService.findRank(rank);
    }

    // account management
    @RequestMapping(value = "/accountManagement")
    public ModelAndView accountManagement() {
        ModelAndView view = new ModelAndView("admin/accountManagement");
       view.addObject("listUser", adminService.getAllUser("1"));
        return view;
    }
    
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    public List<User> getUser(@RequestParam String status) {
        return  adminService.getAllUser(status);
    }

    @RequestMapping(value="/receiveRequest", method=RequestMethod.POST)
    @ResponseBody
    public int receiveRequest(@RequestParam  String id,@RequestParam  String status) {
        return adminService.updateStatusUser(id, status);
    }



    
      @RequestMapping(value = "/shopManagement")
      public ModelAndView shopManagement() {
          ModelAndView view = new ModelAndView("admin/shopManagement");
         view.addObject("listUser", adminService.findAllShop(0));
          return view;
      }

      
      @RequestMapping(value = "/productManagement")
      public ModelAndView productManagement() {
          ModelAndView view = new ModelAndView("admin/productManagement");
          view.addObject("listProductPortfolio",portfolioService.getNamePortfolio());
          view.addObject("listProduct", adminService.Search("", "", "", "1"));
          return view;
      }

      @RequestMapping(value="/admin-Search", method=RequestMethod.POST)
      @ResponseBody
      public List<Product> adminSearch(@RequestParam(required = false)  String brand,@RequestParam(required = false)  String portfolio,@RequestParam(required = false)  String keySearch,@RequestParam(required = false)  String status) {
          if(keySearch == null)
            keySearch = "";
          return adminService.AdminSearchProduct(brand, portfolio, keySearch, status);
      }
      

}
