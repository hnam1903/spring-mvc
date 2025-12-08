package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UpLoadService;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UpLoadService upLoadService;

    public ProductController(ProductService productService, UpLoadService upLoadService) {
        this.productService = productService;
        this.upLoadService = upLoadService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "admin/product/show";
}

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

   @PostMapping("/admin/product/create")
   public String createProduct(
    Model model,@ModelAttribute("newProduct") @Valid Product pr, BindingResult newProductBindingResult,
     @RequestParam("hoidanitFile") MultipartFile file 
    ) {
        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
            
        }
        String img = this.upLoadService.handleSaveUpLoadFile(file, "product");
        pr.setImage(img);
        this.productService.saveProduct(pr);
        return "redirect:/admin/product";
   }


   @GetMapping("/admin/product/{id}")
   public String detailProduct(Model model,@PathVariable long id) {
    Product theProduct = this.productService.fetchProductById(id).get();
    model.addAttribute("product", theProduct);
    model.addAttribute("id", id);
    return "/admin/product/detail";
   }


   @GetMapping("/admin/product/delete/{id}")
   public String deleteProduct(Model model, @PathVariable long id) {
    model.addAttribute("newProduct", new Product());
    model.addAttribute("id", id);
    return "/admin/product/delete";
   }

   @PostMapping("/admin/product/delete")
   public String handerDeleteProduct(Model model, @ModelAttribute("newProduct") Product pr ) {
    this.productService.deleteProduct(pr.getId());
    return "redirect:/admin/product";
   }

   @GetMapping("/admin/product/update/{id}")
   public String getUpdateProductPage(Model model, @PathVariable long id) {
    Optional<Product> currentProduct = this.productService.fetchProductById(id);
    model.addAttribute("newProduct", currentProduct.get());
    return "/admin/product/update";
   }

   @PostMapping("/admin/product/update")
   public String handleUpdateProduct(@ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newProductBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
                
                if (newProductBindingResult.hasErrors()) {
                    return "/admin/product/update";  
                }

            Product currentProduct = this.productService.fetchProductById(pr.getId().get());
            if (currentProduct != null) {
                if (!file.isEmpty()) {
                    String img = this.upLoadService.handleSaveUpLoadFile(file, "product");
                    currentProduct.setImage(img);
                    
                }

                currentProduct.setName(pr.getName());
                currentProduct.setPrice(pr.getPrice());
                currentProduct.setQuantity(pr.getQuantity());
                currentProduct.setDetailDesc(pr.getDetailDesc());
                currentProduct.setShortDesc(pr.getShortDesc());
                currentProduct.setFactory(pr.getFactory());
                currentProduct.setTarget(pr.getTarget());
                
                this.productService.saveProduct(currentProduct);
            }
            return "redirect:admin/product";
            }
}
