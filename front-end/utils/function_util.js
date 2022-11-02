// 节流
function throttle(func, wait) {
    let timeout = null;

    return function () {
        if (!timeout) {
            timeout = setTimeout(() => {
                timeout = null;
                func.call(this, arguments);
            }, wait);
        }
    }
}

// 防抖
function debounce(func, wait) {
    let timeout = null;

    return function () {
        clearTimeout(timeout);
        timeout = setTimeout(function () {
            func();
        }, wait);
    }
}

/** 
 * 完成器，以手动的方式 完成、拒绝 Promise 回调
 */
class Completer {
    
    constructor() {
        this.promise = new Promise(
            (resolve, reject) => {
                this.resolve = (v) => resolve(v);
                this.reject = (e) => reject(e);
            }
        );
    }
}

// 监听器 观察者
function getInstanceOfListenable(){
    return {
        listeners: [],
        addListener(listener) {
            this.listeners.push(listener);
        },
        removeListener(listener) {
            this.listeners = this.listeners.filter(v => { return v != listener })
        },
        notifyAll(v){
            this.listeners.forEach(fn =>{fn(v)})
        }
    }
};



module.exports = {
    throttle: throttle,
    debounce: debounce,
    Completer: Completer,
    getInstanceOfListenable: getInstanceOfListenable,
}
