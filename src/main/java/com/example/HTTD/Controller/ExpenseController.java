package com.example.HTTD.Controller;

import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Service.ExpenseService;
import com.example.HTTD.reponse.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> createExpense(@RequestBody Expense expense){
        try{
            Expense savedExpense = expenseService.createExpense(expense);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Tạo mục tiêu thành công",true, savedExpense)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra khi tạo mục tiêu",false, "")
            );
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseObject> getExpenseById(@PathVariable("id") Long ExpenseId){
        try{
            Expense expense = expenseService.getExpenseById(ExpenseId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, expense)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Mã mục tiêu không tồn tại",false, "")
            );
        }

    }

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAllExpenses(){
        try{
            List<Expense> listExpense = expenseService.getAllExpense();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, listExpense)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateExpense(@PathVariable("id") Long expenseId, @RequestBody Expense expense){
        try{
            expense.setId(expenseId);
            Expense updatedExpense = expenseService.updateExpense(expense);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Cập nhật thành công",true, updatedExpense)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteExpense(@PathVariable("id") Long ExpenseId){
        try {
            boolean isDeleted = expenseService.deleteExpense(ExpenseId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Xóa thành công", true, "")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Xóa thất bại, không tìm thấy chi phí có ID: " + ExpenseId, false, "")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(0, "Lỗi khi xóa chi phí: " + e.getMessage(), false, "")
            );
        }
    }
}
