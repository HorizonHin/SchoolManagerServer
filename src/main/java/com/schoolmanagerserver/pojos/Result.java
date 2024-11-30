package com.schoolmanagerserver.pojos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> {
    private boolean success;
    private String msg;
    public T data;

    public static <E> Result<E> success() {
        return new Result<E>(true, "success", null);
    }

    public static <E> Result<E> success(String msg) {
        return new Result<E>(true, msg, null);
    }

    public static <E> Result<E> success(E data) {
        return new Result<>(true, "success", data);
    }

    public static <E> Result<E> success(String msg, E data) {
        return new Result<E>(true, msg, data);
    }

    public static <E> Result<E> fail(String msg) {
        return new Result<>(false, msg, null);
    }
}
