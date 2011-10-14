package com.nijikokun.register.payment.methods;

import com.nijikokun.register.payment.Method;

import me.ashtheking.currency.Currency;
import me.ashtheking.currency.CurrencyList;

import org.bukkit.plugin.Plugin;

/**
 * MultiCurrency Method implementation.
 *
 * @author Acrobot
 * @copyright (c) 2011
 * @license AOL license <http://aol.nexua.org>
 */
public class MCUR implements Method {
    private Currency currencyList;

    public Object getPlugin() {
        return this.currencyList;
    }

    public String getName() {
        return "MultiCurrency";
    }

    public String getVersion() {
        return "0.09";
    }
    
    public int fractionalDigits() {
       return -1;
    }

    public String format(double amount) {
        return amount + " Currency";
    }

    public boolean hasBanks() {
        return true;
    }

    public boolean hasBank(String bank) {
        return CurrencyList.getAllCurrencys().contains(bank);
    }

    public boolean hasAccount(String name) {
        return true;
    }

    public boolean hasBankAccount(String bank, String name) {
        return CurrencyList.getAllCurrencys().contains(bank);
    }

    public boolean createAccount(String name) {
        CurrencyList.setValue((String) CurrencyList.maxCurrency(name)[0], name, 0);
        return true;
    }

    public boolean createAccount(String name, double balance) {
        CurrencyList.setValue((String) CurrencyList.maxCurrency(name)[0], name, balance);
        return true;
    }

    public MethodAccount getAccount(String name) {
        return new MCurrencyAccount(name);
    }

    public MethodBankAccount getBankAccount(String bank, String name) {
        return new MCurrencyAccount(name, bank);
    }

    public boolean isCompatible(Plugin plugin) {
        return (plugin.getDescription().getName().equalsIgnoreCase("Currency")
             || plugin.getDescription().getName().equalsIgnoreCase("MultiCurrency"))
             && plugin instanceof Currency;
    }

    public void setPlugin(Plugin plugin) {
        currencyList = (Currency) plugin;
    }

    public class MCurrencyAccount implements MethodAccount, MethodBankAccount{
        private String name;
        private String currency;

        public MCurrencyAccount(String name) {
            this.name = name;
            this.currency = (String) CurrencyList.maxCurrency(name)[0];
        }
        public MCurrencyAccount(String name, String currency) {
            this.name = name;
            this.currency = currency;
        }

        public double balance() {
            return CurrencyList.getValue(currency, name);
        }
        
        public String getBankName() {
           return currency;
        }
        
        public int getBankId() {
           return -1;
        }

        public boolean set(double amount) {
            CurrencyList.setValue(currency, name, amount);
            return true;
        }

        public boolean add(double amount) {
            return CurrencyList.add(name, amount, currency);
        }

        public boolean subtract(double amount) {
            return CurrencyList.subtract(name, amount, currency);
        }

        public boolean multiply(double amount) {
            return CurrencyList.multiply(name, amount, currency);
        }

        public boolean divide(double amount) {
            return CurrencyList.divide(name, amount, currency);
        }

        public boolean hasEnough(double amount) {
            return CurrencyList.hasEnough(name, amount, currency);
        }

        public boolean hasOver(double amount) {
            return CurrencyList.hasOver(name, amount, currency);
        }

        public boolean hasUnder(double amount) {
            return CurrencyList.hasUnder(name, amount, currency);
        }

        public boolean isNegative() {
            return CurrencyList.isNegative(name, currency);
        }

        public boolean remove() {
            return CurrencyList.remove(name, currency);
        }
    }
}
