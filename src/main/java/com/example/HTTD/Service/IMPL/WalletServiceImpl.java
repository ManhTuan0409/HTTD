package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Repository.WalletRepository;
import com.example.HTTD.Service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    @Override
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(Long walletId) {
        Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
        return optionalWallet.get();
    }

    @Override
    public List<Wallet> getAllWallet() {
        return walletRepository.findAll()   ;
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        Wallet existingwallet = walletRepository.findById(wallet.getWallId()).get();
        existingwallet.setWallName(wallet.getWallName());
        existingwallet.setAmount(wallet.getAmount());
        existingwallet.setDate_created(wallet.getDate_created());
        Wallet updatedWallet = walletRepository.save(existingwallet);
        return updatedWallet;
    }

    @Override
    public void deleteWallet(Long walletId) {
        walletRepository.deleteById(walletId);
    }
}
