package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    
    public ProductService(ProductRepository productRepository, CartRepository cartRepository,CartDetailRepository cartDetailRepository,UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }
    
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product saveProduct(Product pr) {
        return this.productRepository.save(pr);
    }

    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
        
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
   
    public void handleAddProductToCart(String email, long productId) {
        
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            //check user da co Cart chua ? neu chua -> Tao moi
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                // tao moi cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(1);
                cart = this.cartRepository.save(otherCart);
            }
            //luu cart_detail
            //tim product by id
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setProduct(realProduct);
                cartDetail.setPrice(realProduct.getPrice());
                cartDetail.setQuantity(1);

            this.cartDetailRepository.save(cartDetail);
            }
            
        }
        
    }

}
