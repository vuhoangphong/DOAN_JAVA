package web.badminton.enums;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public enum OrderDetailsEnum {
    ORDER_SUCCESS(0),
    CLOSE_PACKAGE(1),
    BEING_TRANSPORT(2),
    SUCCESSFUL_DELIVERY(3),
    ORDER_CANCELLATION(4);
    private int status;

    OrderDetailsEnum(int status){
        this.status = status;
    }

    public int get(){
        return this.status;
    }
}
