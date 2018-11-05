package com.xixi.comm.error;

import java.util.ArrayList;
import java.util.List;

public enum Result {
    Succeed(0, "Succeed"),
    Fail(1, "Fail");

    private int error;
    private String desc;
    private static List<Result> allErrors;
    static {
        allErrors = new ArrayList<Result>();
        allErrors.add(Succeed);
        allErrors.add(Fail);
    }
    private Result(int err, String desc) {
        this.error = error;
        this.desc = desc;
    }
}
