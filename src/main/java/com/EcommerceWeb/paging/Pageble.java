package com.EcommerceWeb.paging;

import com.EcommerceWeb.sort.Sorter;

public interface Pageble {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
