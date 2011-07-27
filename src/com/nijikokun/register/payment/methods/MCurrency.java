package com.nijikokun.register.payment.methods;

import com.nijikokun.register.payment.Method;
import me.ashtheking.currency.Currency;
import me.ashtheking.currency.CurrencyList;
import org.bukkit.plugin.Plugin;

/**
 * @author Acrobot
 */
public class MCurrency implements Method {
    private Currency currencyList;

    public Object getPlugin() {
        return this.currencyList;
    }

    public String getName() {
        return "Currency";
    }

    public String getVersion() {
        return "0.09";
    }

    public String format(double amount) {
        return amount + " Currency";
    }

    public boolean hasBanks() {
        return false;
    }

    public boolean hasBank(String bank) {
        return false;
    }

    public boolean hasAccount(String name) {
        return true;
    }

    public boolean hasBankAccount(String bank, String name) {
        return false;
    }

    public MethodAccount getAccount(String name) {
        return new MCurrencyAccount(name);
    }

    public MethodBankAccount getBankAccount(String bank, String name) {
        return null;
    }

    public boolean isCompatible(Plugin plugin) {
        return plugin.getDescription().getName().equalsIgnoreCase(getName()) && plugin instanceof Currency;
    }

    public void setPlugin(Plugin plugin) {
        currencyList = (Currency) plugin;
    }

    public class MCurrencyAccount implements MethodAccount{
        private String name;

        public MCurrencyAccount(String name) {
            this.name = name;
        }

        public double balance() {
            return CurrencyList.getValue((String) CurrencyList.maxCurrency(name)[0], name);
        }

        public boolean set(double amount) {
            CurrencyList.setValue((String) CurrencyList.maxCurrency(name)[0], name, amount);
            return true;
        }

        public boolean add(double amount) {
            return CurrencyList.add(name, amount);
        }

        public boolean subtract(double amount) {
            return CurrencyList.subtract(name, amount);
        }

        public boolean multiply(double amount) {
            return CurrencyList.multiply(name, amount);
        }

        public boolean divide(double amount) {
            return CurrencyList.divide(name, amount);
        }

        public boolean hasEnough(double amount) {
            return CurrencyList.hasEnough(name, amount);
        }

        public boolean hasOver(double amount) {
            return CurrencyList.hasOver(name, amount);
        }

        public boolean hasUnder(double amount) {
            return CurrencyList.hasUnder(name, amount);
        }

        public boolean isNegative() {
            return CurrencyList.isNegative(name);
        }

        public boolean remove() {
            return CurrencyList.remove(name);
        }
    }
}
