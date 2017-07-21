package com.free.funny.platform.model;

import com.free.funny.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by zhujunlin on 2017/7/4.
 */

public class LiveListModel extends BaseModel {

    public ArrayList<LiveInfo> data;

    public class LiveInfo {
        public String room_id; //房间 ID
        public String room_src; //房间图片，大小 320*180
        public String room_name; //房间名称
        public String owner_uid; //房间所属用户的 UID
        public String online; //在线人数
        public String nickname; //房间所属用户的账号
        public String url; //房间的网址
        public String game_url; //房间的网址
        public String jumpUrl; //房间的网址
    }
}
