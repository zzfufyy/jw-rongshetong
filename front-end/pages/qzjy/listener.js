// 求职就业监听器方法

const createListenerMethods = () => ({
    candidateTelephoneListener(telephone) {
        this.setData({
            candidateTelephone: telephone,
        })
    },
    sendResumeListener(jobUuid) {
        console.log('index投递简历状态变化：jobUuid:' + jobUuid);
        this.setData({
            jobList: this.data.jobList.map(v => {
                if (v.jobUuid == jobUuid) {
                    v.flagApply = true;
                    v.flagApplyText = '已投递';
                }
                return v;
            })
        })
    }
});

module.exports = {
    createListenerMethods: createListenerMethods,
}