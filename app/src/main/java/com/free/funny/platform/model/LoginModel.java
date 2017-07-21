package com.free.funny.platform.model;

import com.free.funny.base.BaseModel;

import java.util.List;

/**
 * Created by zhujunlin on 2017/7/17.
 */

public class LoginModel extends BaseModel {

    public int pageNum;
    public int pageSize;
    public int pageCount;
    public int total;
    public DataBean data;

    public class DataBean {
        /**
         * userId : 898
         * empId : 310bf591e385479c90f0da16e875a04c
         * empCode : c8122156
         * loginName : youse
         * adminFlag : 1
         * nickName : 测试昵称
         * imgurl : head/c8122156/41cb110fec16423a8c2539bff136a2a7
         * empName : 有色管理员都会觉得
         * birthday : null
         * email : null
         * mobile : 1234
         * needChgPwd : null
         * codedPassword : null
         * expireDate : null
         * lastPwdDate : null
         * isPersonal : 0
         * tokenExpireDate : 2592000000
         * memberId : 078dad2a88914d6595bd17f2fb8e48d4
         * memberCode : c8122156
         * erpCode : null
         * status : 60
         * logoUrl : logo/c8122156/4a853f1b9c584b0fb43e61e7e86dcf9f
         * memberName : 中国有色金属国际贸易有限公司
         * memberNameShort : 有色金属工业总公司在
         * memberType : 10
         * mainUserId : 310bf591e385479c90f0da16e875a04c
         * country : null
         * regProvince : 上海
         * regCity : 上海市
         * regArea : 黄浦区
         * regAddress : 北京朝阳区朝阳路200号
         * rmsMenus : [{"menuId":"1716203db39f43589b2e4bb6bd30f693","menuNo":"GP","applicationCode":"","menuParentNo":"","menuOrder":51,"menuName":"挂牌管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f694","menuNo":"GP001","applicationCode":"","menuParentNo":"GP","menuOrder":1,"menuName":"销售挂牌列表","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/listing/template/list-transaction","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f695","menuNo":"GP003","applicationCode":"","menuParentNo":"GP","menuOrder":2,"menuName":"采购挂牌列表","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/procurement/template/procurement-transaction","menuIcon":"","isVisible":"0","isGrant":null,"mores":null},{"menuId":"ecddfb8892a44a70ae5d7d1756f824da","menuNo":"YSGP01","applicationCode":"","menuParentNo":"GP","menuOrder":3,"menuName":"预售挂牌列表","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/futures/template/list-transaction\r\n","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"ecddfb8892a44a70ae5d7d0756f824da","menuNo":"YSGP02","applicationCode":"","menuParentNo":"GP","menuOrder":4,"menuName":"配送区域维护","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/futures/template/delivery-group","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f733","menuNo":"XS","applicationCode":"","menuParentNo":"","menuOrder":52,"menuName":"挂牌交易","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"0","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f734","menuNo":"XS001","applicationCode":"","menuParentNo":"XS","menuOrder":1,"menuName":"挂牌交易","menuType":"20","menuLevel":2,"menuUrl":"0","menuIcon":"","isVisible":"0","isGrant":null,"mores":null}]},{"menuId":"e02aae6e7eb9481c89a5b42138ee2b95","menuNo":"QT","applicationCode":"","menuParentNo":"","menuOrder":56,"menuName":"快转管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"687514fb2785461cbd1e4815b12fa3fb","menuNo":"QT01","applicationCode":"","menuParentNo":"QT","menuOrder":560010,"menuName":"我的挂单","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/turn/template/my-guadan-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"02ba4cfcee2b40728ddc64ccaeb1c2e2","menuNo":"QT02","applicationCode":"","menuParentNo":"QT","menuOrder":560020,"menuName":"我的成交","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/turn/template/deal-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"9ffa7fdee06846098df89a0c8c210f96","menuNo":"QT03","applicationCode":"","menuParentNo":"QT","menuOrder":560030,"menuName":"我的库存","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/turn/template/my-stock","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f727","menuNo":"JJ","applicationCode":"","menuParentNo":"","menuOrder":61,"menuName":"竞价管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f728","menuNo":"JJ001","applicationCode":"","menuParentNo":"JJ","menuOrder":2,"menuName":"竞买管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/bidding/template/manage-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f729","menuNo":"JJ002","applicationCode":"","menuParentNo":"JJ","menuOrder":3,"menuName":"竞买跟踪","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/bidding/template/manage-list-guanli","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f737","menuNo":"JM","applicationCode":"","menuParentNo":"","menuOrder":62,"menuName":"竞价交易","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"0","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f738","menuNo":"JM001","applicationCode":"","menuParentNo":"JM","menuOrder":1,"menuName":"竞买交易","menuType":"20","menuLevel":2,"menuUrl":"0","menuIcon":"","isVisible":"0","isGrant":null,"mores":null}]},{"menuId":"a23482c0b2b2462ca79c3cac0559c0a3","menuNo":"WDPT","applicationCode":"","menuParentNo":"","menuOrder":66,"menuName":"我的拼团","menuType":"10","menuLevel":1,"menuUrl":"#","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"4eae7b560b754eceb566e1601c2d3efc","menuNo":"SELLER_PT","applicationCode":"","menuParentNo":"WDPT","menuOrder":660010,"menuName":"我发布的拼团","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/group/template/list-transaction","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f700","menuNo":"DD","applicationCode":"","menuParentNo":"","menuOrder":81,"menuName":"订单管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f701","menuNo":"DD001","applicationCode":"","menuParentNo":"DD","menuOrder":1,"menuName":"买家订单","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/buyer-order-centre","menuIcon":"","isVisible":"0","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f702","menuNo":"DD002","applicationCode":"","menuParentNo":"DD","menuOrder":2,"menuName":"卖家订单","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/seller-order-centre","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f703","menuNo":"DD003","applicationCode":"","menuParentNo":"DD","menuOrder":3,"menuName":"买家合同查询","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/buyer-contract-man","menuIcon":"","isVisible":"0","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f704","menuNo":"DD004","applicationCode":"","menuParentNo":"DD","menuOrder":4,"menuName":"卖家合同查询","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/seller-contract-man","menuIcon":"","isVisible":"0","isGrant":null,"mores":null},{"menuId":"5e8189dc9d7d4630ada960cee3bd940d","menuNo":"DD005","applicationCode":"","menuParentNo":"DD","menuOrder":5,"menuName":"订单对账查询","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/order-tracking-seller","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"6c9307624a3c417791821eb38fa3e3d1","menuNo":"DD006","applicationCode":"","menuParentNo":"DD","menuOrder":7,"menuName":"购销余款释放-买方","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/transfer-payment-buyer","menuIcon":"","isVisible":"0","isGrant":null,"mores":null},{"menuId":"6cb047197351423b8e60b463905abe2e","menuNo":"DD007","applicationCode":"","menuParentNo":"DD","menuOrder":8,"menuName":"订单对账查询-买方","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/order-tracking-buyer","menuIcon":"","isVisible":"0","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f707","menuNo":"WL","applicationCode":"","menuParentNo":"","menuOrder":82,"menuName":"提单管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f708","menuNo":"WL001","applicationCode":"","menuParentNo":"WL","menuOrder":1,"menuName":"买家提单","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/buyer-bill-man","menuIcon":"","isVisible":"0","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f709","menuNo":"WL002","applicationCode":"","menuParentNo":"WL","menuOrder":2,"menuName":"卖家提单","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/seller-bill-man","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f668","menuNo":"ZX","applicationCode":"","menuParentNo":"","menuOrder":123,"menuName":"资讯管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f669","menuNo":"ZX010","applicationCode":"","menuParentNo":"ZX","menuOrder":1,"menuName":"资讯发布","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/sociality/template/info-release","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f670","menuNo":"ZX011","applicationCode":"","menuParentNo":"ZX","menuOrder":2,"menuName":"我的资讯列表","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/sociality/template/myInformation-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"4079317423a24cca9fe560e6024419fe","menuNo":"DP","applicationCode":"","menuParentNo":"","menuOrder":131,"menuName":"店铺管理","menuType":"10","menuLevel":1,"menuUrl":"1","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"4b2e15052d4645929e41bbb99741a12b","menuNo":"DP001","applicationCode":"","menuParentNo":"DP","menuOrder":1310010,"menuName":"我的店铺","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/store-my-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"49763f5ee84e464aad6c2787c7aeee11","menuNo":"DP002","applicationCode":"","menuParentNo":"DP","menuOrder":1310020,"menuName":"我购买的模板","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/template-my-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"57340a56784344f8b6d1fbdf922a7afe","menuNo":"DP003","applicationCode":"","menuParentNo":"DP","menuOrder":1310030,"menuName":"模板购买记录","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/template-buy-log-my-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f718","menuNo":"SP","applicationCode":"","menuParentNo":"","menuOrder":171,"menuName":"商品管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f719","menuNo":"SP004","applicationCode":"","menuParentNo":"SP","menuOrder":1,"menuName":"商品管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/platform/template/product-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f725","menuNo":"SQ","applicationCode":"","menuParentNo":"","menuOrder":201,"menuName":"授权管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f726","menuNo":"SQ004","applicationCode":"","menuParentNo":"SQ","menuOrder":1,"menuName":"岗位管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/basic/template/user-role-manage-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f678","menuNo":"QY","applicationCode":"","menuParentNo":"","menuOrder":210,"menuName":"企业管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f680","menuNo":"QY002","applicationCode":"","menuParentNo":"QY","menuOrder":2100010,"menuName":"企业信息","menuType":"20","menuLevel":2,"menuUrl":"backstage.html?1#!/Backstage/workbench/template/enterprise-information-change","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f679","menuNo":"QY001","applicationCode":"","menuParentNo":"QY","menuOrder":2100020,"menuName":"员工管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/staff-manage","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f684","menuNo":"QY006","applicationCode":"","menuParentNo":"QY","menuOrder":2100030,"menuName":"企业认证","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/enterprise-information-change/!#qyzj","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f685","menuNo":"QY007","applicationCode":"","menuParentNo":"QY","menuOrder":2100040,"menuName":"我邀请的客户","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/invited-customer-mem","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f683","menuNo":"QY005","applicationCode":"","menuParentNo":"QY","menuOrder":2100050,"menuName":"交货地管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/delivery-address-manage-enterprise","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"14b011785e454c879fff0622f8e3d69d","menuNo":"QY008","applicationCode":"","menuParentNo":"QY","menuOrder":2100060,"menuName":"黑名单管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/blacklist-management","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"c8aca174130941a39dfc9c8b1131d03d","menuNo":"KHGL","applicationCode":"","menuParentNo":"","menuOrder":215,"menuName":"客户关系","menuType":"10","menuLevel":1,"menuUrl":"00","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"63f12a3cb4054816a6d608af52d7ffe4","menuNo":"KHGL001","applicationCode":"","menuParentNo":"KHGL","menuOrder":2150010,"menuName":"客户管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/customer-khgl","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"4a9f558ebc0a4849bf9d53b38207e487","menuNo":"KHGL002","applicationCode":"","menuParentNo":"KHGL","menuOrder":2150020,"menuName":"客户申请管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/customer-sqgl","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"462c7c3bc0db4212bf754b65e94f592f","menuNo":"KHGL003","applicationCode":"","menuParentNo":"KHGL","menuOrder":2150030,"menuName":"客户组管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/my-business","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1528f644edf14db3b1a1958f7efee72e","menuNo":"KHGL004","applicationCode":"","menuParentNo":"KHGL","menuOrder":2150040,"menuName":"客户等级管理","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/customer-djxg","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]},{"menuId":"1716203db39f43589b2e4bb6bd30f671","menuNo":"ZH","applicationCode":"","menuParentNo":"","menuOrder":220,"menuName":"账户管理","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"1","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f673","menuNo":"ZH002","applicationCode":"","menuParentNo":"ZH","menuOrder":1,"menuName":"个人资料","menuType":"20","menuLevel":2,"menuUrl":"backstage.html?1#!/Backstage/workbench/template/personal-data","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f675","menuNo":"ZH004","applicationCode":"","menuParentNo":"ZH","menuOrder":2,"menuName":"偏好设置","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/attention-setting","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f672","menuNo":"ZH001","applicationCode":"","menuParentNo":"ZH","menuOrder":4,"menuName":"安全设置","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/security-setting","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f677","menuNo":"ZH006","applicationCode":"","menuParentNo":"ZH","menuOrder":6,"menuName":"我邀请的客户","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/workbench/template/invited-customer-emp","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]}]
         * rmsBuyerMenus : [{"menuId":"1716203db39f43589b2e4bb6bd30f735","menuNo":"YX","applicationCode":"","menuParentNo":"","menuOrder":71,"menuName":"撮合交易","menuType":"10","menuLevel":1,"menuUrl":"0","menuIcon":"","isVisible":"0","isGrant":null,"mores":[{"menuId":"1716203db39f43589b2e4bb6bd30f736","menuNo":"YX001","applicationCode":"","menuParentNo":"YX","menuOrder":1,"menuName":"我的发布","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/intention/template/my-publish-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"e7469d42918242b5a898dffa297a84e5","menuNo":"YX002","applicationCode":null,"menuParentNo":"YX","menuOrder":2,"menuName":"我的响应","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/intention/template/my-response-list","menuIcon":null,"isVisible":"1","isGrant":null,"mores":null},{"menuId":"d281133e1ce04c04a79a0e20d00f792e","menuNo":"YX003","applicationCode":null,"menuParentNo":"YX","menuOrder":3,"menuName":"我的交易单","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/intention/template/trade-card-list","menuIcon":null,"isVisible":"1","isGrant":null,"mores":null}]}]
         * appCode : youse
         * roleTags : [{"tagType":"R20","typeName":"默认交易企业","keyWord":"8d9t0f8dg9darfd7fd6df5fdsd","isPersonal":"0"},{"tagType":"R29","typeName":"默认企业游客","keyWord":"2t3y4u5i663i55h","isPersonal":"0"}]
         * preferenceTags : [{"prefName":"橡胶","prefType":"10","bizId":"05ebc8b797904205b453850f9320f648","bizName":"橡胶","parentBizName":"石油化工","keyword":""},{"prefName":"石油化工","prefType":"10","bizId":"05ebc8b797904205b453850f9320f452","bizName":"石油化工","parentBizName":"石油化工","keyword":""},{"prefName":"石油化工","prefType":"10","bizId":"05ebc8b797904205b453850f9320f452","bizName":"石油化工","parentBizName":"石油化工","keyword":""}]
         */

