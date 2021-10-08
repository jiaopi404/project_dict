import axios from 'axios'

const $http = axios.create({
  baseURL: '',
  timeout: 10000, // 10s
  headers: {},
  withCredentials: false // 跨域时，要发生 cookie，要设置此选项
})

$http.interceptors.request.use(
  config => {
    return config
  },
  error => {
    return Promise.reject(error);
  },
)

$http.interceptors.response.use(
  response => {
    return Promise.resolve(response)
  },
  error => {
    return Promise.reject(error)
  }
)


export default $http
