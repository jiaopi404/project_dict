import CusApi from '@/api/CusApi'

/**
 * 关联表 api
 */
class UserWordOxfordMergeApi extends CusApi {
  static prefix = '/userWordOxfordMerge' // controller 的前缀

  static request (axiosOptions) {
    // 判断 url
    if (UserWordOxfordMergeApi.prefix) {
      axiosOptions.url = UserWordOxfordMergeApi.prefix + axiosOptions.url
    }
    return super.request(axiosOptions)
  }

  constructor () {
    super()
  }

  addCommon ({ userId, wordId }) {
    return UserWordOxfordMergeApi.request({
      url: `/addCommon`,
      method: 'put',
      data: { userId, wordId }
    })
  }

  getPageList ({ userId, pageNum, pageSize, query }) {
    return UserWordOxfordMergeApi.request({
      url: `/getPageList?pageNum=${pageNum}&pageSize=${pageSize}&query=${query}`,
      method: 'post',
      data: { userId }
    })
  }

  removeById (id) {
    return UserWordOxfordMergeApi.request({
      url: `/removeById/${id}`,
      method: 'delete'
    })
  }
}

const userWordOxfordMergeApi = new UserWordOxfordMergeApi()

export default userWordOxfordMergeApi
