/**
 * page 变化的 mixin
 */
import _ from 'lodash'
const pageMixin = {
  data () {
    return {
      page: {
        pageSize: 10,
        pageNum: 1
      }
    }
  },
  watch: {
    page: { // 监听page，存储参数
      handler () {
        this.updateQueryParam()
      },
      deep: true,
    }
  },
  created () {
    // 初始化，回填参数
    const { pageSize, pageNum } = this.$route.query
    if (!pageSize || !pageNum) { // 填入 this.$route.query
      this.updateQueryParam()
    } else {
      this.page.pageNum = Number(pageNum) || 1
      this.page.pageSize = Number(pageSize) || 10
    }
  },
  methods: {
    /**
     * 更新 url 中的查询参数
     */
    updateQueryParam () {
      // 过滤掉相同的路由，防止重复跳转
      if (_.isEqual(
        {
          pageNum: this.page.pageNum,
          pageSize: this.page.pageSize
        },
        {
          pageNum: Number(this.$route.query.pageNum),
          pageSize: Number(this.$route.query.pageSize),
        }
      )) {
        return
      }
      this.$router.replace({
        ...this.$route,
        query: {
          ...this.$route.query,
          ...{ pageSize: this.page.pageSize, pageNum: this.page.pageNum }
        }
      })
    }
  }
}

export default pageMixin
