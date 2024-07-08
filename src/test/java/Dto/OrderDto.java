package Dto;

public class OrderDto {
    String status;
    int courierId;
    String customerName;
    String customerPhone;
    String comment;

    public OrderDto(String customerName, String customerPhone, String comment) {
        this.status = "OPEN";
        this.courierId = 0;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.comment = comment;
    }
}

