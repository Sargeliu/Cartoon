package com.example.cartoon;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CartoonResponse implements Serializable {

    @SerializedName("docs")
    private List<Cartoon> cartoons;

    public CartoonResponse(List<Cartoon> cartoons) {
        this.cartoons = cartoons;
    }

    public List<Cartoon> getCartoons() {
        return cartoons;
    }

    @Override
    public String toString() {
        return "CartoonResponse{" +
                "cartoons=" + cartoons +
                '}';
    }
}