package com.example.HTTD.Controller;

import com.example.HTTD.Entity.Category;
import com.example.HTTD.Service.CategoryService;
import com.example.HTTD.reponse.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/auth/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> createCategory(@RequestBody Category category){
        try{
            Category savedCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Tạo danh mục thành công",true, savedCategory)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra khi tạo danh mục",false, "")
            );
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseObject> getCategoryById(@PathVariable("id") Long categoryId){
        try{
            Category category = categoryService.getCategoryById(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, category)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Mã danh mục không tồn tại",false, "")
            );
        }

    }

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAllCategories(){
        try{
            List<Category> listcategory = categoryService.getAllCategory();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, listcategory)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateCategory(@PathVariable("id") Long categoryId, @RequestBody Category category){
        try{
            category.setId(categoryId);
            Category updatedCategory = categoryService.updateCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Cập nhật thành công",true, updatedCategory)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteCategory(@PathVariable("id") Long categoryId){
        try{
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Xóa thành công",true, "")
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Xóa thất bại",false, "")
            );
        }
    }
}
