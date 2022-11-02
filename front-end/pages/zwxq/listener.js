// 求职就业监听器方法

const createListenerMethods = () => ({
    candidateTelephoneListener(telephone) {
        this.setData({
            candidateTelephone: telephone,
        })
    },

});

module.exports = {
    createListenerMethods: createListenerMethods,
}