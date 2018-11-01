package com.epam.misc.dynamic_programming;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;

import java.util.HashSet;
import java.util.Set;

public class ChangeVariations {

    public static final int AMOUNT = 5;

    enum Coin {
        ONE(1),
        TWO(2),
        FIVE(5);
        private final int amount;

        Coin(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }
    }

    private final Set<Multiset<Coin>> setOfBags = new HashSet<>();

    public void giveChange(Multiset<Coin> bagOfCoins, int amount) {
        if (amount == 0) setOfBags.add(EnumMultiset.create(bagOfCoins));
        else if (amount > 0)
            for (Coin coin : Coin.values()) {
                bagOfCoins.add(coin);
                giveChange(bagOfCoins, amount - coin.getAmount());
                bagOfCoins.remove(coin);
            }
    }

    public static void main(String[] args) {
        ChangeVariations changeVariations = new ChangeVariations();
        EnumMultiset<Coin> bagOfCoins = EnumMultiset.create(Coin.class);
        changeVariations.giveChange(bagOfCoins, AMOUNT);
        System.out.println("setOfBags: " + changeVariations.setOfBags);
    }
}
