package hello.core.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

    private Long memberId;

    private String itemName;

    private int itemPrice;

    private int discountPrice;


    //비즈니스로직
    public int calculatePrice(){
        return itemPrice - discountPrice;
    }

}
