package com.nijikokun.register.payment.methods;


import com.nijikokun.register.payment.Method;

import me.greatman.Craftconomy.Account;
import me.greatman.Craftconomy.AccountHandler;
import me.greatman.Craftconomy.Bank;
import me.greatman.Craftconomy.BankHandler;
import me.greatman.Craftconomy.Craftconomy;
import me.greatman.Craftconomy.CurrencyHandler;
import me.greatman.Craftconomy.utils.Config;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

/**
 * Craftconomy Implementation of Method
 *
 * @author ElgarL
 * @copyright (c) 2012
 * @license AOL license <http://aol.nexua.org>
 */
public class CraftEco implements Method {
    private Craftconomy CraftCon;

    public Craftconomy getPlugin() {
        return this.CraftCon;
    }

    public String getName() {
        return "Craftconomy";
    }

    public String getVersion() {
        return "2.3";
    }
    
    public int fractionalDigits() {
    	return 2;
    }

    public String format(double amount) {
    		return Craftconomy.format(amount, CurrencyHandler.getCurrency(Config.currencyDefault, true));
    }

    public boolean hasBanks() {
        return !BankHandler.listBanks().isEmpty();
    }

    public boolean hasBank(String bank) {
        return (hasBanks()) && BankHandler.exists(bank);
    }

    public boolean hasAccount(String name) {
        return AccountHandler.exists(name);
    }

    public boolean hasBankAccount(String bank, String name) {
        return (hasBank(bank)) && (BankHandler.getBank(bank).getOwner() == name);
    }

    public boolean createAccount(String name) {
        if(hasAccount(name))
            return false;
        
        return (AccountHandler.getAccount(name) != null);
    }

    public boolean createAccount(String name, double balance) {
        if(hasAccount(name))
            return false;
        
        if(AccountHandler.getAccount(name) == null)
            return false;

        getAccount(name).set(balance);

        return true;
    }

    public MethodAccount getAccount(String name) {
        return new CraftAccount(AccountHandler.getAccount(name));
    }

    public MethodBankAccount getBankAccount(String bank, String name) {
        return new CraftBankAccount(BankHandler.getBank(bank));
    }

    public boolean isCompatible(Plugin plugin) {
        return plugin.getDescription().getName().equalsIgnoreCase("Craftconomy") 
            && plugin.getClass().getName().equals("me.greatman.Craftconomy.Craftconomy")
            && plugin instanceof Craftconomy;
    }

    public void setPlugin(Plugin plugin) {
    	CraftCon = (Craftconomy)plugin;
    }

    public class CraftAccount implements MethodAccount {
        private Account account;
        private World defaultWorld;

        public CraftAccount(Account account) {
            this.account = account;
            this.defaultWorld = Bukkit.getServer().getWorlds().get(0);
        }

        public Account geCraftAccount() {
            return account;
        }

        public double balance() {
            return this.account.getBalance(defaultWorld);
        }
        
        public double balance(World world) {
            return this.account.getBalance(world);
        }

        public boolean set(double amount) {
            return set(amount, defaultWorld);
        }
        
        public boolean set(double amount, World world) {
            if(this.account == null) return false;
            this.account.setBalance(amount, world);
            return true;
        }

        public boolean add(double amount) {
        	return add(amount, defaultWorld);
        }
        
        public boolean add(double amount, World world) {
            if(this.account == null) return false;
            this.account.addMoney(amount, world);
            return true;
        }

        public boolean subtract(double amount) {
        	return subtract(amount, defaultWorld);
        }
        
        public boolean subtract(double amount, World world) {
            if(this.account == null) return false;
            this.account.substractMoney(amount, world);
            return true;
        }

        public boolean multiply(double amount) {
        	return multiply(amount, defaultWorld);
        }
        
        public boolean multiply(double amount, World world) {
            if(this.account == null) return false;
            this.account.multiplyMoney(amount, world);
            return true;
        }

        public boolean divide(double amount) {
        	return divide(amount, defaultWorld);
        }
        
