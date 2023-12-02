package com.example.HTTD.Controller;

import com.example.HTTD.Entity.User;
import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Service.UserService;
import com.example.HTTD.Service.WalletService;
import com.example.HTTD.reponse.ResponseObject;
import com.example.HTTD.reponse.UserResponse;
import com.example.HTTD.reponse.WalletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;
    @PostMapping("/{id}")
    public ResponseEntity<ResponseObject> createWallet(@PathVariable("id") Long userId, @RequestBody Wallet wallet){
        try{
            Optional<User> optionalUser = userService.findById(userId);
            if(optionalUser.isPresent())
            {
                User user = optionalUser.get();
                // Check if the user already has a wallet
                if (user.getWallet() != null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new ResponseObject(0, "Người dùng đã có ví", false, "")
                    );
                }
                wallet.setUsers(user);
                Wallet savedWallet = walletService.createWallet(wallet);
                WalletResponse walletResponse = new WalletResponse();
                walletResponse.setId(savedWallet.getId());
                walletResponse.setName(savedWallet.getName());
                walletResponse.setAmount(savedWallet.getAmount());
                walletResponse.setDate_created(savedWallet.getDate_created());
                walletResponse.setUserId(savedWallet.getUsers().getId());
                walletResponse.setUsername(savedWallet.getUsers().getUsername());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Tạo Ví thành công", true, savedWallet)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Người dùng không hợp lệ", false, "")
                );
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Có lỗi xảy ra khi tạo Ví", false, "")
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getWalletById(@PathVariable("id") Long walletId){
        try{
            Wallet wallet = walletService.getWalletById(walletId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, wallet)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Mã ví không tồn tại",false, "")
            );
        }

    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllWallets(){
        try{
            List<Wallet> listwallet = walletService.getAllWallet();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Thành công",true, listwallet)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Thất bại, có lỗi xảy ra",false, "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateWallet(@PathVariable("id") Long walletId, @RequestBody Wallet wallet){
        try{
            wallet.setId(walletId);
            Wallet updatedWallet = walletService.updateWallet(wallet);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(1, "Cập nhật thành công",true, updatedWallet)
            );
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(0, "Cập nhật thất bại",false, "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteWallet(@PathVariable("id") Long walletId){
        try {
            boolean isDeleted = walletService.deleteWallet(walletId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Xóa thành công", true, "")
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Xóa thất bại, không tìm thấy ví có ID: " + walletId, false, "")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(0, "Lỗi khi xóa ví: " + e.getMessage(), false, "")
            );
        }
    }
}
