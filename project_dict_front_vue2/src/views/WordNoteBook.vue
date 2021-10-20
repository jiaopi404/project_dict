<template>
  <div class="page-word-note-book cus-page">
    <h1>Word notebook</h1>
    <el-input
      style="flex: 1;"
      v-model="kw"
      placeholder="查询关键字"
      @change="getData"
    />
    <el-divider></el-divider>
    <template v-if="wordList.length">
      <recite-word-card
        class="recite-word-card"
        v-for="(item) in wordList"
        :key="item.id"
        mode="remove"
        :word-info="{ wordId: item.wordOxfordId, exp: null, word: item.word, lastTime: item.lastTime }"
        @word:remove="removeWordHandler(item)"
      />
    </template>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size.sync="page.pageSize"
      :current-page.sync="page.pageNum"
      @current-change="paginationCurrentChangeHandler"
    >
    </el-pagination>
  </div>
</template>

<script>
import pageMixin from "@/mixins/pageMixin";
import ReciteWordCard from '@/components/words/ReciteWordCard'
import userWordOxfordMergeApi from "@/api/UserWordOxfordMergeApi";

const WordNoteBook = {
  mixins: [
    pageMixin
  ],
  components: {
    ReciteWordCard
  },
  name: "WordNoteBook",
  data () {
    return {
      total: 100,
      wordList: [], // 单词列表
      userId: '1231565',
      kw: '',
    }
  },
  created () {
    this.getData()
  },
  methods: {
    async getData () {
      const res = await userWordOxfordMergeApi.getPageList({
        userId: this.userId,
        ...this.page,
        query: this.kw
      })
      this.wordList = res.data.list
      this.total = res.data.total
    },
    paginationCurrentChangeHandler (...args) {
      console.log('当前页变化', args)
      this.getData()
    },
    async removeWordHandler (wordInfo) {
      console.log('移除单词', wordInfo)
      await userWordOxfordMergeApi.removeById(wordInfo.id)
      this.$message.error("移除成功")
      this.getData()
    }
  }
}
export default WordNoteBook
</script>

<style lang="scss" scoped>
.page-word-note-book {}
</style>