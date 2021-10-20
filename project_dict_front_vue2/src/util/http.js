import axios from 'axios'
import HttpError from '@/util/exception/HttpError'

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
    return Promise.reject(new HttpError('请求错误', error));
  },
)

$http.interceptors.response.use(
  response => {
    return Promise.resolve(response)
  },
  error => {
    return Promise.reject(new HttpError('响应错误', error))
  }
)

export default $http
