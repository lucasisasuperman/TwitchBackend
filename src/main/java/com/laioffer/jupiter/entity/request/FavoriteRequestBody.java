package com.laioffer.jupiter.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laioffer.jupiter.entity.db.Item;

//浏览器发过来的数据从json type convert成object type，第一步先获得item type，不可以只发item，要发一个key
public class FavoriteRequestBody { //从用户favorite的数据中存到表格中id，在对应的list中返回
    @JsonProperty("favorite")
    private Item favoriteItem;

    public Item getFavoriteItem() {
        return favoriteItem;
    }

}
