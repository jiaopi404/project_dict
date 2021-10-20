import $http from '@/util/http'

class CusApi {
  static prefix = ''

  constructor() {
  }

  static request (axiosOptions) {
    return $http.request(axiosOptions)
  }
}

export default CusApi
