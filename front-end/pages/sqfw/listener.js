// 社区服务主页监听器方法
const createListenerMethods = () => ({
    async communityInfoChangeListener() {
        await this.loadContent();
    },

    async functionTagChangeListener(){
        await this._loadFunctionTag();
    },

});

module.exports = {
    createListenerMethods: createListenerMethods,
}