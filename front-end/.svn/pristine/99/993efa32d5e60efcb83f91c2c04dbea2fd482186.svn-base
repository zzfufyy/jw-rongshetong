// 防抖 默认500ms
function debounce(fn, delay = 500) {
    let timer = null;
    return function () {
        if (timer) {
            clearTimeout(timer);
        }
        timer = setTimeout(() => {
            fn.apply(this, arguments);
            clearTimeout(timer);
        }, delay);
    }
}

// 节流
function throttle(fn, delay = 100) {
    let timer = null;
    return function () {
        if (timer) return;
        timer = setTimeout(() => {
            fn.apply(this, arguments);
            timer = null;
        }, delay)
    }
}