const { Listenable } = require('../utils/function_util');

// 全局可变量存储类，全局单例
class GlobalService {
    constructor(app) {
        this._app = app;
        this._data = {};
    }

    set(k, v) {
        this._data[k] = v;
    }

    get(k) {
        return this._data[k];
    }

    remove(k) {
        let v = get(k);
        this._data[k] = null;
        return v;
    }

}

// 初始化全局状态
function initGlobal(app) {
    
}


const GlobalKey = {
    UserInfoChanged: 'USER_INFO_CHANGED',
}

module.exports = {
    GlobalService: GlobalService,
    GlobalKey: GlobalKey,
    initGlobal: initGlobal,
}