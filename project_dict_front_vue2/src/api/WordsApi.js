import CusApi from '@/api/CusApi'

class WordsApi extends CusApi {
  static prefix = '/wordOxford' // controller 的前缀

  static request (axiosOptions) {
    // 判断 url
    if (WordsApi.prefix) {
      axiosOptions.url = WordsApi.prefix + axiosOptions.url
    }
    return super.request(axiosOptions)
  }

  constructor () {
    super()
  }

  basicQuery (query) {
    return WordsApi.request({
      url: `/baseQuery?query=${query}`,
      method: 'get'
    })
  }
}

const wordsApi = new WordsApi()

export default wordsApi
