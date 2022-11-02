
class Lock{
    constructor(){
        this.isLock = false;
    }
    lock(){
        this.isLock = true;
    }
    unLock(){
        this.isLock = false;
    }
}

function beginLoading(lock) {
    if (typeof (lock) != 'undefined') {
        try {
            lock.lock();
        } catch (e) {
            console.error(e)
        }
    }
    wx.showLoading({
        title: '加载中，请稍后',
        mask: true,
    })
}

function endLoading(lock) {
    setTimeout(function () {
        if (typeof (lock) != 'undefined') {
            try {
                lock.unLock();
            } catch (e) {
                console.error(e)
            }
        }
        wx.hideLoading();
    }, 300)
}


function wrapLoading(fn, ...inputArgs) {
    console.log(fn() instanceof Promise);
    let handler = {
        async apply(target, ctx, args) {
            let rs;
            try {
                beginLoading();
                rs = await Reflect.apply(...arguments);
            } catch (e) {
                console.error(e);
            } finally {
                endLoading();
            }
            return rs;
        }
    }
    let proxy = new Proxy(fn, handler);
    return proxy(...inputArgs);
}

module.exports = {
    Lock: Lock,
    begin: beginLoading,
    end: endLoading,
    wrap: wrapLoading,
}