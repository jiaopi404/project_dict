<template>
  <div class="page-recite cus-page">
    <h1 class="recite-search">Search...</h1>
    <el-input
      v-model="kw"
      placeholder="输出查询关键字"
      @change="kwChangeHandler"
    />
  </div>
</template>

<script>
import wordsApi from '@/api/WordsApi'

const PageRecite = {
  name: 'PageRecite',
  data () {
    return {
      kw: ''
    }
  },
  methods: {
    async kwChangeHandler (value) {
      if (!value || value.length < 2) {
        return
      }
      const res = await wordsApi.basicQuery(value)
      console.log('%c [res is: ] ', 'color: #67C23A; font-size: 16px;', res)
    }
  }
}
export default PageRecite
</script>

<style lang="scss" scoped>
.page-recite {
  .recite-search {
    text-align: center;
  }
}
</style>