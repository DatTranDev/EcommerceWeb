package com.EcommerceWeb.utils;

import com.EcommerceWeb.model.ProductConfig;
import com.EcommerceWeb.model.Variation;
import com.EcommerceWeb.model.VariationOption;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MotSoPhuongThucBoTro {
    public static String formatMoney(double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(amount);
    }



    public static String formatVariation(List<ProductConfig> list) {
        if (list == null || list.isEmpty() || list.size() > 2) {
            return "unknown";
        }

        String size = "Không size";
        String color = "Không màu";

        for (ProductConfig productConfig : list) {
            if (productConfig == null) {
                return "unknown";
            }

            VariationOption variationOption = productConfig.getVariationOption();
            if (variationOption == null) {
                return "unknown";
            }

            Variation variation = variationOption.getVariation();
            if (variation == null) {
                return "unknown";
            }

            if (variation.getID() == 1) {
                size = variation.getDisplayName() + " " + variationOption.getValue();
            } else if (variation.getID() == 2) {
                color = variation.getDisplayName() + " " + variationOption.getValue();
            }
        }

        return size + " - " + color;
    }


}
