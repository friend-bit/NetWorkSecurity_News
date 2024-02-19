import axios from "axios";

const request=axios.create({
    baseURL:'http://localhost:8989',
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


export default request


