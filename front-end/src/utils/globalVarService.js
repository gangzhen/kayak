import Vue from "vue";

Vue.prototype.$adminRoleOptions = [
    {label: '管理员', value: 'admin',},
];

Vue.prototype.$roleOptions = [
    {label: '运动员', value: 'athlete',},
    {label: '教练员', value: 'coach',},
];

Vue.prototype.$genderOptions = [
    {label: '男', value: 'male',},
    {label: '女', value: 'female',},
];

Vue.prototype.$regionOptions = [
    {label: '地区一', value: 'region1',},
    {label: '地区二', value: 'region2',},
    {label: '地区三', value: 'region3',},
    {label: '地区四', value: 'region4',},
];

Vue.prototype.$levelOptions = [
    {label: 'A2000', value: 'A2000',},
    {label: 'A1600', value: 'A1600',},
    {label: 'A1200', value: 'A1200',},
    {label: 'A900', value: 'A900',},
    {label: 'B800', value: 'B800',},
    {label: 'B600', value: 'B600',},
    {label: 'B500', value: 'B500',},
    {label: 'C400', value: 'C400',},
    {label: 'C200', value: 'C200',},
    {label: 'C100', value: 'C100',},
];

Vue.prototype.$totalCodeOptions = [
    {label: '32名以上 (≥32)', value: '1',},
    {label: '32名以下 (<32)', value: '2',},
];

Vue.prototype.$rankingCodeOptions = [
    {label: '冠军', value: '1',},
    {label: '亚军', value: '2',},
    {label: '半决赛', value: '3',},
    {label: '前8名', value: '4',},
    {label: '前16名', value: '5',},
    {label: '前32名', value: '6',},
];