        public boolean divide(double amount, World world) {
            if(this.account == null) return false;
            this.account.divideMoney(amount, world);
            return true;
        }

        public boolean hasEnough(double amount) {
        	return hasEnough(amount, defaultWorld);
        }
        
        public boolean hasEnough(double amount, World world) {
            return this.account.hasEnough(amount, world);
        }

        public boolean hasOver(double amount) {
        	return hasOver(amount, defaultWorld);
        }
        
        public boolean hasOver(double amount, World world) {
            return hasEnough(amount+0.01, world);
        }

        public boolean hasUnder(double amount) {
        	return hasUnder(amount, defaultWorld);
        }
        
        public boolean hasUnder(double amount, World world) {
            return !hasEnough(amount, world);
        }

        public boolean isNegative() {
        	return isNegative(defaultWorld);
        }
        
        public boolean isNegative(World world) {
            return (balance(world) < 0.0);
        }

        public boolean remove() {
            if(this.account == null) return false;
            //this.account.remove();
            return false;
        }
    }

    public class CraftBankAccount implements MethodBankAccount {
        private Bank account;
        private World defaultWorld;

        public CraftBankAccount(Bank account) {
            this.account = account;
            this.defaultWorld = Bukkit.getServer().getWorlds().get(0);
        }

        public Bank getCraftBankAccount() {
            return account;
        }

        public String getBankName() {
            return this.account.getName();
        }

        public int getBankId() {
            return this.account.getId();
        }

        public double balance() {
            return balance(defaultWorld);
        }
        
        public double balance(World world) {
            return this.account.getBalance(world);
        }
        
        public boolean set(double amount) {
        	return set(amount, defaultWorld);
        }

        public boolean set(double amount, World world) {
            if(this.account == null) return false;
            this.account.setBalance(amount, CurrencyHandler.getCurrency(Config.currencyDefault, true), world);
            return true;
        }

        public boolean add(double amount) {
        	return add(amount, defaultWorld);
        }
        
        public boolean add(double amount, World world) {
            if(this.account == null) return false;
            this.account.addMoney(amount, CurrencyHandler.getCurrency(Config.currencyDefault, true), world);
            return true;
        }

        public boolean subtract(double amount) {
        	return subtract(amount, defaultWorld);
        }
        
        public boolean subtract(double amount, World world) {
            if(this.account == null) return false;
            this.account.substractMoney(amount, CurrencyHandler.getCurrency(Config.currencyDefault, true), world);
            return true;
        }
        
        public boolean multiply(double amount) {
        	return multiply(amount, defaultWorld);
        }

        public boolean multiply(double amount, World world) {
            if(this.account == null) return false;
            this.account.multiplyMoney(amount, CurrencyHandler.getCurrency(Config.currencyDefault, true), world);
            return true;
        }

        public boolean divide(double amount) {
        	return divide(amount, defaultWorld);
        }
        
        public boolean divide(double amount, World world) {
            if(this.account == null) return false;
            this.account.divideMoney(amount, CurrencyHandler.getCurrency(Config.currencyDefault, true), world);
            return true;
        }

        public boolean hasEnough(double amount) {
        	return hasEnough(amount, defaultWorld);
        }
        
        public boolean hasEnough(double amount, World world) {
            return this.account.hasEnough(amount, CurrencyHandler.getCurrency(Config.currencyDefault, true), world);
        }

        public boolean hasOver(double amount) {
        	return hasOver(amount, defaultWorld);
        }
        
        public boolean hasOver(double amount, World world) {
            return hasEnough(amount+0.01, world);
        }

        public boolean hasUnder(double amount) {
        	return hasUnder(amount, defaultWorld);
        }
        
        public boolean hasUnder(double amount, World world) {
            return !hasEnough(amount, world);
        }

        public boolean isNegative() {
        	return isNegative(defaultWorld);
        }
        
        public boolean isNegative(World world) {
            return (balance(world) < 0.0);
        }

        public boolean remove() {
            if(this.account == null) return false;
            //this.account.remove();
            return false;
        }
    }
}