package com.schoolmanagerserver.pojos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {
    private int code;
    private String msg;
    public T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //code==1代表成功，code==0代表失败
    public static <E> Result<E> success() {
        return new Result<E>(1, "success", null);
    }

    public static <E> Result<E> success(E data) {
        return new Result<E>(1, "success", data);
    }

    public static <E> Result<E> fail(String msg) {
        return new Result<E>(0, msg, null);
    }
}