        public String userId;
        public String empId;
        public String empCode;
        public String loginName;
        public String adminFlag;
        public String nickName;
        public String imgurl;
        public String empName;
        public Object birthday;
        public Object email;
        public String mobile;
        public Object needChgPwd;
        public Object codedPassword;
        public Object expireDate;
        public Object lastPwdDate;
        public String isPersonal;
        public long tokenExpireDate;
        public String memberId;
        public String memberCode;
        public Object erpCode;
        public String status;
        public String logoUrl;
        public String memberName;
        public String memberNameShort;
        public String memberType;
        public String mainUserId;
        public Object country;
        public String regProvince;
        public String regCity;
        public String regArea;
        public String regAddress;
        public String appCode;
        public List<RmsMenusBean> rmsMenus;
        public List<RmsBuyerMenusBean> rmsBuyerMenus;
        public List<RoleTagsBean> roleTags;
        public List<PreferenceTagsBean> preferenceTags;
    }

    public class RmsMenusBean {
        /**
         * menuId : 1716203db39f43589b2e4bb6bd30f693
         * menuNo : GP
         * applicationCode :
         * menuParentNo :
         * menuOrder : 51
         * menuName : 挂牌管理
         * menuType : 10
         * menuLevel : 1
         * menuUrl : 0
         * menuIcon :
         * isVisible : 1
         * isGrant : null
         * mores : [{"menuId":"1716203db39f43589b2e4bb6bd30f694","menuNo":"GP001","applicationCode":"","menuParentNo":"GP","menuOrder":1,"menuName":"销售挂牌列表","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/listing/template/list-transaction","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"1716203db39f43589b2e4bb6bd30f695","menuNo":"GP003","applicationCode":"","menuParentNo":"GP","menuOrder":2,"menuName":"采购挂牌列表","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/procurement/template/procurement-transaction","menuIcon":"","isVisible":"0","isGrant":null,"mores":null},{"menuId":"ecddfb8892a44a70ae5d7d1756f824da","menuNo":"YSGP01","applicationCode":"","menuParentNo":"GP","menuOrder":3,"menuName":"预售挂牌列表","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/futures/template/list-transaction\r\n","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"ecddfb8892a44a70ae5d7d0756f824da","menuNo":"YSGP02","applicationCode":"","menuParentNo":"GP","menuOrder":4,"menuName":"配送区域维护","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/futures/template/delivery-group","menuIcon":"","isVisible":"1","isGrant":null,"mores":null}]
         */

