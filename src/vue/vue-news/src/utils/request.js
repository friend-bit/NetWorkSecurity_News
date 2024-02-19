import axios from "axios";
import Vue from "vue";

const request=axios.create({
    baseURL:'http://localhost:8888',
    timeout:5000
})

request.interceptors.response.use(response => {
        let res=response.data;
        if(typeof res ==='string'){
            res=res?JSON.parse(res):res;
        }
        return res;
    },error => {
        console.log('err'+error);
        return Promise.reject(error);
    }
);


// request.interceptors.request.use(config => {
//     config.headers.token=localStorage.getItem('token');
//         return config;
//     }),err=>{
//         return Promise.reject(err);
//     }
// Vue.prototype.$http=axios;
export default request


