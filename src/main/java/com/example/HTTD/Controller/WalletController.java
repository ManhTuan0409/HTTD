package com.example.HTTD.Controller;

import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Service.WalletService;
import com.example.HTTD.reponse.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth/wallet")
public class WalletController {
    private WalletService walletService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> createWallet(@RequestBody Wallet wallet){
        Wallet savedWallet = walletService.createWallet(wallet);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(1, "Tạo thành công",true, savedWallet)
        );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable("id") Long walletId){
        Wallet wallet = walletService.getWalletById(walletId);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Wallet>> getAllWallets(){
        List<Wallet> users = walletService.getAllWallet();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable("id") Long walletId,
                                           @RequestBody Wallet wallet){
        wallet.setWallId(walletId);
        Wallet updatedWallet = walletService.updateWallet(wallet);
        return new ResponseEntity<>(updatedWallet, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable("id") Long walletId){
        walletService.deleteWallet(walletId);
        return new ResponseEntity<>("Wallet successfully deleted!", HttpStatus.OK);
    }
}