        public String menuId;
        public String menuNo;
        public String applicationCode;
        public String menuParentNo;
        public int menuOrder;
        public String menuName;
        public String menuType;
        public int menuLevel;
        public String menuUrl;
        public String menuIcon;
        public String isVisible;
        public Object isGrant;
        public List<MoresBean> mores;
    }

    public class RmsBuyerMenusBean {
        /**
         * menuId : 1716203db39f43589b2e4bb6bd30f735
         * menuNo : YX
         * applicationCode :
         * menuParentNo :
         * menuOrder : 71
         * menuName : 撮合交易
         * menuType : 10
         * menuLevel : 1
         * menuUrl : 0
         * menuIcon :
         * isVisible : 0
         * isGrant : null
         * mores : [{"menuId":"1716203db39f43589b2e4bb6bd30f736","menuNo":"YX001","applicationCode":"","menuParentNo":"YX","menuOrder":1,"menuName":"我的发布","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/intention/template/my-publish-list","menuIcon":"","isVisible":"1","isGrant":null,"mores":null},{"menuId":"e7469d42918242b5a898dffa297a84e5","menuNo":"YX002","applicationCode":null,"menuParentNo":"YX","menuOrder":2,"menuName":"我的响应","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/intention/template/my-response-list","menuIcon":null,"isVisible":"1","isGrant":null,"mores":null},{"menuId":"d281133e1ce04c04a79a0e20d00f792e","menuNo":"YX003","applicationCode":null,"menuParentNo":"YX","menuOrder":3,"menuName":"我的交易单","menuType":"20","menuLevel":2,"menuUrl":"backstage.html#!/Backstage/intention/template/trade-card-list","menuIcon":null,"isVisible":"1","isGrant":null,"mores":null}]
         */

