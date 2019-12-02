package com.study.util;

import com.study.bean.ResultMap;

import java.util.ArrayList;
import java.util.List;

public class Page {

    public static <T> ResultMap<List<T>> paging(int page, int limit, List<T> list) {
        int start = page * limit - limit;
        int len = limit;
        List<T> res = new ArrayList<>();
        if (page * limit > list.size()) {
            for (int i = 0; i < (list.size() - (page - 1) * limit); i++) {
                res.add(list.get(start++));
            }
        } else {
            for (int i = 0; i < len; i++) {
                res.add(list.get(start++));
            }
        }
        new ResultMap<>(0, "", list.size(), res);
        return new ResultMap<>(0, "", list.size(), res);
    }

}
