const string_util = require('./string_util');

function dateToCN(datetime) {
    let time = new Date(datetime);
    return time.getFullYear() + "年" + (time.getMonth() + 1) + "月" + time.getDate() + "日";
}

function dateToYYYYMMDD(datetime) {
    let time = new Date(datetime);
    let year = time.getFullYear();
    let month = time.getMonth() + 1;
    let day = time.getDate();
    month = month < 10 ? '0' + month : month;
    day = day < 10 ? '0' + day : day;
    return year + "-" + month + "-" + day;
}

function getAgeByBirthday(datetime) {
    let time = new Date(datetime);
    let nowtime = new Date();

    let nowMonth = nowtime.getMonth() + 1;
    let nowDay = nowtime.getDate();
    let timeMonth = time.getMonth() + 1;
    let timeDay = time.getDate();

    let age = nowtime.getFullYear() - time.getFullYear();
    if (timeMonth < nowMonth || (timeMonth == nowMonth && timeDay < nowDay)) {
        age++;
    }
    return age;
}

function isToday(datetime) {
    if (string_util.isEmpty(datetime)) {
        return '';
    }
    let time = new Date(datetime);
    let nowTime = new Date();
    return time.getFullYear() == nowTime.getFullYear() &&
        time.getMonth() == nowTime.getMonth() &&
        time.getDate() == nowTime.getDate();
}

function isTodayAfter(datetime) {
    if (string_util.isEmpty(datetime)) {
        return true;
    }
    let time = new Date(datetime);
    let nowTime = new Date();
    if (time.getFullYear() > nowTime.getFullYear()) {
        return true;
    } else if (
        (time.getFullYear() == nowTime.getFullYear()) &&
        (time.getMonth() > nowTime.getMonth())
    ) {
        return true;
    } else if (
        (time.getFullYear() == nowTime.getFullYear()) &&
        (time.getMonth() == nowTime.getMonth()) &&
        (time.getDate() > nowTime.getDate())
    ) {
        return true;
    }
    return false;
}

function getYearMonth(datetime) {
    if (string_util.isEmpty(datetime)) {
        return "";
    }
    let time = new Date(datetime);
    let month = time.getMonth();
    month = month + 1;
    if (month < 10) {
        month = "0" + month;
    }
    let year = time.getFullYear();
    return year + "-" + month;
}

function getDay(datetime) {
    if (string_util.isEmpty(datetime)) {
        return "";
    }
    let time = new Date(datetime);
    // console.info(time);
    let day = time.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    // console.info(day);
    return day;
}

module.exports = {
    dateToCN: dateToCN,
    dateToYYYYMMDD: dateToYYYYMMDD,
    getAgeByBirthday: getAgeByBirthday,
    isToday: isToday,
    isTodayAfter: isTodayAfter,
    getYearMonth: getYearMonth,
    getDay: getDay,
}