        public String menuId;
        public String menuNo;
        public String applicationCode;
        public String menuParentNo;
        public int menuOrder;
        public String menuName;
        public String menuType;
        public int menuLevel;
        public String menuUrl;
        public String menuIcon;
        public String isVisible;
        public Object isGrant;
        public List<MoresBeanX> mores;
    }

    public class MoresBeanX {
        /**
         * menuId : 1716203db39f43589b2e4bb6bd30f736
         * menuNo : YX001
         * applicationCode :
         * menuParentNo : YX
         * menuOrder : 1
         * menuName : 我的发布
         * menuType : 20
         * menuLevel : 2
         * menuUrl : backstage.html#!/Backstage/intention/template/my-publish-list
         * menuIcon :
         * isVisible : 1
         * isGrant : null
         * mores : null
         */

        public String menuId;
        public String menuNo;
        public String applicationCode;
        public String menuParentNo;
        public int menuOrder;
        public String menuName;
        public String menuType;
        public int menuLevel;
        public String menuUrl;
        public String menuIcon;
        public String isVisible;
        public Object isGrant;
        public Object mores;
    }

    public class RoleTagsBean {
        /**
         * tagType : R20
         * typeName : 默认交易企业
         * keyWord : 8d9t0f8dg9darfd7fd6df5fdsd
         * isPersonal : 0
         */

        public String tagType;
        public String typeName;
        public String keyWord;
        public String isPersonal;
    }

    public class PreferenceTagsBean {
        /**
         * prefName : 橡胶
         * prefType : 10
         * bizId : 05ebc8b797904205b453850f9320f648
         * bizName : 橡胶
         * parentBizName : 石油化工
         * keyword :
         */

        public String prefName;
        public String prefType;
        public String bizId;
        public String bizName;
        public String parentBizName;
        public String keyword;
    }

    public class MoresBean {
        /**
         * menuId : 1716203db39f43589b2e4bb6bd30f694
         * menuNo : GP001
         * applicationCode :
         * menuParentNo : GP
         * menuOrder : 1
         * menuName : 销售挂牌列表
         * menuType : 20
         * menuLevel : 2
         * menuUrl : backstage.html#!/Backstage/listing/template/list-transaction
         * menuIcon :
         * isVisible : 1
         * isGrant : null
         * mores : null
         */

        public String menuId;
        public String menuNo;
        public String applicationCode;
        public String menuParentNo;
        public int menuOrder;
        public String menuName;
        public String menuType;
        public int menuLevel;
        public String menuUrl;
        public String menuIcon;
        public String isVisible;
        public Object isGrant;
        public Object mores;
    }
}
