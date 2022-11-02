// pages/aboutus/aboutus.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        tempImg: "",
        choosedStreetList: [],
    },
    callGetCommonSign(tempFilePath) {
        console.log(tempFilePath);
        this.setData({ tempImg: tempFilePath });
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },


    /** 组件方法 */
    bindtapChooseCommunity(e) {
        this.chooseCommunityComponent.showPopup();
    },
    submitChooseStreet: function (e) {
        let choosedStreetList = e.detail.value;
        if (choosedStreetList.length === 0) {
            return;
        }
        this.setData({
           choosedStreetList: choosedStreetList,
        });
        console.info(this.data.choosedStreetList)
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {
        // 加载社区组件
        this.chooseCommunityComponent = this.selectComponent("#choose-street");
    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})