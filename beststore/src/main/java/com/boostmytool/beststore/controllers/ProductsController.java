package com.boostmytool.beststore.controllers;

import com.boostmytool.beststore.models.Product;
import com.boostmytool.beststore.models.ProductDto;
import com.boostmytool.beststore.services.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.PathMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRepository repo;
    @Autowired
    private PathMatcher pathMatcher;

    @GetMapping({"","/"})
    public String showProducts(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto,
                                BindingResult result, Model model) {
        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto" , "imageFile", "This image file is required"));
        }
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
        try{
            String upLoadDir = "public/images/";
            Path upLoadPath = Paths.get(upLoadDir);
            if (!Files.exists(upLoadPath)) {
                Files.createDirectories(upLoadPath);
            }
            try(InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(upLoadDir + storageFileName),StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (Exception ex){
            System.out.println("Error while uploading image" + ex.getMessage());
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);

        repo.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        try{
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            model.addAttribute("productDto", productDto);
        }catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }

        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(Model model, @RequestParam int id,
                                @Valid @ModelAttribute ProductDto productDto,
                                BindingResult result) {
        try{
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if  (result.hasErrors()) {
                return "products/EditProduct";
            }

            if(!productDto.getImageFile().isEmpty()) {
                String upLoadDir = "public/images/";
                Path oldImagePath = Paths.get(upLoadDir + product.getImageFileName());

                try{
                    Files.delete(oldImagePath);
                }catch (Exception ex){
                    System.out.println("Error while uploading image" + ex.getMessage());
                }

                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try(InputStream inputStream = image.getInputStream();){
                    Files.copy(inputStream, Paths.get(upLoadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }

                product.setImageFileName(storageFileName);
            }

            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            repo.save(product);
        }catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id) {
        try{
            Product product = repo.findById(id).get();

            Path imagePath = Paths.get("public/images" + product.getImageFileName());

            try{
                Files.delete(imagePath);
            }catch (Exception ex){
                System.out.println("Error while deleting image" + ex.getMessage());
            }

            repo.delete(product);
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());

        }
        return "redirect:/products";
    }
}