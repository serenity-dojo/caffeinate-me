package caffeinateme.steps;

import caffeinateme.ProductPrice;
import caffeinateme.UnknownProductException;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {

    List<ProductPrice> catalog = new ArrayList<>();

    @Step
    public void addProductsWithPrices(List<ProductPrice> productPrices) {
        catalog.addAll(productPrices);
    }

    public double priceOf(String productName) {
        return catalog.stream()
                .filter(product -> product.getProduct().equals(productName))
                .findFirst()
                .orElseThrow(UnknownProductException::new)
                .getPrice();
    }
}
