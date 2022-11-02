
const orderChangshaDistrictList = [
    { districtName: '芙蓉区', priority: 20 },
    { districtName: '天心区', priority: 18 },
    { districtName: '开福区', priority: 16 },
    { districtName: '雨花区', priority: 14 },
    { districtName: '岳麓区', priority: 12 },
    { districtName: '望城区', priority: 10 },
    { districtName: '浏阳市', priority: 8 },
    { districtName: '长沙县', priority: 4 },
    { districtName: '宁乡市', priority: 6 },
    { districtName: '茶陵县', priority: 2 },
]

function sortChangshaDistrict(districtNameList) {
    let orderDistrictList = orderChangshaDistrictList.sort((a, b) => { return a.priority - b.priority }).reverse();
    let tempList = [];
    orderDistrictList.forEach(v => {
        let index = districtNameList.findIndex(r => { return r.districtName === v.districtName })
        if(index != -1){
            tempList.push(districtNameList[index]);
        }
    });
    tempList = tempList.concat(districtNameList.filter(v => {
        return tempList.findIndex(r => { return r.districtName === v.districtName }) == -1
    }))
    return tempList;
}
module.exports = {
    sortChangshaDistrict: sortChangshaDistrict
}